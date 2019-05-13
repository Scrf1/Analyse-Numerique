/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author FOFE
 */
public interface Subdivision2 
{

    /**
     * @param args the command line arguments
     */
   
    default List subdivision (double a, double b, int n,int m)
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
