package nbody.pkg.drawers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import nbody.pkg.model.Entity;

public class VectorFieldDrawer extends EntityDrawer {  

    @Override
    public void draw(ArrayList<Entity> entityList, ArrayList<Entity> fieldPointList, Graphics2D dImgG2D, Graphics2D sImgG2D) {
        
        dImgG2D.setColor(Color.RED);
        
        for(Entity e: fieldPointList){
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
        
        super.draw(entityList, fieldPointList, dImgG2D, sImgG2D);
    }
}
