
package nbody.pkg;

import view.Window;
import nbody.pkg.model.World;

public class Loop {
    
    private final double targetFps = 100; 
    
    private final World world;
    private final Window window;
    private final MyEventHandler eventHandler;
    
    public Loop(Window window, World world){
        
        this.world = world;
        this.window = window;
        eventHandler = new MyEventHandler();
        
        world.setWidth(window.getCanvasPanel().getWidth());
        world.setHeight(window.getCanvasPanel().getHeight());
        world.setCellD(window.getButtonPanel().getCellD());
        world.setG(window.getButtonPanel().getG());
        world.setDt(window.getButtonPanel().getDt());
        window.getCanvasPanel().setBufferedImages();
        
        world.setAccCalc(AccelerationCalcFactory.getAccCalc((String) window.getButtonPanel().getCalcComboBox().getSelectedItem()));
        window.getCanvasPanel().setDrawer(FieldDrawerFactory.getEntityDrawer((String) window.getButtonPanel().getDrawerComboBox().getSelectedItem()));
    }
    
    public void loop(){

        double startTime;
        double fps = 0;
        
        while(true){  
       
            startTime = System.currentTimeMillis();
            
            if(world.isRunning()){
                world.updateParticles();
                
                String drawer = (String) window.getButtonPanel().getDrawerComboBox().getSelectedItem();
                if(("vector field".equals(drawer)) || ("color field".equals(drawer)) || ("mix field".equals(drawer)))
                    world.updateField();
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

