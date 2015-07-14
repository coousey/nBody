package nbody.pkg.accelerationCalculators;

import nbody.pkg.model.Point;

public class QTree {
    
    private QTreeNode root;

    public QTree(double minX, double minY, double maxX, double maxY) {

        this.root = new QTreeNode(minX, minY, maxX - minX, maxY - minY, null);
        this.root.setLevel(1);
    } 
    
    public void insertPoint(QTreeNode node, Point p){ 
        
        double m = node.getM() + p.getM();
        node.setMassCenterX((node.getMassCenterX()*node.getM() + p.getX()*p.getM())/m);
        node.setMassCenterY((node.getMassCenterY()*node.getM() + p.getY()*p.getM())/m);
        node.setM(m);

        if(node.getPoint() == null && node.getNW() == null){  // external epmty     
            node.setPoint(p);            
        }
        
        else if(node.getPoint() == null && node.getNW() != null ){ // internal empty
            
            if(p.getX() > node.getNW().getX() && p.getX() < node.getNW().getX() + node.getNW().getW() && p.getY() > node.getNW().getY() && p.getY() < node.getNW().getY()+node.getNW().getH()) 
                insertPoint(node.getNW(),p);
            else if(p.getX() > node.getNE().getX() && p.getX() < node.getNE().getX() + node.getNE().getW() && p.getY() > node.getNE().getY() && p.getY() < node.getNE().getY()+node.getNE().getH())
                insertPoint(node.getNE(),p);
            else if(p.getX() > node.getSW().getX() && p.getX() < node.getSW().getX()+node.getSW().getW() && p.getY() > node.getSW().getY() && p.getY() < node.getSW().getY()+node.getSW().getH())
                insertPoint(node.getSW(),p);
            else if(p.getX() > node.getSE().getX() && p.getX() < node.getSE().getX()+node.getSE().getW() && p.getY() > node.getSE().getY() && p.getY() < node.getSE().getY()+node.getSE().getH())  
                insertPoint(node.getSE(),p);               
        } 
        
        else if(node.getPoint() != null && node.getNW() == null){ // external full 
            
            Point op = node.getPoint(); 

            double x = node.getX();
            double y = node.getY();
            double w = node.getW()/2.0;
            double h = node.getH()/2.0;          
            
            node.setNW(new QTreeNode(x,y,w,h,node));
            node.setNE(new QTreeNode((x+w),y,w,h,node));
            node.setSW(new QTreeNode(x,(y+h),w,h,node));
            node.setSE(new QTreeNode((x+w),(y+h),w,h,node));                 
            
            if(op.getX() > node.getNW().getX() && op.getX() < node.getNW().getX() + node.getNW().getW() && op.getY() > node.getNW().getY() && op.getY() < node.getNW().getY()+node.getNW().getH()) 
                insertPoint(node.getNW(),op);  
            else if(op.getX() > node.getNE().getX() && op.getX() < node.getNE().getX() + node.getNE().getW() && op.getY() > node.getNE().getY() && op.getY() < node.getNE().getY()+node.getNE().getH())
                insertPoint(node.getNE(),op);
            else if(op.getX() > node.getSW().getX() && op.getX() < node.getSW().getX()+node.getSW().getW() && op.getY() > node.getSW().getY() && op.getY() < node.getSW().getY()+node.getSW().getH())
                insertPoint(node.getSW(),op);
            else if(op.getX() > node.getSE().getX() && op.getX() < node.getSE().getX()+node.getSE().getW() && op.getY() > node.getSE().getY() && op.getY() < node.getSE().getY()+node.getSE().getH()) 
                insertPoint(node.getSE(),op);
            
            if(p.getX() > node.getNW().getX() && p.getX() < node.getNW().getX() + node.getNW().getW() && p.getY() > node.getNW().getY() && p.getY() < node.getNW().getY()+node.getNW().getH()) 
                insertPoint(node.getNW(),p);
            else if(p.getX() > node.getNE().getX() && p.getX() < node.getNE().getX() + node.getNE().getW() && p.getY() > node.getNE().getY() && p.getY() < node.getNE().getY()+node.getNE().getH())
                insertPoint(node.getNE(),p);
            else if(p.getX() > node.getSW().getX() && p.getX() < node.getSW().getX()+node.getSW().getW() && p.getY() > node.getSW().getY() && p.getY() < node.getSW().getY()+node.getSW().getH())
                insertPoint(node.getSW(),p);
            else if(p.getX() > node.getSE().getX() && p.getX() < node.getSE().getX()+node.getSE().getW() && p.getY() > node.getSE().getY() && p.getY() < node.getSE().getY()+node.getSE().getH()) 
                insertPoint(node.getSE(),p);   
            
            node.setPoint(null); 
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
