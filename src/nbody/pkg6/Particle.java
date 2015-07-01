package nbody.pkg6;

import java.util.ArrayList;

public class Particle extends Entity{
    
    private static int tmpNr;
    private final int nr;
    
    private boolean isInDelList = false;
    
    public Particle (double x, double y, double xV, double yV, double m, World world){
        
        super(x,y,world);
        
        this.xV = xV;
        this.yV = yV;
        this.m = m;
        this.r = Math.pow(m, 1d/3d);
        
        tmpNr ++;
        this.nr = tmpNr;
    }
    
    public void integrate(double dt){
            
        x += xV*dt + 0.5*xA*dt*dt; 
        y += yV*dt + 0.5*yA*dt*dt; 
        
        xV += xA*dt;
        yV += yA*dt;   
    }
        
    public void colision(ArrayList<Entity> particleList, double scale){
        
        double xD; 
        double yD;
        
        for(Entity p: particleList ){
            
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
}
