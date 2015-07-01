package nbody.pkg6.drawers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import nbody.pkg6.Entity;

public class VectorFieldDrawer implements EntityDrawer {  

    @Override
    public void draw(ArrayList<Entity> entityList, Graphics2D dImgG2D) {
        
        dImgG2D.setColor(Color.RED);
        
        for(Entity e: entityList){
            double xA = e.getXA() * 200;
            double yA = e.getYA() * 200;

            double max = e.getWorld().getCellD()*0.3;   // maximal vector length

            if(xA > max) xA = max;
            else if(xA < -max) xA = -max;
            if(yA > max) yA = max;
            else if(yA < -max) yA = -max;

            dImgG2D.drawLine((int)e.getX(), (int)e.getY(),
                    (int)Math.round(e.getX()+xA), (int)Math.round(e.getY()+yA)); // field lines
        }
    }
}
