/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import com.mdalsoft.test.TestableAdapter;
import java.util.Map;

/**
 *
 * @author ibrahim
 */
public class TestDouble extends TestableAdapter{
   /**
    * calcule le double de lentree et soumet le resultat pour test
    * @param testParams 
    */
    public void test(Map testParams) {
        Double d=(Double)testParams.get("entry");
        testParams.put("calulatedsolution",2*d);        
        }
    
    }
