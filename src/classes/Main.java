/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import classes.matrice.CRS;
import classes.matrice.MatriceCreuse;
import java.util.ArrayList;

/**
 *
 * @author SCrf1
 */
public class Main 
{
    public static void main(String[] args)
    {
        double t[][] = {
                            {10, 0, 0, 0, -2, 0}
                           ,{3, 9, 0, 0, 0, 3}
                           ,{0, 7, 8, 7, 0, 0}
                           ,{3, 0, 8, 7, 5, 0}
                           ,{0, 8, 0, 9, 9, 13}
                           ,{0, 4, 0, 0, 2, -1}
                        }; 
        MatriceCreuse crs = new MatriceCreuse(6,6);
        
        
        for(int i = 0; i<t.length; i++)
        {
            for(int j =0; j<t[i].length; j++)
            {
                crs.set(i, j, t[i][j]);
            }
        }
        System.out.println();
        //crs.print();
        ArrayList<Double> d = new ArrayList<>();
        for (int i = 0; i < crs.getNbreColonnes(); i++) {
            d.add(1.);
        }
        double[] result = crs.matriceVecteur(d);
        
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
        
    }
}
