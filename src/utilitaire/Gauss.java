/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaire;

import classes.matrice.MatriceCreuse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import py4j.GatewayServer;
import utilitaire.Fonction;


/**
 *
 * @author dell
 */
public class Gauss {
    public static final int MAX_ITERATIONS = 100;
    private MatriceCreuse M;
    public Gauss(MatriceCreuse matrix) {M = matrix;}
    
    // this method is to print the values of the elements of the matrix M
    public void print(){
        int n=M.getNbreLignes();
        for (int i=0; i< n; i++){
            for (int j=0; j<n+1; j++)
                System.out.println(M.get(i, j)+" ");
            System.out.println();
        }
    }
    
    /* This function helps us to decompose the matrix A into L* and U   */
    public boolean transformToDominant(int r, boolean[] V, int[] R){
        int n= M.getNbreLignes();
        if( r == M.getNbreLignes()){
            MatriceCreuse T = new MatriceCreuse(n,n+1);
            for(int i=0; i< R.length; i++){
                for(int j=0; j<n+1; j++)
                    T.set(i, j, M.get(R[i], j));
            }
            M=T;     
            return true;
        }
        
        for(int i=0; i<n; i++){
            if(V[i]) continue;
            double sum = 0;
            for(int j=0; j<n; j++)
                sum +=Math.abs(M.get(i, j));
            if(2*Math.abs(M.get(i,r)) > sum){
                //Diagonally dorminant?
                V[i] = true;
                R[r] = i;
                
                if (transformToDominant(r+1, V, R))
                    return true;
                V[i] = false;
            }
        }
        return false;
    }
    
    public boolean makeDomninant(){
        boolean[] visited = new boolean[M.getNbreLignes()];
        int[] rows = new int[M.getNbreLignes()];
        Arrays.fill(visited, false);
        return transformToDominant(0,visited,rows);
    }
    
    // This is the main solve function which resolves the equation in question
    public double[] solve(){
        int iterations = 0;
        int n = M.getNbreColonnes();
        double epsilon = 1e-15;
        double[] X = new double[n];
        double[] P = new double[n];
        Arrays.fill(X, 0);
        
        while(true){
            for(int i=0; i<n; i++){
                double sum = M.get(i, n);
                for (int j=0; j<n; j++)
                    if(j != i)
                        sum -= M.get(i, j)*X[j];
                //updating x_i for use in next row calc
                X[i] = 1/M.get(i,i)*sum;
            }
            iterations++;
            if(iterations == 1)
                continue;

            boolean stop = true;
            for(int i=0; i<n && stop; i++)
                if(Math.abs(X[i] - P[i]) > epsilon)
                    stop = false;
            if(stop || iterations == MAX_ITERATIONS) break;
            P = (double[])X.clone();
        }
        return X;
    }
    
    public static MatriceCreuse augmentMatrix(MatriceCreuse M, double[] b){
        int m = M.getNbreColonnes();
        M.setNbreColonnes(m+1);
        for(int i = 0; i < M.getNbreLignes(); i++){
            M.set(i, m, b[i]);
        }
        return M;
    }
    
    public static Equation remplir(int n,int m,Fonction f,MatriceCreuse Ucn)
    {

        int k=num(n-1,m-1,n);
        MatriceCreuse tab=new MatriceCreuse(k,k);
        Equation equation=new Equation ();
        equation.B=new double[k+1];
        int d=0;
    
        for (double index:equation.B)
        {
            index=0;
        }

        for (int i=1;i<n;i++)
        {
            for (int j=1;j<m;j++)
            {
                int x=num(i,j,n),y=num(i-1,j,n),z=num(i+1,j,n),t=num(i,j-1,n),w=num(i,j+1,n);
                equation.B[x]=f.calcul ((double) i/n,(double)j/n);
                tab.set(x,x,2.0*((1.0/(n*n))+(1.0/(m*m))));
                if ((i-1)>0)
                {
                    tab.set(x,y,(-1.0/(n*n)));
                }
                if ((i+1)<n)
                {
                    tab.set(x,z,(-1.0/(n*n)));
                }
                if ((j-1)>0)
                {
                    tab.set(x,t,(-1.0/(m*m)));
                }
                if ((i+1)<n)
                {
                    tab.set(x,w,(-1.0/(m*m)));
                }
                if ((i+1)==n)
                {
                    equation.B[x]+= (1.0/(n*n)*Ucn.get (n,j));
                }
                if ((i-1)==0)
                {
                    equation.B[x]+= (1.0/(n*n)*Ucn.get (0,j));
                }
                if ((j-1)==0)
                {
                    equation.B[x]+= (1.0/(m*m)*Ucn.get (i,0));
                }
                if ((j+1)==m)
                {
                    equation.B[x]+= (1.0/(m*m)*Ucn.get (i,m));
                }

            }
        }
        equation.A=tab;
        return equation;
    }
    
    public static MatriceCreuse remplir(int n,int m)
    {
        int k=num(n-1,m-1,n);
        MatriceCreuse tab=new MatriceCreuse(k,k);
        int d=0;
        for (int i=1;i<n;i++)
        {
            for (int j=1;j<m;j++)
            {
                int x=num(i,j,n),y=num(i-1,j,n),z=num(i+1,j,n),t=num(i,j-1,n),w=num(i,j+1,n);
                tab.set(x,x,2.0*((1.0/(n*n))+(1.0/(m*m))));
                if ((i-1)>0)
                {
                    tab.set(x,y,(-1.0/(n*n)));
                }
                if ((i+1)<n)
                {
                    tab.set(x,z,(-1.0/(n*n)));
                }
                if ((j-1)>0)
                {
                    tab.set(x,t,(-1.0/(m*m)));
                }
                if ((i+1)<n)
                {
                    tab.set(x,w,(-1.0/(m*m)));
                }
            }
        }
        return tab;
    }
        
    public static int num(int i,int j,int n)
    {
        return (i-1)+(n-1)*(j-1);
    }
    
    public static void main(String[] args) throws IOException{
         int n; 
         Equation equat; 
        MatriceCreuse M;
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        
        System.out.println("Enter the number meshes in the equation:");
        n = Integer.parseInt(reader.readLine());
        //Here he are going to fill the matrix M with n*n meshes
        MatriceCreuse matriceCreuse=new MatriceCreuse (n,n);
        for (int i=0;i<n;i++)
        {
            for (int j=0;j<n;j++)
                matriceCreuse.set (i,j,1);
        }
        Fonction f = (double x,double y)->x+y;
        equat = remplir(n,n,f,matriceCreuse);
        M = equat.A;
        //M = remplir(n, n);

        int m = (n-1)*(n-1);
        double[] b = new double[m];
        
        //TODO Here we need to refill b using the method given by Mandingo Mandenga
        b = equat.B;
        
        /*This part solves the matric equation and prints the results of U*/
        double[] u = GaussSiedel(M,b); 
        ArrayList<utilitaire.Position> position =  positionUi(u, n);
        System.out.println("Equation solved, now passing it to python\n");
        GetArray array = new GetArray(u,position);

        GatewayServer gatewayServer = new GatewayServer(new GetArray(u,position));
        gatewayServer.start();
          for(int i=0;i<u.length;i++)
            System.out.println("X["+i+"] = "+u[i]+" position ("+position.get(i).getI()+","+position.get(i).getJ()+")");
          
        System.out.println("Gateway Server Started");
    }
    
    public static double[] GaussSiedel(MatriceCreuse M, double[] b){    
        //double[] b = {1.0,1.0,1.0}; 
        PrintWriter writer = new PrintWriter(System.out, true);
        
        M = augmentMatrix(M,b);
        Gauss gausSeidel = new Gauss(M);
        if(!gausSeidel.makeDomninant()){
            writer.println("The system isn't diagonally dominant: The method cannot guarantee convergence.");
        }
        writer.println();
        return gausSeidel.solve();   
    }
      public static ArrayList<utilitaire.Position> positionUi(double[] u,int n){
        int i,j;
        ArrayList<utilitaire.Position> pos = new ArrayList();
        for (int k=0; k<u.length; k++ ){
            i = k%(n-1) + 1;
            j = k/(n-1) +1;
            pos.add(new utilitaire.Position(i,j,n));
        }
        return pos;
    }

  
}
