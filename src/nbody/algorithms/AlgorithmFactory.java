package nBody.algorithms;

import java.util.HashMap;

import nBody.algorithms.Algorithm;
import nBody.algorithms.BarnesHutAlgorithm;
import nBody.algorithms.BruteForceAlgorithm;

public class AlgorithmFactory {
   
    private static HashMap<String, Algorithm> map = new HashMap<String, Algorithm>(){
        
            { put("brute force", new BruteForceAlgorithm()); }
            { put("barnes-hut", new BarnesHutAlgorithm()); }
        };
    
    public static Algorithm getAlgorithm(String algorithmName) {
        return map.get(algorithmName);
    }   
}
