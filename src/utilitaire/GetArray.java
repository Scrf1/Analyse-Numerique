/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitaire;

import java.util.ArrayList;


/**
 *
 * @author dell
 */
public class GetArray {
    private double[] x;
    private int n;
    private int m;
    private double[] i;
    private double[] j;
    private ArrayList<utilitaire.Position> pos;
    
    public GetArray(double[] x1){
        this.x = x1;
    }
    
    public GetArray(double[] x1, ArrayList<utilitaire.Position> po, int n,int m){
        this.x = x1;
        this.pos = po;
        this.i = new double[pos.size()];
        this.j = new double[pos.size()];
        this.n = n;
        this.m = m;
        for (int ji=0; ji<this.pos.size(); ji++){
            this.j[ji] = (1.0)* pos.get(ji).getJ()/pos.size();
            this.i[ji] = (1.0)* pos.get(ji).getI()/pos.size();
            //System.out.println("It is "+(1.0)*pos.get(ji).getI()/pos.size());
        }
        
    }
    
    public int getM(){
        return m;
    }
    
    public int getN(){
        return n;
    }
    public double[] getI(){
        return i;
    }
    
     public double[] getJ(){
        return j;
    }
    public double[] getX(){
        return x;
    }
    public void printX(){
         for(int i=0;i<x.length;i++)
            System.out.println("X["+i+"] = "+x[i]);
    }
    
 
}
