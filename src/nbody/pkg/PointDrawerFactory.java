
package nbody.pkg;

import java.util.HashMap;
import nbody.pkg.drawers.ColorFieldDrawer;
import nbody.pkg.drawers.PointDrawer;
import nbody.pkg.drawers.MixFieldDrawer;
import nbody.pkg.drawers.PathDrawer;
import nbody.pkg.drawers.VectorFieldDrawer;
import nbody.pkg.drawers.VectorParticleDrawer;

public class PointDrawerFactory {
   
    private static HashMap<String, PointDrawer> map = new HashMap<String, PointDrawer>(){
        
            { put("nothing", new PointDrawer()); }
            { put("path", new PathDrawer()); }
            { put("vectors", new VectorParticleDrawer()); }
            { put("vector field", new VectorFieldDrawer()); }
            { put("color field", new ColorFieldDrawer()); }
            { put("mix field", new MixFieldDrawer()); }
        };
    
    public static PointDrawer getPointDrawer(String drawerName){
        return map.get(drawerName);
    }   
}
