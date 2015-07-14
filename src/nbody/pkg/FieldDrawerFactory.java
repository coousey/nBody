
package nbody.pkg;

import java.util.HashMap;
import nbody.pkg.drawers.ColorFieldDrawer;
import nbody.pkg.drawers.EntityDrawer;
import nbody.pkg.drawers.MixFieldDrawer;
import nbody.pkg.drawers.PathDrawer;
import nbody.pkg.drawers.VectorFieldDrawer;
import nbody.pkg.drawers.VectorParticleDrawer;

public class FieldDrawerFactory {
   
    private static HashMap<String, EntityDrawer> map = new HashMap<String, EntityDrawer>(){
            { put("nothing", new EntityDrawer()); }
            { put("path", new PathDrawer()); }
            { put("vectors", new VectorParticleDrawer()); }
            { put("vector field", new VectorFieldDrawer()); }
            { put("color field", new ColorFieldDrawer()); }
            { put("mix field", new MixFieldDrawer()); }
        };
    
    public static EntityDrawer getEntityDrawer(String drawerName){
        return map.get(drawerName);
    }   
}