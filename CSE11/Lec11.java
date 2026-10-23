public class Lec11 {
    public static void main(String[] args){
        Student student1 = new Student("Johny doe"); 
        Course course = new Course();
        course.addStudent(student1);

        System.out.println();
        course.addStudent( new Student("Janey doe"));

        System.out.println();
        Double d = Double.valueOf(6.78);
        // Double d = 6.78;
        System.out.println( d);

        double x = Double.valueOf("9.987");
        System.out.println(x);

        System.out.println();
        String s1 = "Java";
        System.out.println(s1);
        String s2 = new String("Java");
        System.out.println(s2);
        String s3 =  "Java";
        System.out.println(s3);
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
    }




}