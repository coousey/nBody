package nbody.pkg6;

public class NBody6 {

public static void main(String[] args) {
     
        World world = new World();
        Window window = new Window(world);
        window.getCanvasPanel().setBufferedImages();
        Loop loop = new Loop(window, world);
        
        loop.loop();
    } 
}