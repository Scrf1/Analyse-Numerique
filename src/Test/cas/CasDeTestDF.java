/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test.cas;

import interfaces.Fonction;

/**
 *
 * @author SCrf1
 */
public class CasDeTestDF 
{
    private Fonction f;
    private Fonction c;
    private double a;
    private double b;
    private double u_a;
    private double u_b;
    private int n;
    private Fonction resultatAttendu;

    public CasDeTestDF(Fonction f, Fonction c, double a, double b, double u_a, double u_b, int n, Fonction resultatAttendu) {
        this.f = f;
        this.c = c;
        this.a = a;
        this.b = b;
        this.u_a = u_a;
        this.u_b = u_b;
        this.n = n;
        this.resultatAttendu = resultatAttendu;
    }
    
   
    
    
    public Fonction getF() {
        return f;
    }

    public void setF(Fonction f) {
        this.f = f;
    }

    public Fonction getC() {
        return c;
    }

    public void setC(Fonction c) {
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getU_a() {
        return u_a;
    }

    public void setU_a(double u_a) {
        this.u_a = u_a;
    }

    public double getU_b() {
        return u_b;
    }

    public void setU_b(double u_b) {
        this.u_b = u_b;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public Fonction getResultatAttendu() {
        return resultatAttendu;
    }

    public void setResultatAttendu(Fonction resultatAttendu) {
        this.resultatAttendu = resultatAttendu;
    }
    
    
    
}
