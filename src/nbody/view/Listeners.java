package nBody.view;

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Stack;

public class Listeners {
    
    private ArrayList<AWTEvent> eventList;
    
    public Listeners(){
        
        eventList = new ArrayList<AWTEvent>();
    }
    
    private final  ActionListener actionListener = new ActionListener() { 

        @Override
        public void actionPerformed(ActionEvent e) {
            eventList.add(e);       
        }    
    };
    
    private final ComponentListener componentListener = new ComponentListener() {

        @Override
        public void componentResized(ComponentEvent e) {
            eventList.add(e);   
        }
        @Override
        public void componentMoved(ComponentEvent e) {}
        @Override
        public void componentShown(ComponentEvent e) {}
        @Override
        public void componentHidden(ComponentEvent e) {}
    
    };
    
    private final MouseListener mouseListener = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mousePressed(MouseEvent e) {
            eventList.add(e);        
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            eventList.add(e);        
        }
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    };
    
    public final ActionListener getActionListener(){
        return actionListener;
    }
    
    public MouseListener getMouseListener(){
        return mouseListener;
    }
    
    public ComponentListener getComponentListener(){
        return componentListener;
    }
    
    public ArrayList<AWTEvent> getEventList(){
        return eventList;
    }
}

