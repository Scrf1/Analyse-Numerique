/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.matrice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SCrf1
 */
public class MatriceCreuse 
{
    private HashMap<String, Valeur> vals;
    private int nbreLignes;
    private int nbreColonnes;
    
    public MatriceCreuse()
    {
        this.vals = new HashMap<>();
    }

    public MatriceCreuse(int nbreLignes, int nbreColonnes) 
    {
        this.nbreLignes = nbreLignes;
        this.nbreColonnes = nbreColonnes;
        this.vals = new HashMap<>();
    }
    
    public double get(int i, int j)
    {
        if(vals.containsKey(Integer.toString(i) + "," + Integer.toString(j)))
            return (double) vals.get(Integer.toString(i) + "," + Integer.toString(j)).getVal();
        else
            return 0;
    }
    
    public void set(int i, int j, double x)
    {
        if(!vals.containsKey(Integer.toString(i) + "," + Integer.toString(j)))
            vals.put(Integer.toString(i) + "," + Integer.toString(j),
                    new Valeur(x, i, j));
        else
            vals.replace(Integer.toString(i) + "," + Integer.toString(j), new Valeur(x,i,j));
    }   
    
    public double[] matriceVecteur(ArrayList<Double> vecteur)
    {
        double[] resultat = new double[this.getNbreLignes()];
        for (int i = 0; i < resultat.length; i++) 
        {
            resultat[i] = 0;
        }
        
        for (int i = 0; i < resultat.length; i++) 
        {
            for (int j = 0; j < vecteur.size(); j++) 
            {
                resultat[i] += this.get(i, j) * vecteur.get(j);
            }
        }
        return resultat;
    }
    
    public double[] matriceVecteur(double[] vecteur)
    {
        double[] resultat = new double[this.getNbreLignes()];
        for (int i = 0; i < resultat.length; i++) 
        {
            resultat[i] = 0;
        }
        
        for (int i = 0; i < resultat.length; i++) 
        {
            for (int j = 0; j < vecteur.length; j++) 
            {
                resultat[i] += this.get(i, j) * vecteur[j];
            }
        }
        return resultat;
    }
    
    public void print()
    {
        for (Map.Entry<String, Valeur> entry : vals.entrySet()) 
        {
            String key = entry.getKey();
            Valeur value = entry.getValue();
            System.out.println(key + " => " + value.getVal());
        } 
    }

    public int getNbreLignes() {
        return nbreLignes;
    }

    public int getNbreColonnes() {
        return nbreColonnes;
    }

    /**
     * @param nbreLignes the nbreLignes to set
     */
    public void setNbreLignes(int nbreLignes) {
        this.nbreLignes = nbreLignes;
    }

    /**
     * @param nbreColonnes the nbreColonnes to set
     */
    public void setNbreColonnes(int nbreColonnes) {
        this.nbreColonnes = nbreColonnes;
    }
    
    
}
