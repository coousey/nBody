package nBody;

public class RandomGenerator {
    
    public double PowRandom(double maxX, double pow){
		
        double maxY = Math.pow(maxX, pow);
        double x;
        double y;

        do{

            x = Math.random()*maxX;
            y = Math.random()*maxY;
        }
        while(y > Math.pow(x, pow));

        return x;
    }
	
    public double linearRandom(double maxX){

        double maxY = maxX;
        double x;
        double y;

        do{			
            x = Math.random()*maxX;
            y = Math.random()*maxY;
        }
        while(y > x);

        return x;
    }
}
