/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import classes.matrice.CRS;
import classes.matrice.MatriceCreuse;
import interfaces.Fonction;
import java.util.ArrayList;

/**
 *
 * @author SCrf1
 */
public class Main 
{
    public static void main(String[] args)
    {
        VF vf = new VF();
        double[] res = vf.volumesFinis(1000, 0, 0, (double x) -> 2);
        double[] sub = vf.subdivision(0, 1, 1000);
        
        
//        for (int i = 0; i < sub.length; i++) {
//            System.out.println(sub[i]);
//        }
        for (int i = 0; i < res.length; i++) {
            System.out.println("Resultat = " + res[i] + "   RA = " + sub[i] * sub[i] + "  Erreur => " + Math.abs(res[i] - sub[i]*sub[i]) + "");
        }
    }
}
