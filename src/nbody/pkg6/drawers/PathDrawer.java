package nbody.pkg6.drawers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import nbody.pkg6.Entity;

public class PathDrawer implements EntityDrawer{

    @Override
    public void draw(ArrayList<Entity> entityList, Graphics2D sImgG2D) {

        sImgG2D.setColor(Color.GRAY);
        
        for(Entity e: entityList)
            sImgG2D.fillOval((int)(e.getX()), (int)(e.getY()), 1, 1);
    }    
}
