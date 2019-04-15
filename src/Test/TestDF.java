/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import classes.DF;
import com.mdalsoft.test.TestableAdapter;
import interfaces.Fonction;
import java.util.Map;

/**
 *
 * @author SCrf1
 */
public class TestDF extends TestableAdapter
{

    @Override
    public void test(Map testParams)
    {
        DF df = new DF();
        Fonction c = (Fonction) testParams.get("c");
        Fonction f = (Fonction) testParams.get("f");
        Double a = (Double) testParams.get("a");
        Double b = (Double) testParams.get("b");
        Double u_a = (Double) testParams.get("u_a");
        Double u_b = (Double) testParams.get("u_b");
        int longueur_maillage = (int) testParams.get("longueur_maillage");
        
        double resultatFonction[] = df.differencesFinies(a, b, longueur_maillage, f, c, u_a, u_b);
        testParams.put("resultatFonction", resultatFonction);
    }
    
    public static boolean fonctionDeTest(Fonction c, Fonction f, double a, double b, double u_a, double u_b, int longueur_maillage, double[] resultat_attendu)
    {
        DF fonction = new DF();
        
        double resultatFonction[] = fonction.differencesFinies(a, b, longueur_maillage, f, c, u_a, u_b);
        double erreur_absolue = 0.00, norme_attendu = 0.00;
        double e_h = 0.00, valeur_absolue;
        
        if(resultatFonction == null && resultat_attendu == null)
            return true;
        else if (resultatFonction == null && resultat_attendu != null)
            return false;
        else if (resultatFonction != null && resultat_attendu == null)
            return false;
        else{
            for(int i = 0; i < longueur_maillage - 1; i++){
                erreur_absolue = erreur_absolue + Math.pow(resultatFonction[i] - resultat_attendu[i],2);
                norme_attendu = norme_attendu + Math.pow(resultat_attendu[i], 2);
                
                if(resultatFonction[i] < resultat_attendu[i])
                    valeur_absolue = resultat_attendu[i] - resultatFonction[i];
                else
                    valeur_absolue = resultatFonction[i] - resultat_attendu[i];
                
                if(e_h < valeur_absolue)
                    e_h = valeur_absolue;
            }
            System.out.println(e_h + "   " + (b-a)/longueur_maillage);
            if(Math.sqrt(norme_attendu) != 0.00)
                return Math.sqrt(erreur_absolue)/Math.sqrt(norme_attendu) < Math.pow(10, -8);
            else 
                return Math.sqrt(erreur_absolue) < Math.pow(10, -8);
        }
    }
    
}
