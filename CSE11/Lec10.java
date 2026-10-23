public class Lec10 {

    public static void main(String[] args){
        Circle circle1 = null; 
        System.out.println(circle1);
        circle1 = new Circle();
        System.out.println(Circle.getNumberOfObjects());

        System.out.println(circle1 + " circle of radius " + circle1.getRadius() + " has area " + circle1.getArea());
        circle1.setRadius(10.0);
        System.out.println(circle1 + " circle of radius " + circle1.getRadius() + " has area " + circle1.getArea());

        System.out.println();

        Circle circle2 = new Circle(5.0);
        System.out.println(circle2 + " circle of radius " + circle2.getRadius() + " has area " + circle2.getArea());

        //circle1 = circle2; 
        System.out.println(Circle.getNumberOfObjects());

    }
}