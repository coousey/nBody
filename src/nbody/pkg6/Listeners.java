package nbody.pkg6;

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Stack;

public class Listeners {
    
    private final Stack<AWTEvent> eventStack;
    
    public Listeners(){
        
        eventStack = new Stack<>();
    }
    
    private final  ActionListener actionListener = new ActionListener() { 

        @Override
        public void actionPerformed(ActionEvent e) {
            eventStack.push(e);       
        }    
    };
    
    private final ComponentListener componentListener = new ComponentListener() {

        @Override
        public void componentResized(ComponentEvent e) {
            eventStack.push(e);       
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
            eventStack.push(e);        
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            eventStack.push(e);        
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
    
    public Stack getEventStack(){
        return eventStack;
    }
}

