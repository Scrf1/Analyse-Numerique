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
public interface IVF extends Subdivision
{
    double[] volumesFinis(int n, double u_debut, double u_fin ,Fonction f);
}
