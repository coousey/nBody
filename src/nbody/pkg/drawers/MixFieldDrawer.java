package nbody.pkg.drawers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import nbody.pkg.model.Entity;

public class MixFieldDrawer implements EntityDrawer{

    @Override
    public void draw(ArrayList<Entity> entityList, Graphics2D dImgG2D) {
        
        for(Entity e: entityList){
            double xA = e.getXA() * 1000;
            double yA = e.getYA() * 1000;

            double a = Math.sqrt(xA*xA + yA*yA); // for acceleration to color convertion

            Color color;

            if(a <= 255) color = new Color((int)(a),0,0);
            else if(a > 255 && a <= 510) color = new Color(255,(int)(a - 255),0);
            else if(a > 510 && a <= 765) color = new Color(255,255,(int)(a - 510));
            else color = new Color(255,255,255);

            double max = e.getWorld().getCellD()*0.3; // max vector length

            if(xA > max) xA = max;
            else if(xA < -max) xA = -max;
            if(yA > max) yA = max;
            else if(yA < -max) yA = -max;

            dImgG2D.setColor(color);
            dImgG2D.drawLine((int)e.getX(), (int)e.getY(),
                    (int)Math.round(e.getX()+xA), (int)Math.round(e.getY()+yA)); // field lines 
        }
    }   
}
