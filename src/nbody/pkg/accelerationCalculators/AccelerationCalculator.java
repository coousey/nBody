package nbody.pkg.accelerationCalculators;

import java.util.ArrayList;
import nbody.pkg.model.Entity;

public interface AccelerationCalculator {
    
    // the first list is the one for which we calculate the acceleration and the second one causes the acceleration
    void CalculateAcceleration(ArrayList<Entity> entityList, ArrayList<Entity> entityList2, double minX, double minY, double maxX, double maxY, double theta, double G);
}
