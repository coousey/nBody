package nbody.pkg.accelerationCalculators;

import java.util.ArrayList;
import nbody.pkg.model.Point;

public interface Algorithm {
    
    // the first list is the one for which we calculate the acceleration and the second one causes the acceleration
    void CalculateAcceleration(ArrayList<Point> pointList, ArrayList<Point> pointList2, double minX, double minY, double maxX, double maxY, double theta, double G);
}
