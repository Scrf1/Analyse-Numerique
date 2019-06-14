/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import classes.matrice.MatriceCreuse;
import interfaces.Fonction2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import utilitaire.Equation;
import static utilitaire.Gauss.GaussSiedel;
import static utilitaire.Gauss.positionUi;
import static utilitaire.Gauss.sendToPython;

/**
 *
 * @author dell
 */
public class DF2 {
    public static void main(String[] args) throws IOException{
         int n,m; 
         Equation equat; 
        MatriceCreuse M;
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        
//        System.out.println("Enter the number n of meshes in the equation:");
//        n = Integer.parseInt(reader.readLine());
//        System.out.println("Enter the number m of meshes in the equation:");
//        m = Integer.parseInt(reader.readLine());
        //Here he are going to fill the matrix M with n*n meshes
        n=20;
        m=20;
        Fonction2 f = (double x,double y)->-Math.sin(x);
        Fonction2 g = (double x,double y)->Math.sin(x);
        System.out.println("I am filling the matrix now");
        equat = remplir(n,m,f,g);
        M = equat.A;
        double[]  b = equat.B;


        //TODO Here we need to refill b using the method given by Mandingo Mandenga
       
        
                
        double[] u = GaussSiedel(M,b);
        ArrayList<utilitaire.Position> position =  positionUi(u, n);
        sendToPython(u, position, n, m);
          for(int i=0;i<u.length;i++)
            System.out.println("X["+i+"] = "+u[i]+" position ("+position.get(i).getI()+","+position.get(i).getJ()+")");

        System.out.println("Gateway Server Started");
    }
    
       public static Equation remplir(int n, int m, Fonction2 f, Fonction2 g)
    {

        int k=num(n-1,m-1,n);
        MatriceCreuse tab=new MatriceCreuse(k,k);
        Equation equation=new Equation ();
        equation.B=new double[k+1];
        int d=0;
        /**
         * Initialisation de la matrice B avec les zeros
         */
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
                tab.set(x,x,2*(n*n+m*m));
                if ((i-1)>0)
                {
                    tab.set(x,y,(-(n*n)));
                }
                if ((i+1)<n)
                {

                    tab.set(x,z,-(n*n));
                }
                if ((j-1)>0)
                {
                    tab.set(x,t,-(m*m));
                }
                if ((j+1)<n)
                {
                    tab.set(x,w,-(m*m));
                }
                if ((i+1)==n)
                {
                    equation.B[x]+= ((n*n)*g.calcul(1.0,(1.0*j)/m));
                }
                if ((i-1)==0)
                {
                    equation.B[x]+= ((n*n)*g.calcul (0.0,(1.0*j)/m));
                }
                if ((j-1)==0)
                {
                    equation.B[x]+= ((m*m)*g.calcul ((1.0*i)/n,0.0));
                }
                if ((j+1)==m)
                {
                    equation.B[x]+= ((m*m)*g.calcul ((1.0*i)/n,1.0));
                }

            }
        }
        equation.A=tab;
        return equation;
    }
    
   
        
    public static int num(int i,int j,int n)
    {
        return (i-1)+(n-1)*(j-1);
    }
    
}
