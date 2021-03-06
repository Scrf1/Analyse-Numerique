/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitaire;

import classes.matrice.MatriceCreuse;
import interfaces.Fonction2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import py4j.GatewayServer;



/**
 *
 * @author dell
 */
public class Gauss {
    public static final int MAX_ITERATIONS = 500;
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
        double epsilon = 1e-8;
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
                X[i] = (1/M.get(i,i))*sum;
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
        m = M.getNbreColonnes();
        for(int i = 0; i <= M.getNbreLignes(); i++){
            M.set(i, m, b[i]);
        }
        return M;
    }

 
   
    public static void sendToPython(double[] u, ArrayList<utilitaire.Position> position, int n,int m){
        System.out.println("Equation solved, now passing it to python\n");
        GetArray array = new GetArray(u,position,n,m);
        GatewayServer gatewayServer = new GatewayServer(new GetArray(u,position,n,m));
        gatewayServer.start();
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
