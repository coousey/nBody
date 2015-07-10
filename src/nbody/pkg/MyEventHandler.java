package nbody.pkg;

import view.Window;
import nbody.pkg.model.World;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Stack;
import java.awt.event.ComponentEvent;
import nbody.pkg.accelerationCalculators.BruteForceAccCalc;
import nbody.pkg.accelerationCalculators.QTreeAccCalc;
import nbody.pkg.drawers.ColorFieldDrawer;
import nbody.pkg.drawers.MixFieldDrawer;
import nbody.pkg.drawers.SimpleParticleDrawer;
import nbody.pkg.drawers.VectorParticleDrawer;
import nbody.pkg.drawers.VectorFieldDrawer;

public class MyEventHandler {  
    
    public void doEvents(World world, Window window){
        
        Stack<AWTEvent> eventStack = window.getListeners().getEventStack();
        
        while( !eventStack.empty()){
            AWTEvent e = eventStack.pop();
            
            // buttons
            if(e.getClass() == ActionEvent.class){   
                ActionEvent ae = (ActionEvent)e;
  
                // random
                if(ae.getSource() == window.getButtonPanel().getRandomButton())
                    world.AddRandom(window.getButtonPanel().getRandN(), window.getButtonPanel().getR(), window.getButtonPanel().getM()); 
                // apply
                else if(ae.getSource() == window.getButtonPanel().getApplyChangesButton()){
                    world.setG(window.getButtonPanel().getG());
                    world.setDt(window.getButtonPanel().getDt());
                    world.setCellD(window.getButtonPanel().getCellD());
                    world.setFieldList();
                }
                // clear
                else if(ae.getSource() == window.getButtonPanel().getClearButton()){
                    world.clear();
                }
                // pause
                else if(ae.getSource() == window.getButtonPanel().getStartStopButton())
                    world.setRunning(!world.isRunning());
                // save
                else if(ae.getSource() == window.getButtonPanel().getSaveButton())
                    world.saveStateToFile();
                // load
                else if(ae.getSource() == window.getButtonPanel().getLoadButton())
                    world.loadStateFromFile();
                // field radio buttons
                else if(ae.getSource() == window.getButtonPanel().getNonWithVectorsRadioButton()){
                    window.getCanvasPanel().setParticleDrawer(new VectorParticleDrawer());
                    window.getCanvasPanel().setFieldDrawer(null);
                    world.setBackgroundColor(Color.WHITE);
                    world.setFieldAccCalc(null);
                }
                else if(ae.getSource() == window.getButtonPanel().getNonRadioButton()){
                    window.getCanvasPanel().setParticleDrawer(new SimpleParticleDrawer());
                    window.getCanvasPanel().setFieldDrawer(null);
                    world.setBackgroundColor(Color.WHITE);
                    world.setFieldAccCalc(null);
                }
                else if(ae.getSource() == window.getButtonPanel().getColorRadioButton()){
                    window.getCanvasPanel().setParticleDrawer(new SimpleParticleDrawer());
                    window.getCanvasPanel().setFieldDrawer(new ColorFieldDrawer());
                    world.setBackgroundColor(Color.WHITE);
                    if(window.getButtonPanel().getBruteRadioButton().isSelected())
                        world.setFieldAccCalc(new BruteForceAccCalc());
                    else 
                        world.setFieldAccCalc(new QTreeAccCalc());
                }
                else if(ae.getSource() == window.getButtonPanel().getVectorRadioButton()){
                    window.getCanvasPanel().setParticleDrawer(new SimpleParticleDrawer());
                    window.getCanvasPanel().setFieldDrawer(new VectorFieldDrawer());
                    world.setBackgroundColor(Color.WHITE);
                    if(window.getButtonPanel().getBruteRadioButton().isSelected())
                        world.setFieldAccCalc(new BruteForceAccCalc());
                    else 
                        world.setFieldAccCalc(new QTreeAccCalc());
                }
                else if(ae.getSource() == window.getButtonPanel().getMixRadioButton()){
                    window.getCanvasPanel().setParticleDrawer(new SimpleParticleDrawer());
                    window.getCanvasPanel().setFieldDrawer(new MixFieldDrawer());
                    world.setBackgroundColor(Color.BLACK);
                    if(window.getButtonPanel().getBruteRadioButton().isSelected())
                        world.setFieldAccCalc(new BruteForceAccCalc());
                    else 
                        world.setFieldAccCalc(new QTreeAccCalc());
                }
                // calc radio butons
                else if(ae.getSource() == window.getButtonPanel().getBruteRadioButton()){
                    world.setParticleAccCalc(new BruteForceAccCalc());
                    world.setFieldAccCalc(new BruteForceAccCalc());
                }
                else if(ae.getSource() == window.getButtonPanel().getQTreeRadioButton()){
                    world.setParticleAccCalc(new QTreeAccCalc());
                    world.setFieldAccCalc(new QTreeAccCalc());
                }
            }
            
            // mouse 
            else if(e.getClass() == MouseEvent.class){               
                MouseEvent me = (MouseEvent)e;
                if(me.getID() == MouseEvent.MOUSE_PRESSED){ // save pressing coordinates
                    world.setPressedX(me.getX());
                    world.setPressedY(me.getY());
                }
                else if(me.getID() == MouseEvent.MOUSE_RELEASED ){ // performs actions on mouse release based on the saved press cooridnates
                    if((me.getModifiersEx() & MouseEvent.CTRL_DOWN_MASK) != 0)                                                
                        world.AddParticle(world.getPressedX(), world.getPressedY(), (me.getX() - world.getPressedX())*0.1, (me.getY() - world.getPressedY())*0.1, window.getButtonPanel().getM());
                    else if((me.getModifiersEx() & MouseEvent.SHIFT_DOWN_MASK) != 0)
                        world.zoom(world.getPressedX(),world.getPressedY(),me.getX(),me.getY());
                    else if((me.getModifiersEx() & MouseEvent.ALT_DOWN_MASK) != 0)
                        world.selectNearest(me.getX(), me.getY());
                    else
                         world.translate(me.getX()-world.getPressedX(), me.getY()-world.getPressedY());                     
                } 
            }
            else if(e.getClass() == ComponentEvent.class){  
                world.setWidth(window.getCanvasPanel().getWidth());
                world.setHeight(window.getCanvasPanel().getHeight());
                window.getCanvasPanel().setBufferedImages();
                world.setFieldList();
            }
        }       
    } 
}

