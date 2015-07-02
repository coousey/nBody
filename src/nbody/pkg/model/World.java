package nbody.pkg.model;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import nbody.pkg.Loop;
import nbody.pkg.RandomGenerator;
import nbody.pkg.accelerationCalculators.AccelerationCalculator;
import nbody.pkg.accelerationCalculators.QTreeAccCalc;

public class World {
      
    public static double scale = 1;  // sacle. changes when zooming
    
    private int n;  // total number of particles currently in the sim  
    private double G;
    private double dt;
    private int cellD;  // distance between field points
    private double totalMass;
    private final int selectDistance = 50; // distance in pixels from witch selecting is possible
    private double range = 5; // fraction of screan containing the simulation. behind it particles will be deleted. also size of qtree
    private int cycles;
    private int width;  // size of canvas
    private int height;
    private boolean running = false;
    private int pressedX;   // coordinates of mouse press event
    private int pressedY;
    private double massCenterX;
    private double massCenterY;
    private double massCenterCircleSize = 10;
    private Color backgroundColor = Color.WHITE;
    private int nrOfParticlesCreated;
    
    private AccelerationCalculator particleAccCalc;
    private AccelerationCalculator fieldAccCalc;
    private final RandomGenerator ranGen = new RandomGenerator();     
    private final ArrayList<Entity> particleList = new ArrayList<>();
    private final ArrayList<Entity> delList = new ArrayList<>();
    private final ArrayList<Entity> fieldPointList = new ArrayList<>();
    
    public World(){
        
        particleAccCalc = new QTreeAccCalc();
        fieldAccCalc = new QTreeAccCalc();
    }
    
    public void update(){
    
        particleAccCalc.CalculateAcceleration(particleList, particleList,
            (width/2) - (width/2)*range,(height/2) - (width/2)*range,
            (width/2) + (width/2)*range,(height/2) + (width/2)*range,
            0.5, G);
        
        for(Entity e: particleList){ 
            Particle p = (Particle)e;
            
            p.integrate(dt);
            p.colision(particleList,scale);
            outOfRangeToDelList(p);
        }
        
        DeleteDelList();
        calcCenterOfMass();
        
        if(fieldAccCalc != null){            
            fieldAccCalc.CalculateAcceleration(fieldPointList, particleList,
                (width/2) - (width/2)*range,(height/2) - (width/2)*range,
                (width/2) + (width/2)*range,(height/2) + (width/2)*range,
                0.5, G);
        }
    }
    
    public void clear(){
         
        particleList.clear();      
        totalMass = 0;
        n = 0;
        cycles = 0;
        running = false;
    }
    
    // adds n particles of mass m, randomly distributed in radius maxR
    public void AddRandom(int n, int maxR, double m){ 

        double fi; 
	double r;
        
        for(int i = 0; i < n; i++ ){
            fi = Math.random()*2*Math.PI;
            r = ranGen.linearRandom(maxR);    
            AddParticle(width/2 + r*Math.cos(fi), height/2 + r*Math.sin(fi),0 , 0, m);
        }
    }
    
    public void AddParticle(double x, double y, double xV, double yV, double m){       
        particleList.add(new Particle(x, y, xV, yV, m, this ));
        n++;
        totalMass += m;
    }
    
    public void selectNearest(double x, double y){
        
        Particle tempP = null;
        double xD;
        double yD;
        double minD = Double.POSITIVE_INFINITY;
        double d;
        
        for(Entity e: particleList){ 
            Particle p = (Particle)e;
            
            p.setSelected(false);
            xD = p.getX() - x;
            yD = p.getY() - y;
            d = Math.sqrt(xD*xD + yD*yD);
            if(d <= minD ){
                minD = d;
                tempP = p;
            }
        }   
        if(minD <= selectDistance && tempP != null){
            tempP.setSelected(true);
        }
    }
    
    public void translate(double xD, double yD){
        
       for(Entity e: particleList){              
            e.setX(e.getX() + xD);
            e.setY(e.getY() + yD);
       }     
    }

    public void zoom(double pressX, double pressY, int releaseX, int releaseY) {
        
        double zoom = 0.01*(releaseX - pressX);
        scale += scale*zoom ;
        range += range*zoom;
        
        for(Entity e: particleList){ 
            Particle p = (Particle)e;
            
            p.setX( p.getX() + (p.getX() - pressX)*zoom );
            p.setY( p.getY() + (p.getY() - pressY)*zoom );

            p.setR( p.getR() + p.getR()*zoom );
            
            p.setXV( p.getXV() + p.getXV()*zoom );
            p.setYV( p.getYV() + p.getYV()*zoom );          
       }   
    }
    
    public void setFieldList(){
        
        fieldPointList.clear();
        
        for(int i = 0; i < height+cellD; i+= cellD){
            for(int j = 0; j < width+cellD; j+= cellD){             
                fieldPointList.add(new Entity(j, i, this ));
            }
        }
    }
    
    public void outOfRangeToDelList(Entity e){        // kasuje cząsteczki poza zasięgiem drzewa na podstawie range  
        if(e.getX() < (width/2) - (width/2)*range ||
           e.getX() > (width/2) + (width/2)*range ||
           e.getY() < (height/2) - (width/2)*range ||
           e.getY() > (height/2) + (width/2)*range){
                delList.add(e); 
        }
    }

    public void saveStateToFile(){
        try{
            PrintWriter printWriter = new PrintWriter("save.txt");       
            printWriter.println(cycles);
            printWriter.println(nrOfParticlesCreated);
            
            for(Entity e: particleList){ 
               Particle p = (Particle)e;
               
               printWriter.println(p.x+" "+p.y+" " + p.getXV() + " " + p.getYV()+" " + p.getM());
            }
 
            printWriter.close();
        }
        catch (FileNotFoundException ex){
            Logger.getLogger(Loop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadStateFromFile(){
        
        particleList.clear();
        
        try{
            BufferedReader br = new BufferedReader(new FileReader("save.txt"));
            
            String line = br.readLine();
            cycles = Integer.parseInt(line);
            line = br.readLine();
            nrOfParticlesCreated = Integer.parseInt(line);
            line = br.readLine();

            while (line != null) {
                System.out.println(line);
                particleList.add(new Particle(Double.parseDouble(line.split(" ")[0]),Double.parseDouble(line.split(" ")[1]),Double.parseDouble(line.split(" ")[2]),Double.parseDouble(line.split(" ")[3]),Double.parseDouble(line.split(" ")[4]),this));
                line = br.readLine();
                n++;
            }

            br.close();
        }
        catch(IOException ex){
            Logger.getLogger(Loop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void DeleteDelList(){
        
        n -= delList.size();
        particleList.removeAll(delList);
        delList.clear();
    }
    
    public void addCycle(){
        cycles++;
    }
    
    public void calcCenterOfMass(){
        
        double x = 0;
        double y = 0;
        
        for(Entity e: particleList){  
            Particle p = (Particle)e;
            x += p.getM()*p.getX();
            y += p.getM()*p.getY();
        }
        
        massCenterX = x/totalMass;
        massCenterY = y/totalMass;
    }
    
    public ArrayList<Entity> getParticleList(){ return particleList; }
    public ArrayList<Entity> getFieldPointList(){ return fieldPointList; }
    public ArrayList<Entity> getDelList(){ return delList; }
    
    public void setParticleAccCalc(AccelerationCalculator accCalc){ this.particleAccCalc = accCalc; }
    public void setFieldAccCalc(AccelerationCalculator accCalc){ this.fieldAccCalc = accCalc; }
    
    public void setCellD(int cellD){ this.cellD = cellD; }
    public int getCellD(){ return cellD; }
    
    public void setWidth(int width){ this.width = width; }
    public void setHeight(int height){ this.height = height; } 
    
    public double getTotalMass(){ return totalMass; }
     public double getMassCenterX(){ return massCenterX; }
    public double getMassCenterY(){ return massCenterY; }
    public double getMassCenterCircleSize(){ return massCenterCircleSize; }
    
    public boolean isRunning(){ return running; }
    public void setRunning(boolean running){ this.running = running; }
    
    public void setPressedX(int pressedX){ this.pressedX = pressedX; }
    public void setPressedY(int pressedY){ this.pressedY = pressedY; }
    public int getPressedX(){ return pressedX; }
    public int getPressedY(){ return pressedY; }
   
    public void setBackgroundColor(Color color){ this.backgroundColor = color; }
    public Color getBackgroundColor(){ return backgroundColor; }
    
    public void setNrOfParticlesCreated(int nr){ this.nrOfParticlesCreated = nr; }
    public int getNrOfParticlesCreated(){ return nrOfParticlesCreated; }

    public int getN(){ return n; }
    
    public void setG(double G){ this.G = G; }
    
    public void setDt(double dt){ this.dt = dt; }
    
    public int getCycles(){ return cycles; }
    
    public double getRange(){ return range; }
    
    public double getScale(){ return scale; }
}