package nBody.drawers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import nBody.model.Point;

public class MixFieldDrawer extends PointDrawer{

    @Override
    public void draw(ArrayList<Point> particleList, ArrayList<Point> pointList, Graphics2D dImgG2D, Graphics2D sImgG2D) {
        
        for(Point p: pointList){
            double xA = p.getXA() * 1000;
            double yA = p.getYA() * 1000;

            double a = Math.sqrt(xA*xA + yA*yA); // for acceleration to color convertion

            Color color;

            if(a <= 255) color = new Color((int)(a),0,0);
            else if(a > 255 && a <= 510) color = new Color(255,(int)(a - 255),0);
            else if(a > 510 && a <= 765) color = new Color(255,255,(int)(a - 510));
            else color = new Color(255,255,255);

            double max = p.getWorld().getCellD()*0.3; // max vector length

            if(xA > max) xA = max;
            else if(xA < -max) xA = -max;
            if(yA > max) yA = max;
            else if(yA < -max) yA = -max;

            dImgG2D.setColor(color);
            dImgG2D.drawLine((int)p.getX(), (int)p.getY(),
                    (int)Math.round(p.getX()+xA), (int)Math.round(p.getY()+yA)); // field lines 
        }
        
        super.draw(particleList, pointList, dImgG2D, sImgG2D);
    }   
}
