package nbody.pkg.drawers;

import java.awt.Graphics2D;
import java.util.ArrayList;
import nbody.pkg.model.Entity;

public interface EntityDrawer {
    
    void draw(ArrayList<Entity> entityList, Graphics2D dImgG2D);
}
