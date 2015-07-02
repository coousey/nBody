package nbody.pkg.drawers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import nbody.pkg.model.Entity;
import nbody.pkg.model.Particle;

public class VectorParticleDrawer extends SimpleParticleDrawer {

    @Override
    public void draw(ArrayList<Entity> entityList, Graphics2D dImgG2D) {
        
        super.draw(entityList, dImgG2D);    // particle dot
        
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
