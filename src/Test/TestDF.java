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
 
}
