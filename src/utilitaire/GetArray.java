/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaire;

import java.util.ArrayList;
import py4j.GatewayServer;

/**
 *
 * @author dell
 */
public class GetArray {
    private double[] x;
    private double[] i;
    private double[] j;
    private ArrayList<utilitaire.Position> pos;
    
    public GetArray(double[] x1){
        this.x = x1;
    }
    
    public GetArray(double[] x1, ArrayList<utilitaire.Position> po){
        this.x = x1;
        this.pos = po;
        this.i = new double[pos.size()];
        this.j = new double[pos.size()];
        for (int ji=0; ji<this.pos.size(); ji++){
            this.j[ji] = pos.get(ji).getJ();
            this.i[ji] = pos.get(ji).getI();
            System.out.println(pos.get(ji).getI());
        }
        
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
