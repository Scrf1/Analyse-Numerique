/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analysenumerique2;

import classes.DF;
import interfaces.Fonction;

/**
 *
 * @author SCrf1
 */
public class AnalyseNumerique2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        DF df=new DF();
        Fonction f=(double x)->1;
        Fonction c=(double x)->0;

        double u[]=df.differencesFinies(0,10,1000,f,c,0,1);
        for (double d:u)
            System.out.println(d);
    }
    
}
