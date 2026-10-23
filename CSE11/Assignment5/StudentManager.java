///////////////////////////////////////////////////////////////////////////////
// Author:            Connor Gibson
// Email:             cogibson@ucsd.edu
// Class:             CSE 11 Winter 2026
// Instructor's Name: Ben Ochoa
///////////////////////////////////////////////////////////////////////////////

//
// NOTE: YOU SHOULD NOT HAVE TO IMPORT ANY PACKAGES TO COMPLETE THIS
// ASSIGNMENT.
// 
// If you add extraneous packages (intentionally or not), then your
// code might fail Gradescope compilation.
//

/**
 * This class manages the student profile
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public class StudentManager {

    private int currentYear;
    private UCSDDatabase database;
    private static final int DEFAULT_YEAR = 2000;
    private static final String STANDING_FRESHMAN = "freshman";
    private static final String STANDING_SOPHOMORE = "sophomore";
    private static final String STANDING_JUNIOR = "junior";
    private static final String STANDING_SENIOR = "senior";

    /**
    * Creates a StudentManager object with default values
    */
    public StudentManager() {
        currentYear = DEFAULT_YEAR;
        database = new UCSDDatabase();
    }

    /**
    * Creates a student manager object with passed in values
    * 
    * @param givenYear The year passed in 
    * @param initialCapacity The capacity of the database
    */
    public StudentManager(int givenYear, int initialCapacity) {
        currentYear = givenYear; 
        database = new UCSDDatabase(initialCapacity);
    }

    /**
    * Adds a student to the database
    *
    * @param studentToAdd Adds that name to the student database
    */
    public void addStudentToDatabase(Student studentToAdd) {
        database.addStudent(studentToAdd);
    }

    /**
    * Getter of the size variable
    *
    * @param name The name of the student 
    * @param givenOccupation The occupation of the graduated student
    * @return A boolean variable representing if the degree is completed or not
    */
    public boolean completeDegree(String name, String givenOccupation) {
        Student s = database.findStudentByName(name);
        if (s == null){
            return false;
        }
        if (!s.getStanding().toLowerCase().equals(STANDING_SENIOR)){
            return false; 
        }
        s.setStanding(null);
        s.setOccupation(givenOccupation);
        s.setIsGraduated(true);
        
        return true;
    }

    /**
    * Updates the academic year, adding one to everyones age
    * and moving their standing up one 
    */
    public void newAcademicYearUpdate() {
        currentYear++;
        String standing = "";
        Student[] all = database.getAllStudents();
        for (int i = 0; i < all.length; i++){
            standing = all[i].getStanding();
            all[i].setAge(all[i].getAge() + 1);
            if (all[i].getStanding() == null){
                continue;
            }
            if (standing.equalsIgnoreCase(STANDING_FRESHMAN)){
                all[i].setStanding(STANDING_SOPHOMORE);
            } else if (standing.equalsIgnoreCase(STANDING_SOPHOMORE)){
                all[i].setStanding(STANDING_JUNIOR);
            } else if (standing.equalsIgnoreCase(STANDING_JUNIOR)){
                all[i].setStanding(STANDING_SENIOR);
            }
        }
    }

    /**
    * Returns a new list of only a certain standing
    *
    * @param standing The desired standing of students to be returned
    * @return A student database of a specific standing
    */
    public Student[] findStudentsByStanding(String standing) {
        if (!standing.equalsIgnoreCase("freshman")
            && !standing.equalsIgnoreCase("sophomore")
            && !standing.equalsIgnoreCase("junior")
            && !standing.equalsIgnoreCase("senior")){
            return new Student[0];
        }
        Student[] all = database.getAllStudents();
        int count = 0;
        for (int i = 0; i < all.length; i++){
            if (all[i].getStanding() != null 
                && all[i].getStanding().equalsIgnoreCase(standing)){
                count++;
            }
        }
        if (count == 0){
            return new Student[0];
        }
        Student[] standingOnly = new Student[count];
        int idx = 0; 

        for (int j = 0; j < all.length; j++){
            if (all[j].getStanding() != null
                && all[j].getStanding().equalsIgnoreCase(standing)){
                standingOnly[idx] = all[j];
                idx++;
            }
        }
        return standingOnly;
    }

    /**
    * Gets the average age of all students in a database
    *
    * @return The average age, rounded to the nearest integer
    */
    public int getCurrentAverageAge() {
        int sum = 0; 
        int count = 0;
        Student[] all = database.getAllStudents();
        for (int i = 0; i < all.length; i++){
            if (all[i].isGraduated()){
                continue;
            } else {
                sum += all[i].getAge();
                count++;
            }
        }
        if (count == 0){
            return 0;
        } else {
            return (int) Math.round( (double) sum / count);
        }
    }

    // TODO: Add more unit tests to ensure correctness of methods.
    /**
    * Unit tests to ensure accuracy of the program
    *
    * @return A boolean, representing if the tests passed or failed
    */
    @SuppressWarnings("checkstyle:MagicNumber") // DO NOT CHANGE THIS LINE!!!
    public static boolean unitTests() {
        boolean passed = true;
        int age = 0;
        // Test case 1
        StudentManager manager1 = new StudentManager();
        Student studentToAdd = new Student("Buddy", STANDING_FRESHMAN, 18);
        manager1.addStudentToDatabase(studentToAdd);
        manager1.newAcademicYearUpdate();
        if (manager1.getCurrentAverageAge() != 19) {
            System.out.println("StudentManager Test 1 FAILED");
            passed = false;
        }

        // Test case 2
        StudentManager manager2 = new StudentManager();
        Student studentToAdd2 = new Student("Zach", STANDING_SOPHOMORE, 23);
        manager2.addStudentToDatabase(studentToAdd2);
        manager2.newAcademicYearUpdate();
        age = manager2.getCurrentAverageAge();
        if (manager2.completeDegree("Zach", "Student") != false){
            passed = false;
        }
        Student[] result = 
            manager2.findStudentsByStanding(studentToAdd2.getStanding());
        if (result.length != 1 || !result[0].getName().equals("Zach")) {
            System.out.println("StudentManager Test 2 FAILED");
            passed = false;
        }


        // Test case 3
        StudentManager manager3 = new StudentManager(2026, 1);
        Student studentToAdd3 = new Student("Ronald", STANDING_SENIOR, 21);
        manager3.addStudentToDatabase(studentToAdd3);
        age = manager3.getCurrentAverageAge();
        if (manager3.completeDegree(studentToAdd3.getName(), 
            "Hockey player") != true) {
            System.out.println("StudentManager Test 3 FAILED");
            passed = false;
        }
        Student[] result2 = 
            manager3.findStudentsByStanding("senior");
        if (result2.length != 0) {
            System.out.println("StudentManager Test 3 FAILED");
            passed = false;
        }

        // Test case 4 — specifically covers newAcademicYearUpdate
        StudentManager manager4 = new StudentManager();
        Student s4 = new Student("Lily", STANDING_FRESHMAN, 18);
        manager4.addStudentToDatabase(s4);
        if (manager4.completeDegree("Lily", "Therapist") == true){
            passed = false;
        }
        manager4.newAcademicYearUpdate();
        if (s4.getAge() != 19 || !s4.getStanding().equals(STANDING_SOPHOMORE)) {
            System.out.println("StudentManager Test 4 FAILED");
            passed = false;
        }
        Student[] result3 = 
            manager4.findStudentsByStanding(s4.getStanding());
        if (result3.length != 1 || !result3[0].getName().equals("Lily")) {
            System.out.println("StudentManager Test 4 FAILED");
            passed = false;
        }
        return passed;
    }

    /**
    * Takes the return value of unit tests and provides 
    * a corresponding response
    *
    * @param args The argument of a string array
    */
    public static void main(String[] args) {
        if (unitTests()) {
            System.out.println("StudentManager: All unit tests passed.\n");
        } else {
            System.out.println("StudentManager: ERROR: Failed test.\n");
            return;
        }
    }
}