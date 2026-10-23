///////////////////////////////////////////////////////////////////////////////
// Author:            Connor Gibson
// Email:             cogibson@ucsd.edu
// Class:             CSE 11 Winter 2026
// Instructor's Name: Ben Ochoa
///////////////////////////////////////////////////////////////////////////////

/**
 * This class takes creates a student profile
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public class Student {
    private String name; 
    private String standing; 
    private int age; 
    private String occupation; 
    private boolean isGraduated;
    /**
    * Creates a student object with default values
    */
    public Student(){
        name = null; 
        standing = null; 
        age = 0; 
        occupation = null; 
        isGraduated = false; 
    }
    /**
    * Creates a student object with parameters passed in 
    * for the profile 
    *
    * @param name The name of the student
    * @param standing The standing of the student 
    * @param age The age of the student, as an integer
    */
    public Student(String name, String standing, int age){
        this.name = name; 
        this.standing = standing; 
        this.age = age; 
        this.occupation = null; 
        this.isGraduated = false; 
    }
    /**
    * Getter of the name variable
    *
    * @return The name, as an string
    */
    public String getName(){
        return name; 
    }
    /**
    * Getter of the standing variable
    *
    * @return The standing, as a string
    */
    public String getStanding(){
        return standing; 
    }
    /**
    * Getter of the age variable
    *
    * @return The age, as an integer
    */
    public int getAge(){
        return age; 
    }
    /**
    * Getter of the occupation variable
    *
    * @return The occupation, as a string
    */
    public String getOccupation(){
        return occupation;
    }
    /**
    * Getter of the graduated variable
    *
    * @return The status of graduation, as a boolean
    */
    public boolean isGraduated(){
        return isGraduated;
    }

    /**
    * Setter of the name variable
    *
    * @param name The name to update the current name variable
    */
    public void setName(String name){
        this.name = name; 
    }
    /**
    * Setter of the standing variable
    *
    * @param standing The standing to update the current standing variable
    */
    public void setStanding(String standing){
        this.standing = standing; 
    }
    /**
    * Setter of the age variable
    *
    * @param age The age to update the current age variable
    */
    public void setAge(int age){
        this.age = age;
    } 
    /**
    * Setter of the occupation variable
    *
    * @param occupation The name to update the current name variable
    */
    public void setOccupation(String occupation){
        this.occupation = occupation; 
    }
    /**
    * Setter of the graduated variable
    *
    * @param isGraduated Status of graduated to pass in
    */
    public void setIsGraduated(boolean isGraduated){
        this.isGraduated = isGraduated; 
    }

    // TODO: Add more unit tests to ensure correctness of methods.
    /**
    * Unit tests to ensure accuracy of program
    *
    * @return A boolean, representing if they pass or fail
    */
    @SuppressWarnings("checkstyle:MagicNumber") // DO NOT CHANGE THIS LINE!!!
    public static boolean unitTests() {
        boolean passed = true;
        // Test case 1
        Student student1 = new Student();
        student1.setAge(10);
        if (student1.getAge() != 10) {
            System.out.println("Student Test 1 FAILED");
            passed = false;
        }
        // Test case 2
        Student student2 = new Student("Carter", "freshman", 18);
        if (!student2.getStanding().equals("freshman")) {
            System.out.println("Student Test 2 FAILED");
            passed = false;
        }
        // Test case 3
        Student student3 = new Student("Carter", "senior", 22);
        student3.setOccupation("Doctor");
        student3.setIsGraduated(true);
        if (!student3.getOccupation().equals("Doctor")) {
            System.out.println("Student Test 3 FAILED");
            passed = false;
        }
        // Test case 4
        Student student4 = new Student("Carter", "senior", 22);
        if (!student4.getName().equals("Carter")) {
            System.out.println("Student Test 4 FAILED");
            passed = false;
        }
        return passed;
    }

    /**
    * The main method that prints a response 
    * corresponding to the unit tests return value
    *
    * @param args The argument passed into the main method
    */
    public static void main(String[] args) {
        if (unitTests()) {
            System.out.println("Student: All unit tests passed.\n");
        } else {
            System.out.println("Student: ERROR: Failed test.\n");
            return;
        }
    }
}
