package nbody.pkg.accelerationCalculators;

import java.util.ArrayList;
import nbody.pkg.model.Entity;
import nbody.pkg.model.World;

public class QTreeAccCalc implements AccelerationCalculator{
       
    @Override
    public void CalculateAcceleration(ArrayList<Entity> entityList, ArrayList<Entity> entityList2, double minX, double minY, double maxX, double maxY, double theta, double G) {
        
        QTree qTree = new QTree(minX, minY, maxX, maxY);
        
        for(Entity e2: entityList2){
            qTree.insertParticle(qTree.getRoot(), e2);
        }
        for(Entity e: entityList){
            e.setXA(0);
            e.setYA(0);
            this.calcAcceleration(qTree.getRoot(), e, World.scale, theta, G); 
        }
    }
    
    public void calcAcceleration(QTreeNode node,Entity e, double scale, double theta, double G){
        
        double xD; 
        double yD;
        double d;                
        double f;
        
        if(node.getNW() == null && node.getEntity() != null && node.getEntity() != e){   // we cant go deeper. calculating for this node 

            xD = node.getMassCenterX() - e.getX(); 
            yD = node.getMassCenterY() - e.getY();
            d = Math.sqrt(xD*xD + yD*yD)/ scale ; 
            
            if(d < e.getR() + node.getEntity().getR()) {       // if the distance is smaler than (r + p.r) the the force is proportional to the distance 
                d = e.getR() + node.getEntity().getR();
                f = ((d/(e.getR() + node.getEntity().getR()))*G*node.getEntity().getM()) / Math.pow(d, 3); 
            }   
            else f = (G*node.getM()) / Math.pow(d, 3);
            
            e.setXA(e.getXA() + f*xD);
            e.setYA(e.getYA() + f*yD);
        }
        
        else if(node.getNW() != null ){     // we can go deeper so lets chack width to distance ratio
            
            xD = node.getMassCenterX() - e.getX(); 
            yD = node.getMassCenterY() - e.getY();
            d = Math.sqrt(xD*xD + yD*yD)/ scale ; 
            
            if(node.getW()/d < theta){      // area is sufficient far away
                
                f = node.getM() / Math.pow(d, 3);
            
                e.setXA(e.getXA() + f*xD);
                e.setYA(e.getYA() + f*yD);
            }
            else {      // area is to close. Go one level deeper 
                
                calcAcceleration(node.getNW(),e, scale, theta, G);
                calcAcceleration(node.getNE(),e, scale, theta, G);
                calcAcceleration(node.getSW(),e, scale, theta, G);
                calcAcceleration(node.getSE(),e, scale, theta, G);
            }
        }   
    }
}
