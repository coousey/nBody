package nbody.pkg.drawers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import nbody.pkg.model.Point;

public class PathDrawer extends PointDrawer{

    @Override
    public void draw(ArrayList<Point> particleList, ArrayList<Point> pointList, Graphics2D dImgG2D, Graphics2D sImgG2D) {

        sImgG2D.setColor(Color.GRAY);
        
        for(Point p: particleList)
            sImgG2D.fillOval((int)(p.getX()), (int)(p.getY()), 1, 1);
        
        super.draw(particleList, pointList, dImgG2D, sImgG2D);
    }    
}
