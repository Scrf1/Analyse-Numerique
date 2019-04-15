/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import static Test.DFTestLogger.classeATester;
import static com.mdalsoft.test.DefaultTestLogger.logTest;
import com.mdalsoft.test.Testable;
import interfaces.Fonction;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SCrf1
 */
public class VFTestLogger 
{
    String sep = "\\";
    String testFileLogger ="com.mdalsoft.test.FileTestLogger";
    String dbTestLogger ="com.mdalsoft.test.DbTestLogger";  
    String firstMethodToTest = "Test.DFTestLogger.testMonCas";
    static String classeATester = "Test.TestVF";
    
//========================================================================================
//================              MAIN           ===========================================
//========================================================================================
    
    public static void main(String[] args) throws Exception
    {
        long startTime = System.currentTimeMillis();
        Map parTest = new HashMap();
        String methodToTest= "Test.VFTestLogger.main";
        String testFileLogger= "com.mdalsoft.test.FileTestLogger";
        try 
        {
            parTest.put("classtotest", classeATester);
            parTest.put("teststarttime", "" + startTime);
            parTest.put("testreference", methodToTest); 
            parTest.put("testLogger0", testFileLogger);    
            logTest(parTest, "start", false);
            DFTestLogger mtti = new DFTestLogger();
            parTest.put("testobjet", mtti);
            boolean rt = mtti.testMonCas();
            parTest.put("testresult", rt);
           
            System.out.println("Situation du test " + rt);
            Logger.global.log(Level.INFO, "Situation du test {0}", rt);
        }
        catch(Throwable exx)
        {
            //mettre un point d'arrêt à l'instruction printstacktrace suivante                            
            exx.printStackTrace();
            parTest.put("testresult",false);
            parTest.put("testerror", exx);
        }
        finally
        {
            parTest.put("testendtime", "" + System.currentTimeMillis());
            logTest(parTest, "end", true);
            System.exit(0);			 
        }
        
        // fin de la méthode main
    }
    
//========================================================================================
//============== LES AUTRES METHODES =====================================================
//========================================================================================

    public boolean testMonCas() throws Exception
    {
        
        Map paramLogTest = initParamTest(System.currentTimeMillis(), firstMethodToTest, null);
        logTest(paramLogTest, "start", false);
        boolean testResult = false;
        try
        {
            boolean threaded = true;
            boolean exportNewTestDataBeforeProcess = true;
            boolean loadTestData = true;           
            boolean testDataLoaded = false;
            boolean useDbUnit = false;
            boolean exportMasterData = false;
             
            Map testParams = new HashMap();  
            Object testData = null;
            prepareChecking(testParams);
            Testable tet = (Testable) Class.forName(classeATester).newInstance();
            testParams.put("testedobject",tet);
            tet.test(testParams);
            testResult = checkResult(testParams); 
        }
        catch(Throwable th)
        {
            paramLogTest.put("testerror", th);
            throw th;                           
        }
        finally
        {
            paramLogTest.put("testresult", testResult);
            paramLogTest.put("testendtime", "" + System.currentTimeMillis());
            logTest(paramLogTest, "end", false);                    
        }
        return testResult;
    
        //Fin de la méthode test mon cas
    }
    
    private void prepareChecking(Map testParams) throws Exception
    {
        Fonction f = (double x)->Math.sin(x);
        testParams.put("f", f);
        testParams.put("a", 0.);
        testParams.put("b", 1.);
        testParams.put("u_a", 0.);
        testParams.put("u_b", 0.);
        testParams.put("longueur_maillage", 100);
    }
    
    private  boolean checkResult(Map testParams) throws Exception 
    {
        double resultatFonction[] = (double[]) testParams.get("resultatFonction");
        int longueur_maillage = (int) testParams.get("longueur_maillage");
        double a = (double) testParams.get("a");
        double b = (double) testParams.get("b");
        double erreur_absolue = 0.00, norme_attendu = 0.00;
        double e_h = 0.00, valeur_absolue;
        
    // ************************************************************************************** 
    //********      Résultat attendu    *****************************************************
    
        double[] resultat_attendu = new double[resultatFonction.length];
        for(int i = 0; i < resultat_attendu.length; i++)
        {
            resultat_attendu[i] = Math.sin(a + i * (b-a) / longueur_maillage);
        }
        
    //***************************************************************************************    
        
        
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
            System.out.println(e_h + "   " + (b - a) / longueur_maillage);
            if(Math.sqrt(norme_attendu) != 0.00)
                return Math.sqrt(erreur_absolue)/Math.sqrt(norme_attendu) < Math.pow(10, -8);
            else 
                return Math.sqrt(erreur_absolue) < Math.pow(10, -8);
        }
        
    }

   
    private Map initParamTest(long startTime, String firstMethodToTest, Object o) 
    {
        Map parTest = new HashMap(); 
        parTest.put("teststarttime", "" + startTime);
        parTest.put("testreference", firstMethodToTest); 
        parTest.put("testLogger0", testFileLogger); 
        if(o != null) 
            parTest.put("lfm", o);
        parTest.put("testobjet", this);
        return parTest; 
    }
}
