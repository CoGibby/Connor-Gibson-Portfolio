public class Circle extends GeometricObject{
    private double radius; 

    public Circle(){
        // super();

    }

    public Circle(double radius){
        // super(); 
        this.radius = radius; 
    }

    public Circle(double radius, String color, boolean filled){
        // super();
        //setColor(color);
        //setFilled(filled);
        super( color, filled);
        this.radius = radius;
    }

    public double getRadius(){
        return radius; 
    }

    public void setRadius(double radius){
        this.radius = radius; 
    }

    public double getArea(){
        return Math.PI * radius * radius;
    }

    public double getPerimeter(){
        return 2 * radius * Math.PI;
    }

    public double getDiameter(){
        return 2 * radius;
    }


    @Override
    public String toString(){
        return super.toString() + "\n Circle \n" + radius;
    }


    public static void main(String[] args){
        Circle circ = new Circle(3.5, "black", true);
        System.out.println(circ.toString());
    }

}