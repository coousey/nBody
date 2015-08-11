package nBody;

import nBody.control.Loop;
import nBody.model.World;
import nBody.view.Window;

public class NBody {

public static void main(String[] args) {
     
        World world = new World();
        Window window = new Window(world);
        Loop loop = new Loop(window, world);
        
        loop.loop();
    } 
}
