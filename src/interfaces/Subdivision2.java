/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subdivision2;

import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author FOFE
 */
public class Subdivision2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    /*
     double[][] subdivision2(double a, double b, int n,int m)
    {
        
        double[] [] subdivision2 = null;
        if(n <= 1 || m <= 1)
            return subdivision2 ;
        else{
            double h= (b-a)/n;
            double k= (b-a)/m;
            subdivision2 = new double[n][m];
            for(int i = 0; i < n ; i++){
                for(int j=0;j<m;j++){
                subdivision2[i][j] = a + (i + 1)*(b - a)/(n);
            }}
            return subdivision;
        }
    };
   
   */  
     
     
     
     
    List subdivision (double a, double b, int n,int m)
    {
        
        List  subdivision = null;
        if(n <= 1 || m <= 1)
            return subdivision ;
        else{
            double h= (b-a)/n;
            double k= (b-a)/m;
          double[]  subdivision1 = new double[n];
          double[]  subdivision2 = new double[m];

            for(int i = 0; i < n ; i++){
               
                subdivision1[i] = h*i;
            }
            for(int j = 0; j < m ; j++){
               
                subdivision2[j] = k*j;
            }
            subdivision.add(subdivision1);
              subdivision.add(subdivision2);
            return subdivision;
        }
    };
    
}
