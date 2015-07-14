//package nbody.pkg.drawers;
//
//import java.awt.Color;
//import java.awt.Graphics2D;
//import java.util.ArrayList;
//import nbody.pkg.model.Entity;
//import nbody.pkg.model.Particle;
//
//public class SimpleParticleDrawer extends EntityDrawer {
//    
//    @Override
//    public void draw(ArrayList<Entity> entityList, Graphics2D dImgG2D) {
//        
//        dImgG2D.setColor(Color.BLUE);
//        
//        for(Entity e: entityList){
//            Particle p = (Particle)e;
//            dImgG2D.fillOval((int)(p.getX()-p.getR()), (int)(p.getY()-p.getR()), // particle dot
//                    (int)(p.getR()*2), (int)(p.getR()*2));
//
//            if(p.isSelected()){
//                dImgG2D.drawOval((int)(e.getX()-2*p.getR()), (int)(p.getY()-2*p.getR()), // circle surroundig the particle dot
//                        (int)(2*(p.getR()*2)), (int)(2*(p.getR()*2))); 
//                
//                dImgG2D.setColor(Color.RED);
//                dImgG2D.drawString("nr  " +  p.getNr() , 10, 100);
//                dImgG2D.drawString("m   " +  p.getM() , 10, 120);
//                dImgG2D.drawString("Vx  " +  String.format("%.4f", p.getXV()) , 10, 140);
//                dImgG2D.drawString("Vy  " +  String.format("%.4f", p.getYV()) , 10, 160);
//                dImgG2D.drawString("Ax  " +  String.format("%.4f", p.getXA()) , 10, 180);
//                dImgG2D.drawString("Ay  " +  String.format("%.4f", p.getYA()) , 10, 200);
//                dImgG2D.drawString("x  " +  String.format("%.4f", p.getX()) , 10, 220);
//                dImgG2D.drawString("y  " +  String.format("%.4f", p.getY()) , 10, 240);
//                dImgG2D.setColor(Color.BLUE);
//            }
//        }
//    }
//}
