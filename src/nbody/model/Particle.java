package nBody.model;

import java.util.ArrayList;

public class Particle extends Point{
    
    private final int nr;
    private double xV;
    private double yV;
    private boolean selected;
    
    private boolean isInDelList = false;
    
    public Particle (double x, double y, double xV, double yV, double m, World world){
        
        super(x,y,world);
        
        this.selected = false;
        this.xV = xV;
        this.yV = yV;
        this.m = m;
        this.r = Math.pow(m, 1d/3d);
        world.setNrOfParticlesCreated(world.getNrOfParticlesCreated() + 1);
        this.nr = world.getNrOfParticlesCreated();
    }
    
    public void integrate(double dt){
            
        x += xV*dt + 0.5*xA*dt*dt; 
        y += yV*dt + 0.5*yA*dt*dt; 
        
        xV += xA*dt;
        yV += yA*dt;   
    }
        
    public void colision(ArrayList<Point> particleList, double scale){
        
        double xD; 
        double yD;
        
        for(Point po: particleList ){
            Particle p = (Particle)po;
            xD = p.x - x; 
            yD = p.y - y;
            
            if(!p.equals(this) && (!this.isInDelList && !((Particle)p).isInDelList)){
                
                if(Math.abs(xD) < (r + p.r)/2d && Math.abs(yD) < (r + p.r)/2d){

                    if(p.m > m){
                        x = p.x;
                        y = p.y;
                    }
                    
                    xV = (m*xV + p.m*p.xV)/(m + p.m);
                    yV = (m*yV + p.m*p.yV)/(m + p.m);
                   
                    m += p.m;
                    r = Math.pow(m, 1d/3d)* scale;
                    
                    if(p.selected)          // after a colision the greater particle remains 
                        selected = true;    // if the smaler particle is selected we have to select the bigger
                                                
                    world.getDelList().add(p);
                    ((Particle)p).isInDelList = true;
                }
            }
        }
    }
    
    public int getNr(){ return nr;}
    
    public double getXV(){ return xV; }   
    public void setXV(double xV){ this.xV = xV; }    
    public double getYV(){ return yV; }   
    public void setYV(double yV){ this.yV = yV; }
    
    public boolean isSelected(){ return selected; }
    public void setSelected(boolean isSelected){ this.selected = isSelected; }
}

