package view;

import nbody.pkg.model.World;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import nbody.pkg.drawers.EntityDrawer;
import nbody.pkg.drawers.PathDrawer;
import nbody.pkg.drawers.SimpleParticleDrawer;

public class CanvasPanel extends JPanel{
    
    private BufferedImage img;  // the static and dynamic images are painted on it
    private Graphics2D imgG2D;
    private BufferedImage sImg; // static image for path drawing etc.
    private Graphics2D sImgG2D; 
    private BufferedImage dImg; // dynamic image for thing that change every frame
    private Graphics2D dImgG2D;
    private Graphics2D panelG2D;
    
    private final World world;
    private final Window window;
    private EntityDrawer particleDrawer;
    private EntityDrawer fieldDrawer;
    private EntityDrawer pathDrawer;
    
    public CanvasPanel(Window window, World world){
        
        this.world = world;
        this.window = window;
        particleDrawer = new SimpleParticleDrawer();
        fieldDrawer = null;   
        pathDrawer = new PathDrawer();
    }
    
    public void draw(double fps, int cycles){

        panelG2D = (Graphics2D)getGraphics();
  
        if(!window.getButtonPanel().getPathCheck()){               
            imgG2D.setColor(world.getBackgroundColor());
            imgG2D.fillRect(0,0,getWidth(), getHeight());

            sImgG2D.setColor(world.getBackgroundColor());
            sImgG2D.fillRect(0,0,getWidth(), getHeight());
        }

        dImgG2D.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR)); // fill with transparency so it wont cover the path
        dImgG2D.fillRect(0,0, getWidth(), getHeight());  
        dImgG2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER)); 
          
        // path
        if(window.getButtonPanel().getPathCheck())
            pathDrawer.draw(world.getParticleList(), sImgG2D);
        
        // field
        if(fieldDrawer != null) 
            fieldDrawer.draw(world.getFieldPointList(), dImgG2D);
        
        // particles
        particleDrawer.draw(world.getParticleList(), dImgG2D);
        
        dImgG2D.setColor(Color.RED);
        
        // mass center
        dImgG2D.drawOval((int)(world.getMassCenterX() - world.getMassCenterCircleSize()/2),
                (int)(world.getMassCenterY()- world.getMassCenterCircleSize()/2),
                (int)world.getMassCenterCircleSize(),(int)world.getMassCenterCircleSize());
       
        dImgG2D.drawString("n             " + Integer.toString(world.getN()), 10, 15); 
        dImgG2D.drawString("total m   " + Integer.toString((int)world.getTotalMass()), 10, 35);
        dImgG2D.drawString("fps         " + String.format("%.4f",fps) , 10, 55);
        dImgG2D.drawString("cycles     " + (int)cycles , 10, 75);
        
        dImgG2D.drawString(" pan: mouse      add: Ctrl + mouse      zoom: Shift + mouse      select: Alt + mouse", 10, getHeight()-20);
        dImgG2D.drawLine(10, getHeight() - 45, (int)(100*world.getScale()), getHeight() - 45);
        dImgG2D.drawString(String.format("%.4f"+ " %%",world.getScale()*100), 40, getHeight() - 50);

        if(window.getButtonPanel().getPathCheck()) imgG2D.drawImage(sImg, 0, 0, null);                          
        imgG2D.drawImage(dImg, 0, 0, null);           
        panelG2D.drawImage(img, 0, 0, null);

        panelG2D.dispose();     
    }

    public void setBufferedImages(){

        img = new BufferedImage(this.getWidth(), this.getHeight(),BufferedImage.TYPE_INT_RGB);
        imgG2D = (Graphics2D)img.getGraphics();
        imgG2D.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON));

        sImg = new BufferedImage(this.getWidth(), this.getHeight(),BufferedImage.TYPE_INT_RGB);
        sImgG2D = (Graphics2D)sImg.getGraphics();
        sImgG2D.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON));

        dImg = new BufferedImage(this.getWidth(), this.getHeight(),BufferedImage.TYPE_INT_ARGB);
        dImgG2D = (Graphics2D)dImg.getGraphics();
        dImgG2D.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON));
    }
    
    public void saveImage(int cycles){
        
    try {
        ImageIO.write( dImg, "PNG", new File("saved_"+cycles+".png") );

        } catch (IOException e) { System.out.println("IO exception in saveImage"); }
    }   
    public void setParticleDrawer(EntityDrawer particleDrawer){ this.particleDrawer = particleDrawer; }
    public void setFieldDrawer(EntityDrawer fieldDrawer){ this.fieldDrawer = fieldDrawer; }
}
