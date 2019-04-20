/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import abstraites.VFabstrait;
import interfaces.Fonction;
import utilitaire.Integration;

/**
 *
 * @author SCrf1
 */
public class VF extends VFabstrait
{
    @Override
    public double[] volumesFinis(double a, double b, int n, Fonction f, double u_debut, double u_fin) 
    {
        double[] maillage = null;
        maillage = subdivision(a, b, n);
        
        if(maillage != null)
        {
            double tabf[] = new double[maillage.length];
            double beta[] = new double[maillage.length];
            double alpha[] = new double[maillage.length];

            //calcul du pas
            double pas = (b - a)/n;


            for (int i = 1; i<maillage.length - 1; i++)
            {
                tabf[i] = Integration.gaussLegrend((maillage[i] + maillage[i-1]) / 2, (maillage[i+1] + maillage[i]) / 2, f);//f.calcul(maillage[i]) * pas *pas;
            }
            tabf[0] = Integration.gaussLegrend(a + pas / 2, maillage[0] + pas / 2, f) - (a/pas - b) * u_debut;
            tabf[maillage.length-1] = Integration.gaussLegrend(a, b, f) + (a / pas) * u_fin;

            //initialisation
            alpha[0] = 2;
            beta[0] = tabf[0]/alpha[0];

            //iteration
            for (int i = 1; i < maillage.length; i++)
            {
                alpha[i] = 2 - (1.0/alpha[i-1]);
                beta[i] = (tabf[i] + beta[i-1])/alpha[i];
            }

            //solution finale x
            double u[] = new double[maillage.length];
            u[maillage.length - 1] = beta[maillage.length - 1];
            for (int i = maillage.length-2; i >= 0; i--)
            {
                u[i] = beta[i] + (u[i+1]/alpha[i]);
            }
            return u;
        }
        return null;
    }
    
}
