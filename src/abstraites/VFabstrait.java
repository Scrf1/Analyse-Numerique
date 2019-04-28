/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstraites;

import interfaces.Fonction;
import interfaces.IVF;

/**
 *
 * @author SCrf1
 */
public abstract class VFabstrait implements IVF
{
    @Override
    public abstract double[] volumesFinis(int n, double u_debut, double u_fin, Fonction f);
   
}
