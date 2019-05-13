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
public interface IDF2 extends Subdivision2
{
    /**
     * Résolution de l'équation: - u" = f en dimension 2
     * 
     * @param n: Nombre de points de subdivision en abscisse
     * @param m: Nombre de points de subdivision en ordonnées
     * @param f
     * @param u_gamma
     * @return : les valeurs de la fonction U aux différents points du maillage, en suivant l'ordre de numérotation
     */
    double[] differenceFinies2(int n, int m, Fonction2 f, Fonction2 u_gamma);
}
