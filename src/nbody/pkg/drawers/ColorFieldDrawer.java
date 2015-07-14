package nbody.pkg.drawers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import nbody.pkg.model.Point;

public class ColorFieldDrawer extends PointDrawer{

    @Override
    public void draw(ArrayList<Point> particleList, ArrayList<Point> pointList, Graphics2D dImgG2D, Graphics2D sImgG2D) {
        
        for(Point p: pointList){
            double xA = p.getXA() * 1000;
            double yA = p.getYA() * 1000;

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

            dImgG2D.fillRect((int)(p.getX()-p.getWorld().getCellD()/2),(int)(p.getY()-p.getWorld().getCellD()/2),
                    p.getWorld().getCellD(), p.getWorld().getCellD()); // field rectangle
        }
        
        super.draw(particleList, pointList, dImgG2D, null);
    }    
}
