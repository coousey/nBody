package nbody.pkg.accelerationCalculators;

import nbody.pkg.model.Entity;

public class QTreeNode {
    
    private double x;
    private double y;
    private double w;
    private double h;
    
    private QTreeNode nw;
    private QTreeNode ne;
    private QTreeNode sw;
    private QTreeNode se;
    
    private double m;
    private double massCenterX;
    private double massCenterY;
    
    private QTreeNode parent;
    private Entity entity = null;
    
    private String dir;
    private int level;
    
    private boolean empty = true;
    
    public QTreeNode(double x, double y, double w, double h, QTreeNode parent) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.parent = parent;
        if(parent != null)
            this.level = parent.getLevel()+1;
    }

    public void setNW(QTreeNode node){
        nw = node;
        nw.setDir("nw");
    }
    public void setNE(QTreeNode node){
        ne = node;
        ne.setDir("ne");
    }
    public void setSW(QTreeNode node){
        sw = node;
        sw.setDir("sw");
    }
    public void setSE(QTreeNode node){
        se = node;
        se.setDir("se");
    }
    public QTreeNode getNW(){ return nw; }
    public QTreeNode getNE(){ return ne; }
    public QTreeNode getSW(){ return sw; }
    public QTreeNode getSE(){ return se; }
    public QTreeNode getParent(){ return parent; }

    public void setEntity(Entity e){ this.entity = e; }
    public Entity getEntity(){ return this.entity; }
    
    public double getX(){ return x; }
    public double getY(){ return y; }
    
    public double getW(){ return w; }
    public  double getH(){ return h; }
    
    public void setEmpty(boolean empty){ this.empty = empty; }
    public boolean empty(){ return empty; } 
    
    public void setM(double m){ this.m = m; }
    public double getM(){ return m; }
    public void setMassCenterX(double x){ massCenterX = x; }
    public void setMassCenterY(double y){ massCenterY = y; }
    public double getMassCenterX(){ return massCenterX; }
    public double getMassCenterY(){ return massCenterY; }
    
    public void setDir(String dir){ this.dir = dir; }
    public String getDir(){ return dir; }
    
    public void setLevel(int level){ this.level = level;}
    public int getLevel(){ return level; }
}

