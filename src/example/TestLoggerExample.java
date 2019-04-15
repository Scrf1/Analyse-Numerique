package example;


/**
 * Titre :        Your Product Name
 * Description :  Your description
 * Copyright :    Copyright (c) 1998
 * Soci t  :      lamas
 * @author I.Moukouop
 * @version
 */
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mdalsoft.test.DefaultTestLogger.logTest;
import com.mdalsoft.test.TestLogger;
import com.mdalsoft.test.Testable;

import java.io.File;
import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author ibrahim
 */
public class TestLoggerExample {
    //String  baseTestPath="c:\\moukouop\\moukouop\\mdalerp";
    String sep="\\";
    String testFileLogger="com.mdalsoft.test.FileTestLogger";
    String dbTestLogger="com.mdalsoft.test.DbTestLogger";  
    /**
     * replacer par le nom de la methode testee, sous le format correspondant à son nom complet
     * le plus souvent, si vous n'avez pas changé l'appel à testMonCas, il faudra juste changer le nom de la classe
     */
    String firstMethodToTest= "TestLoggerExample.testMonCas";
     /**
     * replacer par le nom de la classe testee, sous le format correspondant à son nom complet
     */
    static String classeATester= "example.TestDouble";
    
    public static void main(String[] args) throws Exception
    {
         //18mn pour preparer le programme minimal de test
        //Double d=new Double(1.020010017E9);
        //System.out.println("Mon test "+System.currentTimeMillis());
       // System.exit(0);
        //DataDescriptor dd=new DataDescriptor("jdbc:odbc:nipma","","", "sun.jdbc.odbc.JdbcOdbcDriver");
        long startTime=System.currentTimeMillis();
        Map parTest=new HashMap();
        String methodToTest= "TestLoggerExample.main";//ici, remplacer TestLoggerExample par le nom de la classe de ce fichier
        String testFileLogger= "com.mdalsoft.test.FileTestLogger";
        try {
            parTest.put("classtotest",classeATester);
            parTest.put("teststarttime",""+startTime);
             //nomclasse.nommethode testee et eventuellement les signatures de paramètres s'il y a plusieurs methodes de meme nom
            //dans le respect des conventions des servicelocator
            parTest.put("testreference",methodToTest); 
            parTest.put("testLogger0",testFileLogger);    
            logTest(parTest,"start",false);
            
             
           
            
            TestLoggerExample mtti=new TestLoggerExample();
            parTest.put("testobjet",mtti);
            
            /**
             * il est souhaitable de ne rien changer  ici. Pour tester un autre cas, créer une autre classe de test.
             */
            boolean rt=mtti.testMonCas();
            parTest.put("testresult",rt);
           
            System.out.println("Situation du test "+rt);
            Logger.global.info("Situation du test "+rt);
           // Messager.messageDlg("Alerte", "Situation du test "+rt, Message.OK) ;         //mtti.test(lfm);
            }catch(Throwable exx){
                            //mettre un point d'arrêt à l'instruction printstacktrace suivante                            
                              exx.printStackTrace();
                              parTest.put("testresult",false);
                              parTest.put("testerror", exx);
                             }
                         finally{
                            parTest.put("testendtime",""+System.currentTimeMillis());
                            logTest(parTest,"end",true);
                            //if(lfm!=null) lfm.closeSession();
                              //si vous souhaitez couperle programme, vous vous faire System.exit(0), après avoir proprement libere les ressources
                            System.exit(0);			 
                            }
        }
    
        public boolean testMonCas() throws Exception
        {
            //on initialise pour le log
            Map paramLogTest=initParamTest(System.currentTimeMillis(),firstMethodToTest,null);
            logTest(paramLogTest, "start", false);
            boolean testResult=false;
            try{
                //passer cette variable à false si on n'utilise pas de threads dasns le traitement
                boolean threaded=true;
                //passer cette variable à fale si on ne souhaite pas exporter les données créées lors du test avant de réaliser les tests
                boolean exportNewTestDataBeforeProcess=true;
                boolean loadTestData=true;           
                boolean testDataLoaded=false;
                boolean useDbUnit=false;
                boolean exportMasterData=false;
             
                  //les paramètres des tests
                Map testParams=new HashMap();         
                //chargement des données
               // DataSetMap testdata=null;
               Object testData=null;

            

                //remplacer par le chemin du fichier de données de tests à charger 
                //s'il en existe un, sinon commenter
                 prepareChecking(testParams);
                //on realise le traitement
                Testable tet=(Testable)Class.forName(classeATester).newInstance();
                testParams.put("testedobject",tet);
                /**
                 * se contente d'executer la methode à tester, et à remplir testParams avec les éléments qui permettront de verifier le resultat
                 */
                tet.test(testParams);
                testResult=checkResult(testParams); 
                }catch(Throwable th){
                      paramLogTest.put("testerror", th);
                      throw th;                           
                     }finally{
                            paramLogTest.put("testresult",testResult);
                            paramLogTest.put("testendtime",""+System.currentTimeMillis());
                            logTest(paramLogTest, "end", false);                    
                            }
            return testResult;
            }
   /**
   * mt dans le map  testParams tout ce qui sera plus tard necessaire pour les oracles de test
   */
    private void prepareChecking(Map testParams) throws Exception{
         /*
        * ajoute au map les paramètres nécessaies au programme qui sera testé. Le testeur et le developpeur s'entendront sur les noms souhaités
        * cette méthode est redefinie au cas par cas.
        */
            testParams.put("entry", new Double(10));
           }
/**
 * verifie les oracles de test
 * @param testParams
 * @return true si ok et false sinon
 * @throws Exception 
 */
    private  boolean checkResult( Map testParams) throws Exception 
    {
	//attention àce code qui compare deux réels en utilisant l'egalité
           return ((Double)testParams.get("calulatedsolution")).equals(new Double(2));
    }

   
    private Map initParamTest(long startTime, String firstMethodToTest, Object o) 
    {
        Map parTest=new HashMap(); 
        parTest.put("teststarttime",""+startTime);
             //nomclasse.nommethode testee et eventuellement les signatures de paramètres s'il y a plusieurs methodes de meme nom
            //dans le respect des conventions des servicelocator
        parTest.put("testreference",firstMethodToTest); 
        parTest.put("testLogger0",testFileLogger); 
        if(o!=null) parTest.put("lfm",o);
        parTest.put("testobjet",this);
        return parTest; 
        }
 
    
    
}







