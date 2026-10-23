public class Circle{

    private double radius; 
    private static int numberOfObjects = 0;

    public Circle(){
        radius = 1.0; 
        numberOfObjects++; 
    }

    public Circle(double newRadius){
        radius = newRadius;
        numberOfObjects++; 

    }

    public double getRadius(){
        return radius; 
    }

    public void setRadius(double newRadius){
        radius = newRadius;
    }

    public double getArea(){
        return Math.PI * radius * radius;
    }

    public static int getNumberOfObjects(){
        return numberOfObjects; 
    }

}

