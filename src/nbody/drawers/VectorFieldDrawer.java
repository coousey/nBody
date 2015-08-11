package nBody.drawers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import nBody.model.Point;

public class VectorFieldDrawer extends PointDrawer {  

    @Override
    public void draw(ArrayList<Point> particleList, ArrayList<Point> pointList, Graphics2D dImgG2D, Graphics2D sImgG2D) {
        
        dImgG2D.setColor(Color.RED);
        
        for(Point p: pointList){
            double xA = p.getXA() * 200;
            double yA = p.getYA() * 200;

            double max = p.getWorld().getCellD()*0.3;   // maximal vector length

            if(xA > max) xA = max;
            else if(xA < -max) xA = -max;
            if(yA > max) yA = max;
            else if(yA < -max) yA = -max;

            dImgG2D.drawLine((int)p.getX(), (int)p.getY(),
                    (int)Math.round(p.getX()+xA), (int)Math.round(p.getY()+yA)); // field lines
        }
        
        super.draw(particleList, pointList, dImgG2D, sImgG2D);
    }
}

