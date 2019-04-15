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

public interface IDF extends Subdivision
{
    double[] differencesFinies(double a, double b, int n, Fonction f, Fonction c,double u_debut,double u_fin);
}
