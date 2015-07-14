package nbody.pkg.accelerationCalculators;

import java.util.ArrayList;
import nbody.pkg.model.Point;
import nbody.pkg.model.World;

public class BarnesHutAccCalc implements Algorithm{
       
    @Override
    public void CalculateAcceleration(ArrayList<Point> pointList, ArrayList<Point> pointList2, double minX, double minY, double maxX, double maxY, double theta, double G) {
        
        QTree qTree = new QTree(minX, minY, maxX, maxY);
        
        for(Point p2: pointList2){
            qTree.insertPoint(qTree.getRoot(), p2);
        }
        for(Point p: pointList){
            p.setXA(0);
            p.setYA(0);
            this.calcAcceleration(qTree.getRoot(), p, World.scale, theta, G); 
        }
    }
    
    public void calcAcceleration(QTreeNode node,Point p, double scale, double theta, double G){
        
        double xD; 
        double yD;
        double d;                
        double f;
        
        if(node.getNW() == null && node.getPoint() != null && node.getPoint() != p){   // we cant go deeper. calculating for this node 

            xD = node.getMassCenterX() - p.getX(); 
            yD = node.getMassCenterY() - p.getY();
            d = Math.sqrt(xD*xD + yD*yD)/ scale ; 
            
            if(d < p.getR() + node.getPoint().getR()) {       // if the distance is smaler than (r + p.r) the the force is proportional to the distance 
                d = p.getR() + node.getPoint().getR();
                f = ((d/(p.getR() + node.getPoint().getR()))*G*node.getPoint().getM()) / Math.pow(d, 3); 
            }   
            else f = (G*node.getM()) / Math.pow(d, 3);
            
            p.setXA(p.getXA() + f*xD);
            p.setYA(p.getYA() + f*yD);
        }
        
        else if(node.getNW() != null ){     // we can go deeper so lets chack width to distance ratio
            
            xD = node.getMassCenterX() - p.getX(); 
            yD = node.getMassCenterY() - p.getY();
            d = Math.sqrt(xD*xD + yD*yD)/ scale ; 
            
            if(node.getW()/d < theta){      // area is sufficient far away
                
                f = node.getM() / Math.pow(d, 3);
            
                p.setXA(p.getXA() + f*xD);
                p.setYA(p.getYA() + f*yD);
            }
            else {      // area is to close. Go one level deeper 
                
                calcAcceleration(node.getNW(),p, scale, theta, G);
                calcAcceleration(node.getNE(),p, scale, theta, G);
                calcAcceleration(node.getSW(),p, scale, theta, G);
                calcAcceleration(node.getSE(),p, scale, theta, G);
            }
        }   
    }
}
