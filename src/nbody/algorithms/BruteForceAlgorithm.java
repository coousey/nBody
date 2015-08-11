package nBody.algorithms;

import java.util.ArrayList;

import nBody.model.Point;
import nBody.model.World;

public class BruteForceAlgorithm implements Algorithm {

    @Override
    public void CalculateAcceleration(ArrayList<Point> pointList, ArrayList<Point> pointList2, double minX, double minY, double maxX, double maxY, double theta, double G) {
        
        double xD; 
        double yD;
        double f;
        double d;
            
        for(Point p: pointList){

            p.setXA(0);
            p.setYA(0);

            for(Point p2: pointList2){

                xD = p2.getX() - p.getX(); 
                yD = p2.getY() - p.getY();

                d = Math.sqrt(xD*xD + yD*yD)/ World.scale ;  

                if(d < p.getR() + p2.getR()) {               // if the distance is smaler than (r + p.r) the the force is proportional to the distance
                    d = p.getR() + p2.getR();
                    f = ((d/(p.getR() + p2.getR()))*G*p2.getM()) / Math.pow(d, 3); 
                }   
                else f = (G*p2.getM()) / Math.pow(d, 3);

                p.setXA(p.getXA() + f*xD);
                p.setYA(p.getYA() + f*yD);  
            }
        }
    }    
}

