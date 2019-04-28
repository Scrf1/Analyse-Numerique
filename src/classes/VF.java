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
    
//    @Override
//    public double[] volumesFinis(double r, double s, int n, Fonction f, double a, double b, double c, double u_r, double u_s)
//    {
//        // resolution de l'équation :
//        // -au"(x) + b u'(x) + c u(x) = f(x) 
//        
//        double[] maillage = null;
//        maillage = subdivision(r, s, n);
//        
//        if(maillage != null)
//        {
//            double tabf[] = new double[maillage.length];
//            double beta[] = new double[maillage.length];
//            double alpha[] = new double[maillage.length];
//
//            //calcul du pas
//            double pas = (s - r)/n;
//
//
//            for (int i = 1; i<maillage.length - 1; i++)
//            {
//                tabf[i] = Integration.gaussLegrend((maillage[i] + maillage[i-1]) / 2, (maillage[i+1] + maillage[i]) / 2, f);//f.calcul(maillage[i]) * pas *pas;
//            }
//            tabf[0] = Integration.gaussLegrend(a + pas / 2, maillage[0] + pas / 2, f) - (a/pas - b) * u_r;
//            tabf[maillage.length-1] = Integration.gaussLegrend(a, b, f) + (a / pas) * u_s;
//
//            //initialisation
//            alpha[0] = 2*a/pas + b + c;
//            beta[0] = tabf[0]/alpha[0];
//
//            //iteration
//            for (int i = 1; i < maillage.length; i++)
//            {
//                alpha[i] = (2*a/pas + b + c) - ( ( (-a/pas) * (a/pas - b) ) / alpha[i-1]);
//                beta[i] = (tabf[i] + (a/pas) * beta[i-1]) / alpha[i];
//            }
//
//            //solution finale x
//            double u[] = new double[maillage.length];
//            u[maillage.length - 1] = beta[maillage.length - 1];
//            for (int i = maillage.length-2; i >= 0; i--)
//            {
//                u[i] = beta[i] + ( (a/pas) * u[i+1]/alpha[i] );
//            }
//            return u;
//        }
//        return null;
//    }

    @Override
    public double[] volumesFinis(int n, double u_debut, double u_fin, Fonction f) 
    {
        double[] maillage = null;
        maillage = subdivision(0, 1, n);
        
        if(maillage != null)
        {
            double tabf[] = new double[maillage.length];
            double beta[] = new double[maillage.length];
            double alpha[] = new double[maillage.length];

            //calcul du pas
            double pas = 1.0/n;
            
            
            System.out.println(" pas/2 = " + (pas/2) +"  I(f) = "+ Integration.gaussLegrend( 0, pas / 2, f));
            for (int i = 1; i<maillage.length - 1; i++)
            {
                tabf[i] = Integration.gaussLegrend((maillage[i] + maillage[i-1]) / 2, (maillage[i+1] + maillage[i]) / 2, f) * pas  ;//f.calcul(maillage[i]) * pas *pas;
                System.out.print(tabf[i] + "  ");
            }
            
            tabf[0] += u_debut;
            tabf[maillage.length-1] += u_fin;

            System.out.print(tabf[0] + "  ");
            //initialisation
            alpha[0] = 2;
            beta[0] = tabf[0]/alpha[0];

            //iteration
            for (int i = 1; i < maillage.length; i++)
            {
                alpha[i] = 2 - ( 1 / alpha[i-1]);
                beta[i] = (tabf[i] +  beta[i-1]) / alpha[i];
            }

            //solution finale x
            double u[] = new double[maillage.length];
            u[maillage.length - 1] = beta[maillage.length - 1];
            for (int i = maillage.length-2; i >= 0; i--)
            {
                u[i] = beta[i] + ( u[i+1]/ alpha[i] );
            }
            return u;
        }
        return null;
    }
    
    
    
}
