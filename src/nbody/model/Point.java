package nBody.model;

public class Point {
    
    protected double x;
    protected double y;  
    protected double xA;
    protected double yA;
    protected double r;
    protected double m;
    
    protected World world;
    
    public Point(double x, double y, World world){
             
        this.x = x;
        this.y = y;
        this.r = 0;
        this.world = world;
    } 
    
    public double getX(){ return x; }
    public void setX(double x){ this.x = x; }   
    public double getY(){ return y; }
    public void setY(double y){ this.y = y; }
    
    public double getXA(){ return xA; }
    public void setXA(double xA){ this.xA = xA; }   
    public double getYA(){ return yA; }
    public void setYA(double yA){ this.yA = yA; }
    
    public double getR(){ return r; }
    public void setR(double r){ this.r = r; }
    
    public double getM(){ return m; }
    public void setM(double m){ this.m = m; }
    
    public World getWorld(){ return world; }     
}
