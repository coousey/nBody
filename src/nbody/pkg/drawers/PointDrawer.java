package nbody.pkg.drawers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import nbody.pkg.model.Point;
import nbody.pkg.model.Particle;

public class PointDrawer {
    
    public void draw(ArrayList<Point> particleList, ArrayList<Point> pointList, Graphics2D dImgG2D, Graphics2D sImgG2D) {
          
        for(Point po: particleList){
            Particle p = (Particle)po;
            
            dImgG2D.setColor(Color.BLUE);
            dImgG2D.fillOval((int)(p.getX()-p.getR()), (int)(p.getY()-p.getR()), // particle dot
                    (int)(p.getR()*2), (int)(p.getR()*2));
        }
    }
}
