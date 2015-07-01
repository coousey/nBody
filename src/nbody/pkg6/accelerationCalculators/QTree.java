package nbody.pkg6.accelerationCalculators;

import nbody.pkg6.Entity;

public class QTree {
    
    private QTreeNode root;

    public QTree(double minX, double minY, double maxX, double maxY) {

        this.root = new QTreeNode(minX, minY, maxX - minX, maxY - minY, null);
        this.root.setLevel(1);
    } 
    
    public void insertParticle(QTreeNode node, Entity e){ 
        
        double m = node.getM() + e.getM();
        node.setMassCenterX((node.getMassCenterX()*node.getM() + e.getX()*e.getM())/m);
        node.setMassCenterY((node.getMassCenterY()*node.getM() + e.getY()*e.getM())/m);
        node.setM(m);

        if(node.getEntity() == null && node.getNW() == null){  // external epmty     
            node.setEntity(e);            
        }
        
        else if(node.getEntity() == null && node.getNW() != null ){ // internal empty
            
            if(e.getX() > node.getNW().getX() && e.getX() < node.getNW().getX() + node.getNW().getW() && e.getY() > node.getNW().getY() && e.getY() < node.getNW().getY()+node.getNW().getH()) 
                insertParticle(node.getNW(),e);
            else if(e.getX() > node.getNE().getX() && e.getX() < node.getNE().getX() + node.getNE().getW() && e.getY() > node.getNE().getY() && e.getY() < node.getNE().getY()+node.getNE().getH())
                insertParticle(node.getNE(),e);
            else if(e.getX() > node.getSW().getX() && e.getX() < node.getSW().getX()+node.getSW().getW() && e.getY() > node.getSW().getY() && e.getY() < node.getSW().getY()+node.getSW().getH())
                insertParticle(node.getSW(),e);
            else if(e.getX() > node.getSE().getX() && e.getX() < node.getSE().getX()+node.getSE().getW() && e.getY() > node.getSE().getY() && e.getY() < node.getSE().getY()+node.getSE().getH())  
                insertParticle(node.getSE(),e);               
        } 
        
        else if(node.getEntity() != null && node.getNW() == null){ // external full 
            
            Entity op = node.getEntity(); 

            double x = node.getX();
            double y = node.getY();
            double w = node.getW()/2.0;
            double h = node.getH()/2.0;          
            
            node.setNW(new QTreeNode(x,y,w,h,node));
            node.setNE(new QTreeNode((x+w),y,w,h,node));
            node.setSW(new QTreeNode(x,(y+h),w,h,node));
            node.setSE(new QTreeNode((x+w),(y+h),w,h,node));                 
            
            if(op.getX() > node.getNW().getX() && op.getX() < node.getNW().getX() + node.getNW().getW() && op.getY() > node.getNW().getY() && op.getY() < node.getNW().getY()+node.getNW().getH()) 
                insertParticle(node.getNW(),op);  
            else if(op.getX() > node.getNE().getX() && op.getX() < node.getNE().getX() + node.getNE().getW() && op.getY() > node.getNE().getY() && op.getY() < node.getNE().getY()+node.getNE().getH())
                insertParticle(node.getNE(),op);
            else if(op.getX() > node.getSW().getX() && op.getX() < node.getSW().getX()+node.getSW().getW() && op.getY() > node.getSW().getY() && op.getY() < node.getSW().getY()+node.getSW().getH())
                insertParticle(node.getSW(),op);
            else if(op.getX() > node.getSE().getX() && op.getX() < node.getSE().getX()+node.getSE().getW() && op.getY() > node.getSE().getY() && op.getY() < node.getSE().getY()+node.getSE().getH()) 
                insertParticle(node.getSE(),op);
            
            if(e.getX() > node.getNW().getX() && e.getX() < node.getNW().getX() + node.getNW().getW() && e.getY() > node.getNW().getY() && e.getY() < node.getNW().getY()+node.getNW().getH()) 
                insertParticle(node.getNW(),e);
            else if(e.getX() > node.getNE().getX() && e.getX() < node.getNE().getX() + node.getNE().getW() && e.getY() > node.getNE().getY() && e.getY() < node.getNE().getY()+node.getNE().getH())
                insertParticle(node.getNE(),e);
            else if(e.getX() > node.getSW().getX() && e.getX() < node.getSW().getX()+node.getSW().getW() && e.getY() > node.getSW().getY() && e.getY() < node.getSW().getY()+node.getSW().getH())
                insertParticle(node.getSW(),e);
            else if(e.getX() > node.getSE().getX() && e.getX() < node.getSE().getX()+node.getSE().getW() && e.getY() > node.getSE().getY() && e.getY() < node.getSE().getY()+node.getSE().getH()) 
                insertParticle(node.getSE(),e);   
            
            node.setEntity(null); 
        }
    }
    
    public void Traverse(QTreeNode node){ // for debugging

        if(node.getParent() != null){
        System.out.println("level " + node.getLevel() + " parent dir " + node.getParent().getDir()+ " dir " + node.getDir() + " mass "+ node.getM());
        }else System.out.println("level " + node.getLevel() + " parent dir null " + " dir " + node.getDir() + " mass "+ node.getM());
        
        if(node.getNW() != null){
            
            Traverse(node.getNW());
            Traverse(node.getNE());
            Traverse(node.getSW());
            Traverse(node.getSE());
        }       
    }
    
     public QTreeNode getRoot(){
        return root;
    }
}
