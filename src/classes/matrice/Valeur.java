/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.matrice;

/**
 *
 * @author SCrf1
 */
public class Valeur
{
    private double val;
    private int i;
    private int j;

    public Valeur(double val, int i, int j) {
        this.val = val;
        this.i = i;
        this.j = j;
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
    
    
}
