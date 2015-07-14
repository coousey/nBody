package nbody.pkg;

import java.util.HashMap;
import nbody.pkg.accelerationCalculators.AccelerationCalculator;
import nbody.pkg.accelerationCalculators.BruteForceAccCalc;
import nbody.pkg.accelerationCalculators.QTreeAccCalc;

public class AccelerationCalcFactory {
   
    private static HashMap<String, AccelerationCalculator> map = new HashMap<String, AccelerationCalculator>(){
            { put("brute force", new BruteForceAccCalc()); }
            { put("barnes-hut", new QTreeAccCalc()); }
        };
    
    public static AccelerationCalculator getAccCalc(String accCalcName){
        return map.get(accCalcName);
    }   
}