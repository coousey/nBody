package nBody.control;

import nBody.algorithms.AlgorithmFactory;
import nBody.drawers.DrawerFactory;
import nBody.model.World;
import nBody.view.Window;

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;
import java.awt.event.ComponentEvent;

public class MyEventHandler {  
    
    public void doEvents(World world, Window window){
        
        ArrayList<AWTEvent> eventList = window.getListeners().getEventList();
        
        while( !eventList.isEmpty()){
        	
            AWTEvent e = eventList.get(0);
            eventList.remove(0);
            
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
                    window.getCanvasPanel().drawBackgroud();
                }
                // start/pause
                else if(ae.getSource() == window.getButtonPanel().getStartStopButton())
                    world.setRunning(!world.isRunning());
                // save
                else if(ae.getSource() == window.getButtonPanel().getSaveButton())
                    world.saveStateToFile();
                // load
                else if(ae.getSource() == window.getButtonPanel().getLoadButton())
                    world.loadStateFromFile();
                // drawer comboBox
                else if(ae.getSource() == window.getButtonPanel().getDrawerComboBox()){
                    window.getCanvasPanel().setDrawer(DrawerFactory.getPointDrawer((String) window.getButtonPanel().getDrawerComboBox().getSelectedItem()));
                }
                // calcComboBox
                else if(ae.getSource() == window.getButtonPanel().getCalcComboBox()){
                    world.setAlgorithm(AlgorithmFactory.getAlgorithm((String) window.getButtonPanel().getCalcComboBox().getSelectedItem()));
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
