package nbody.pkg.drawers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import nbody.pkg.model.Entity;
import nbody.pkg.model.Particle;

public class VectorParticleDrawer extends EntityDrawer {

    @Override
    public void draw(ArrayList<Entity> entityList, ArrayList<Entity> fieldPointList, Graphics2D dImgG2D, Graphics2D sImgG2D) {
        
        super.draw(entityList, fieldPointList, dImgG2D, sImgG2D);    // particle dot
        
        for(Entity e: entityList){
            Particle p = (Particle)e;
            
            dImgG2D.setColor(Color.BLUE);
            dImgG2D.drawLine((int)p.getX(), (int)p.getY(),
                    (int)(p.getX()+p.getXV()*10), (int)(p.getY()+p.getYV()*10)); // velocity vector    
            dImgG2D.setColor(Color.RED);
            dImgG2D.drawLine((int)p.getX(), (int)p.getY(),
                    (int)(p.getX()+p.getXA()*100), (int)(p.getY()+p.getYA()*100)); // acceleration vector
        }        
    }   
}
