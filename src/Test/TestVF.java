/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import classes.VF;
import com.mdalsoft.test.TestableAdapter;
import interfaces.Fonction;
import java.util.Map;

/**
 *
 * @author SCrf1
 */
public class TestVF extends TestableAdapter
{

    @Override
    public void test(Map testParams)
    {
        VF vf = new VF();
        Fonction f = (Fonction) testParams.get("f");
        Double u_a = (Double) testParams.get("u_a");
        Double u_b = (Double) testParams.get("u_b");
        int longueur_maillage = (int) testParams.get("longueur_maillage");
        
        double resultat[] = vf.volumesFinis(longueur_maillage, u_a, u_b, f);
        
        testParams.put("resultatFonction", resultat);
    }
   
    
}
