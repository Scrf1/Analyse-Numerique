/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Test.cas.CasDeTestDF;
import classes.DF;
import static com.mdalsoft.test.DefaultTestLogger.logTest;
import com.mdalsoft.test.Testable;
import com.mdalsoft.test.TestableAdapter;
import example.TestLoggerExample;
import interfaces.Fonction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SCrf1
 */
public class DFTestLogger 
{
    String sep = "\\";
    String testFileLogger ="com.mdalsoft.test.FileTestLogger";
    String dbTestLogger ="com.mdalsoft.test.DbTestLogger";  
    String firstMethodToTest = "Test.DFTestLogger.testsDifferencesFinies";
    static String classeATester = "Test.TestDF";
    
//========================================================================================
//================              MAIN           ===========================================
//========================================================================================
    
    public static void main(String[] args) throws Exception
    {
        long startTime = System.currentTimeMillis();
        Map parTest = new HashMap();
        String methodToTest= "Test.DFTestLogger.main";
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
            boolean rt = mtti.testsDifferencesFinies();
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

    public boolean testsDifferencesFinies() throws Exception
    {
        
        Map paramLogTest = initParamTest(System.currentTimeMillis(), firstMethodToTest, null);
        logTest(paramLogTest, "start", false);
        boolean testResult = false;
        
        //===========================================================================================
        //======================   Cas de test ======================================================
        
        CasDeTestDF[] casdetests = 
        {
            new CasDeTestDF((double x) -> x, (double x) -> 1, 0, 1, 0, 1, 100, (double x) -> x),
            new CasDeTestDF((double x) -> x*x - 2, (double x) -> 1, 0, 1, 0, 1, 100, (double x) -> x*x),
            new CasDeTestDF((double x) -> x*x*x - 2, (double x) -> x, 0, 1, 0., 1, 100, (double x) -> x*x),
            new CasDeTestDF((double x) -> 0, (double x) -> 0, 0, 1, 0., 0, 100, (double x) -> 0),
            new CasDeTestDF((double x) -> Math.cos(x), (double x) -> 0, 0, 1, 1, 0.5403023058681398, 100, (double x) -> Math.cos(x)),
            new CasDeTestDF((double x) -> 2 * Math.sin(x), (double x) -> 1, 0, 1, 0., 0.8414709848078965, 100, (double x) -> Math.sin(x)),
            new CasDeTestDF((double x) -> 1, (double x) -> 1, 0, 1, 1, 1, 100, (double x) -> 1)
        };
        
        try
        {
            boolean threaded = true;
            boolean exportNewTestDataBeforeProcess = true;
            boolean loadTestData = true;           
            boolean testDataLoaded = false;
            boolean useDbUnit = false;
            boolean exportMasterData = false;
             
            for (CasDeTestDF casDeTestDF : casdetests) 
            {
                Map testParams = new HashMap();  
                Object testData = null;
                prepareChecking(testParams, 
                        casDeTestDF.getF(), 
                        casDeTestDF.getC(),
                        casDeTestDF.getA(), 
                        casDeTestDF.getB(), 
                        casDeTestDF.getU_a(),
                        casDeTestDF.getU_b(),
                        casDeTestDF.getN());
                
                Testable tet = (Testable)Class.forName(classeATester).newInstance();
                testParams.put("testedobject",tet);
                tet.test(testParams);
                testResult = checkResult(testParams, casDeTestDF.getResultatAttendu()); 
            }
            
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
    
    
    private void prepareChecking(Map testParams, Fonction f, Fonction c, double a, double b, double u_a, double u_b, int n) throws Exception
    {
        
        testParams.put("c", c);
        testParams.put("f", f);
        testParams.put("a", a);
        testParams.put("b", b);
        testParams.put("u_a", u_a);
        testParams.put("u_b", u_b);
        testParams.put("longueur_maillage", n);
    }
    
    private  boolean checkResult(Map testParams, Fonction ra) throws Exception 
    {
        double resultatFonction[] = (double[]) testParams.get("resultatFonction");
        int longueur_maillage = (int) testParams.get("longueur_maillage");
        double a = (double) testParams.get("a");
        double b = (double) testParams.get("b");
        double tol = 1e-6;
        DF df = new DF();
        double[] subdivision = df.subdivision(a, b, longueur_maillage);
        
    // ************************************************************************************** 
    //********      Résultat attendu    *****************************************************
    
        double[] resultat_attendu = new double[resultatFonction.length];
        for(int i = 0; i < subdivision.length; i++)
        {
            resultat_attendu[i] = ra.calcul(subdivision[i]);
        }
        
    //***************************************************************************************    
        
        return checkExactitude(resultatFonction, resultat_attendu, longueur_maillage, a, b, tol);
        
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
    
    private boolean checkExactitude(double[] resultatFonction, double[] resultat_attendu, int longueur_maillage, double a, double b, double tol)
    {
        double erreur_absolue = 0.00;
        double norme_attendu = 0.00;
        double e_h = 0.00;
        double valeur_absolue;
        
        if(resultatFonction == null && resultat_attendu == null)
            return true;
        else if (resultatFonction == null && resultat_attendu != null)
            return false;
        else if (resultatFonction != null && resultat_attendu == null)
            return false;
        else
        {
              
            for(int i = 0; i < longueur_maillage - 1; i++)
            {
                erreur_absolue = erreur_absolue + Math.pow(resultatFonction[i] - resultat_attendu[i],2);
                norme_attendu = norme_attendu + Math.pow(resultat_attendu[i], 2);
                
                valeur_absolue = Math.abs(resultat_attendu[i] - resultatFonction[i]);
                
                if(e_h < valeur_absolue)
                    e_h = valeur_absolue;
            }
            System.out.print(e_h + "   " + (b-a)/longueur_maillage + "   ");
            if(Math.sqrt(norme_attendu) != 0.00)
            {
                System.out.println(Math.sqrt(erreur_absolue)/Math.sqrt(norme_attendu) < tol);
                return Math.sqrt(erreur_absolue)/Math.sqrt(norme_attendu) < tol;
            }
                
            else 
            {
                System.out.println(Math.sqrt(erreur_absolue) < tol);
                return Math.sqrt(erreur_absolue) < tol;
            }
                
        }
    }
    
}
