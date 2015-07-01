
package nbody.pkg6;

public class Loop {
    
    private final double targetFps = 100; 
    
    private final World world;
    private final Window window;
    private final MyEventHandler eventHandler;
    
    public Loop(Window window, World world){
        
        this.world = world;
        this.window = window;
        eventHandler = new MyEventHandler();
        this.world.setWidth(window.getCanvasPanel().getWidth());
        this.world.setHeight(window.getCanvasPanel().getHeight());
        this.world.setCellD(window.getCellD());
        this.world.setG(window.getG());
        this.world.setDt(window.getDt());
    }
    
    public void loop(){

        double startTime;
        double fps = 0;
        
        while(true){  
       
            startTime = System.currentTimeMillis();
            
            if(world.isRunning()){
                world.update();
                world.addCycle(); 
            }               
            
            do{  
                eventHandler.doEvents(world, window);
                window.getCanvasPanel().draw(fps, world.getCycles());
                
            }while(System.currentTimeMillis() - startTime < 1000.0/targetFps);
            
            fps = 1000.0/(System.currentTimeMillis() - startTime);
        }  
    }
}

