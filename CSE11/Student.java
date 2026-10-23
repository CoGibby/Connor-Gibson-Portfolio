public class Student{
    private String name;
    private Course[] courseList; 

    public Student(){
        // name = ""; 
        this("");
    }

    public Student(String name){
        this.name = name; 
    }

    public String getName(){
        return name; 
    }

    public void addCourse(Course course){
        //TODO 
    }
}