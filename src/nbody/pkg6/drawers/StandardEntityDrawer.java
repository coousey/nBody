package nbody.pkg6.drawers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import nbody.pkg6.Entity;

public class StandardEntityDrawer implements EntityDrawer {
    
    @Override
    public void draw(ArrayList<Entity> entityList, Graphics2D dImgG2D) {
        
        dImgG2D.setColor(Color.BLUE);
        
        for(Entity e: entityList){
            dImgG2D.fillOval((int)(e.getX()-e.getR()), (int)(e.getY()-e.getR()), // particle dot
                    (int)(e.getR()*2), (int)(e.getR()*2));

            if(e.isSelected())
                dImgG2D.drawOval((int)(e.getX()-2*e.getR()), (int)(e.getY()-2*e.getR()), // circle surroundig the particle dot
                        (int)(2*(e.getR()*2)), (int)(2*(e.getR()*2))); 
        }
    }
}
