package view;

import nbody.pkg.model.World;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import nbody.pkg.Listeners;

public class Window extends JFrame{
    
    private final CanvasPanel canvasPanel;
    private final ButtonPanel buttonPanel;   
       
    private final Listeners listeners = new Listeners();
    
    public Window(World world){
        
        this.setSize(new Dimension(1000,700));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        canvasPanel = new CanvasPanel(this, world);
        canvasPanel.addMouseListener(listeners.getMouseListener());
        canvasPanel.addComponentListener(listeners.getComponentListener());
        this.add(canvasPanel);
        
        buttonPanel = new ButtonPanel(this, listeners);     
        this.add(buttonPanel,BorderLayout.NORTH);
               
        this.setResizable(true);
        this.setVisible(true);
    }
   
    public CanvasPanel getCanvasPanel(){ return canvasPanel; }
    public ButtonPanel getButtonPanel(){ return buttonPanel; }
    public Listeners getListeners(){ return listeners; }
}