package nBody.drawers;

import java.awt.Graphics2D;
import java.util.ArrayList;

import nBody.model.Point;

public interface Drawer {

	public void draw(ArrayList<Point> particleList, ArrayList<Point> pointList, Graphics2D dImgG2D, Graphics2D sImgG2D);
}
