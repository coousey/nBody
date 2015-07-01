package nbody.pkg6.drawers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import nbody.pkg6.Entity;

public class VectorEntityDrawer extends StandardEntityDrawer {

    @Override
    public void draw(ArrayList<Entity> entityList, Graphics2D dImgG2D) {
        
        super.draw(entityList, dImgG2D);    // particle dot
        
        for(Entity e: entityList){
            dImgG2D.setColor(Color.BLUE);
            dImgG2D.drawLine((int)e.getX(), (int)e.getY(),
                    (int)(e.getX()+e.getXV()*10), (int)(e.getY()+e.getYV()*10)); // velocity vector    
            dImgG2D.setColor(Color.RED);
            dImgG2D.drawLine((int)e.getX(), (int)e.getY(),
                    (int)(e.getX()+e.getXA()*100), (int)(e.getY()+e.getYA()*100)); // acceleration vector
        }        
    }   
}
