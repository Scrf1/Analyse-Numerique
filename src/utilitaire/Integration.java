/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitaire;

import interfaces.Fonction;

/**
 *
 * @author SCrf1
 */
public class Integration 
{
    
    public static double gaussLegrend(double a, double b, Fonction f)
    {
        return ((b - a)/2) * ( f.calcul( (a-b)/(2* Math.sqrt(3)) + (a+b)/2 ) + f.calcul((b-a)/(2* Math.sqrt(3)) + (a+b)/2) );
    }
}
