package nbody.pkg6;

public class Entity {
    
    protected double x;
    protected double y;  
    protected double xA;
    protected double yA;
    protected double xV;
    protected double yV;
    protected double r;
    protected double m;
    protected boolean selected;
    
    protected World world;
    
    public Entity(double x, double y, World world){
             
        this.x = x;
        this.y = y;
        this.r = 0;
        this.world = world;
        this.selected = false;
    } 
    
    public double getX(){ return x; }
    public void setX(double x){ this.x = x; }   
    public double getY(){ return y; }
    public void setY(double y){ this.y = y; }
    
    public double getXA(){ return xA; }
    public void setXA(double xA){ this.xA = xA; }   
    public double getYA(){ return yA; }
    public void setYA(double yA){ this.yA = yA; }
    
    public double getXV(){ return xV; }   
    public void setXV(double xV){ this.xV = xV; }    
    public double getYV(){ return yV; }   
    public void setYV(double yV){ this.yV = yV; }
    
    public double getR(){ return r; }
    public void setR(double r){ this.r = r; }
    
    public double getM(){ return m; }
    public void setM(double m){ this.m = m; }
    
    public boolean isSelected(){ return selected; }
    public void setSelected(boolean isSelected){ this.selected = isSelected; }
    
    public World getWorld(){ return world; }     
}