package nbody.pkg.drawers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import nbody.pkg.model.Entity;

public class ColorFieldDrawer implements EntityDrawer{

    @Override
    public void draw(ArrayList<Entity> entityList, Graphics2D dImgG2D) {
        
        for(Entity e: entityList){
            double xA = e.getXA() * 1000;
            double yA = e.getYA() * 1000;

            int a = (int)Math.sqrt(xA*xA + yA*yA); // for acceleration to color convertion

            Color color;

            if(a <= 0 ) color = new Color(0,0,0);
            else if(a > 0 && a <= 153)color = new Color(0, 0,(5/3)*a);
            else if(a > 153 && a <= 306) color = new Color((int)((4/3)*(a-153)),0,255 );
            else if(a > 306 && a <= 459) color = new Color(255,0,(int)(255 -(5/3)*(a-306)) );
            else if(a > 459 && a <= 612) color = new Color(255,(int)((5/3)*(a-459)),0);
            else if(a > 612 && a <= 765) color = new Color(255,255,(int)((5/3)*(a-612)));
            else color = new Color(255,255,255);

            dImgG2D.setColor(color);

            dImgG2D.fillRect((int)(e.getX()-e.getWorld().getCellD()/2),(int)(e.getY()-e.getWorld().getCellD()/2),
                    e.getWorld().getCellD(), e.getWorld().getCellD()); // field rectangle
        }
    }    
}
