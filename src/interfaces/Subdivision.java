/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author SCrf1
 */
public interface Subdivision
{
    default double[] subdivision(double a, double b, int n)
    {
        double[] subdivision = null;
        if(n <= 1)
            return subdivision;
        else{
            subdivision = new double[n - 1];
            for(int i = 0; i < n - 1; i++){
                subdivision[i] = a + (i + 1)*(b - a)/(n);
            }
            return subdivision;
        }
    };
    
}
