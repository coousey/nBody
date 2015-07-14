package nbody.pkg.drawers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import nbody.pkg.model.Entity;
import nbody.pkg.model.Particle;

public class EntityDrawer {
    
    public void draw(ArrayList<Entity> entityList, ArrayList<Entity> fieldPointList, Graphics2D dImgG2D, Graphics2D sImgG2D) {
          
        for(Entity e: entityList){
            Particle p = (Particle)e;
            
            dImgG2D.setColor(Color.BLUE);
            dImgG2D.fillOval((int)(p.getX()-p.getR()), (int)(p.getY()-p.getR()), // particle dot
                    (int)(p.getR()*2), (int)(p.getR()*2));
        }
    }
}
