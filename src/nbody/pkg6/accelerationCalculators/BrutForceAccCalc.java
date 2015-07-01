package nbody.pkg6.accelerationCalculators;

import java.util.ArrayList;
import nbody.pkg6.Entity;
import nbody.pkg6.World;

public class BrutForceAccCalc implements AccelerationCalculator {

    @Override
    public void CalculateAcceleration(ArrayList<Entity> entityList, ArrayList<Entity> entityList2, double minX, double minY, double maxX, double maxY, double theta, double G) {
        
        double xD; 
        double yD;
        double f;
        double d;
            
        for(Entity e: entityList){

            e.setXA(0);
            e.setYA(0);

            for(Entity e2: entityList2){

                xD = e2.getX() - e.getX(); 
                yD = e2.getY() - e.getY();

                d = Math.sqrt(xD*xD + yD*yD)/ World.scale ;  

                if(d < e.getR() + e2.getR()) {               // if the distance is smaler than (r + p.r) the the force is proportional to the distance
                    d = e.getR() + e2.getR();
                    f = ((d/(e.getR() + e2.getR()))*G*e2.getM()) / Math.pow(d, 3); 
                }   
                else f = (G*e2.getM()) / Math.pow(d, 3);

                e.setXA(e.getXA() + f*xD);
                e.setYA(e.getYA() + f*yD);  
            }
        }
    }    
}
