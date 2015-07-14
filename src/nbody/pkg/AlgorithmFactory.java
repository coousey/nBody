package nbody.pkg;

import java.util.HashMap;
import nbody.pkg.accelerationCalculators.Algorithm;
import nbody.pkg.accelerationCalculators.BruteForceAccCalc;
import nbody.pkg.accelerationCalculators.BarnesHutAccCalc;

public class AlgorithmFactory {
   
    private static HashMap<String, Algorithm> map = new HashMap<String, Algorithm>(){
        
            { put("brute force", new BruteForceAccCalc()); }
            { put("barnes-hut", new BarnesHutAccCalc()); }
        };
    
    public static Algorithm getAlgorithm(String algorithmName) {
        return map.get(algorithmName);
    }   
}