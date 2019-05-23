/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitaire;

/**
 *
 * @author dell
 */
import classes.matrice.MatriceCreuse;
import interfaces.Fonction2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import static utilitaire.Gauss.num;
import static utilitaire.Gauss.remplir;
 
public class Gauss_Sleidel 
{
    public static final int MAX_ITERATIONS = 100;  
    private double[][] M;
    public Gauss_Sleidel(double [][] matrix) { M = matrix; }
 
    public void print()
    {
        int n = M.length;
        for (int i = 0; i < n; i++) 
        {
            for (int j = 0; j < n + 1; j++)
                System.out.print(M[i][j] + " ");
            System.out.println();
        }
    }
 
    public boolean transformToDominant(int r, boolean[] V, int[] R)
    {
        int n = M.length;
        if (r == M.length) 
        {
            double[][] T = new double[n][n+1];
            for (int i = 0; i < R.length; i++) 
            {
                for (int j = 0; j < n + 1; j++)
                    T[i][j] = M[R[i]][j];
            }
 
            M = T;
 
            return true;
        }
 
        for (int i = 0; i < n; i++) 
        {
            if (V[i]) continue;
 
            double sum = 0;
 
            for (int j = 0; j < n; j++)
                sum += Math.abs(M[i][j]);
 
            if (2 * Math.abs(M[i][r]) > sum) 
            { // diagonally dominant?
                V[i] = true;
                R[r] = i;
 
                if (transformToDominant(r + 1, V, R))
                    return true;
 
                V[i] = false;
            }
        }
 
        return false;
    }
 
    public boolean makeDominant()
    {
        boolean[] visited = new boolean[M.length];
        int[] rows = new int[M.length];
 
        Arrays.fill(visited, false);
 
        return transformToDominant(0, visited, rows);
    }
 
    public void solve()
    {
        int iterations = 0;
        int n = M.length;
        double epsilon = 1e-15;
        double[] X = new double[n]; // Approximations
        double[] P = new double[n]; // Prev
        Arrays.fill(X, 0);
 
        while (true) 
        {
            for (int i = 0; i < n; i++) 
            {
                double sum = M[i][n]; // b_n
 
                for (int j = 0; j < n; j++)
                    if (j != i)
                        sum -= M[i][j] * X[j];
 
        // Update x_i to use in the next row calculation
                X[i] = 1/M[i][i] * sum;
            }
 
            System.out.print("X_" + iterations + " = {");
            for (int i = 0; i < n; i++)
                System.out.print(X[i] + " ");
            System.out.println("}");
 
            iterations++;
            if (iterations == 1) 
                continue;
 
            boolean stop = true;
            for (int i = 0; i < n && stop; i++)
                if (Math.abs(X[i] - P[i]) > epsilon)
                    stop = false;
 
            if (stop || iterations == MAX_ITERATIONS) break;
            P = (double[])X.clone();
        }
    }
 
    public static void main(String[] args) throws IOException
    {
        int n;
//        double[][] M = {{0.25,-0.0625,0.0,-0.0625,0.0,0.0,0.0,0.0,0.0,-3.875},
//            {-0.0625,0.25,-0.0625,0.0,-0.0625,0.0,0.,0.0,0.0,-3.9375},
//            {0.0,-0.0625,0.25,0.0,0.0,0.0,0.0,0.0,0.0,-3.9375},
//            {-0.0625,0.0,0.0,0.25,-0.0625,0.0,-0.0625,0.0,0.0,-3.9375},
//            {0.0,-0.0625,0.0,-0.0625,0.25,-0.0625,0.0,-0.0625,0.0,-4.0},
//            {0.0,0.0,-0.0625,0.0,-0.0625,0.25,0.0,0.0,0.0,-4.0},
//            {0.0,0.0,0.0,-0.0625,0.0,0.0,0.25,-0.0625,0.0,-3.9375},
//            {0.0,0.0,0.0,0.0,-0.0625,0.0,-0.0625,0.25,-0.0625,-4.0},
//            {0.0,0.0,0.0,0.0,0.0,-0.0625,0.0,-0.0625,0.25,-4.0}};


        int m; 
         Equation equat; 
        MatriceCreuse M;
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
              PrintWriter writer = new PrintWriter(System.out, true);
        
        System.out.println("Enter the number n of meshes in the equation:");
        n = Integer.parseInt(reader.readLine());
        System.out.println("Enter the number m of meshes in the equation:");
        m = Integer.parseInt(reader.readLine());
        //Here he are going to fill the matrix M with n*n meshes
        MatriceCreuse matriceCreuse=new MatriceCreuse (n,m);
        for (int i=0;i<n;i++)
        {
            for (int j=0;j<m;j++)
                matriceCreuse.set (i,j,1);
        }
        Fonction2 f = (double x,double y)->-4;
        equat = remplir(n,m,f,matriceCreuse);
        double[]  b = equat.B;
        System.out.println("Size of equat.A"+equat.A.getNbreLignes()+"*"+equat.A.getNbreColonnes());
         for (int i=0;i<equat.A.getNbreLignes()+1;i++)
        {
            for (int j=0;j<equat.A.getNbreColonnes()+2;j++){
                System.out.print("\t \t"+equat.A.get(i,j));
            }
            System.out.println("\n");
                
        }
        M = augmentMatrix(equat.A, b);
        double MM[][] = new double[M.getNbreLignes()+1][M.getNbreColonnes()+1];
        System.out.println("Matrix after Augment");
         for (int i=0;i<M.getNbreLignes()+1;i++)
        {
            for (int j=0;j<M.getNbreColonnes()+1;j++){
                MM[i][j] = M.get (i,j);
                System.out.print("\t \t"+MM[i][j]);
            }
            System.out.println("\n");
                
        }
//        for (int i = 0; i < n; i++) 
//        {
//            StringTokenizer strtk = new StringTokenizer(reader.readLine());
// 
//            while (strtk.hasMoreTokens())
//                for (int j = 0; j < n + 1 && strtk.hasMoreTokens(); j++)
//                    M[i][j] = Integer.parseInt(strtk.nextToken());
//        }
 
 
        Gauss_Sleidel gausSeidel = new Gauss_Sleidel(MM);
 
        if (!gausSeidel.makeDominant()) 
        {
            writer.println("The system isn't diagonally dominant: " +
                     "The method cannot guarantee convergence.");
        }
 
        writer.println();
        gausSeidel.print();
        gausSeidel.solve();
    }
    
     public static Equation remplir(int n,int m,Fonction2 f,MatriceCreuse Ucn)
    {

        int k=num(n-1,m-1,n);
        MatriceCreuse tab=new MatriceCreuse(k,k);
        Equation equation=new Equation();
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
      public static MatriceCreuse augmentMatrix(MatriceCreuse M, double[] b){
        int m = M.getNbreColonnes();
          System.out.println("Number of columns in augmaent method "+m);
        M.setNbreColonnes(m+1);
        m = M.getNbreColonnes();
     
          System.out.println("Updated n of C"+ M.getNbreColonnes());
        for(int i = 0; i <= M.getNbreLignes(); i++){
            M.set(i, m, b[i]);
            System.out.println(M.get(i, m)+" instead of "+b[i]);
        }
          System.out.println("In the augment method");
          for (int i=0;i<M.getNbreLignes()+1;i++)
        {
            for (int j=0;j<M.getNbreColonnes()+1;j++){
                System.out.print("\t \t"+M.get(i, j));
            }
            System.out.println("\n");
                
        }
        return M;     
    }
           
    public static int num(int i,int j,int n)
    {
        return (i-1)+(n-1)*(j-1);
    }
}
 