package nBody.drawers;

import java.util.HashMap;

import nBody.drawers.ColorFieldDrawer;
import nBody.drawers.MixFieldDrawer;
import nBody.drawers.PathDrawer;
import nBody.drawers.PointDrawer;
import nBody.drawers.VectorFieldDrawer;
import nBody.drawers.VectorParticleDrawer;

public class DrawerFactory {
   
    private static HashMap<String, Drawer> map = new HashMap<String, Drawer>(){
        
            { put("nothing", new PointDrawer()); }
            { put("path", new PathDrawer()); }
            { put("vectors", new VectorParticleDrawer()); }
            { put("vector field", new VectorFieldDrawer()); }
            { put("color field", new ColorFieldDrawer()); }
            { put("mix field", new MixFieldDrawer()); }
        };
    
    public static Drawer getPointDrawer(String drawerName){
        return map.get(drawerName);
    }   
}

