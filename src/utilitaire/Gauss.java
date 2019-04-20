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
import java.util.Arrays;

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
        MatriceCreuse M;
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        
        System.out.println("Enter the number meshes in the equation:");
        n = Integer.parseInt(reader.readLine());
        //Here he are going to fill the matrix M with n*n meshes
        M = remplir(n,n);

        int m = (n-1)*(n-1);
        double[] b = new double[m];
        
        //TODO Here we need to refill b using the method given by Mandingo Mandenga
        for (int i=0; i<m; i++){
            b[i] = 1.0;
        }
        
        //This part solves the matric equation and prints the results of X
        double[] x = GaussSiedel(M,b); 
        for(int i=0;i<x.length;i++)
            System.out.println("X["+i+"] = "+x[i]);
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
}
