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
 * This class contains a database of student profiles
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public class UCSDDatabase {
    private int capacity;
    private int size;
    private Student[] students;

    private static final int INITIAL_CAPACITY = 10;
    private static final int RESIZE_FACTOR = 2;
    private static final String STANDING_FRESHMAN = "freshman";
    private static final String STANDING_SOPHOMORE = "sophomore";
    private static final String STANDING_JUNIOR = "junior";
    private static final String STANDING_SENIOR = "senior";

    /**
    * Creates a database, setting varaibles to default values
    */
    public UCSDDatabase() {
        capacity = INITIAL_CAPACITY; 
        students = new Student[capacity];
        size = 0; 
    }

    /**
    * Creates a database given a capacity parameter
    *
    * @param initialCapacity The capacity of the database
    */
    public UCSDDatabase(int initialCapacity) {
        if (initialCapacity <= 0){
            capacity = INITIAL_CAPACITY; 
        } else {
            capacity = initialCapacity;
        }
        students = new Student[capacity];
        size = 0;
    }

    /**
    * Makes sure the capacity isn't full, and resizes if so
    *
    */
    private void ensureCapacity() {
        boolean isFull = false; 
        if (size == capacity){
            isFull = true; 
        }
        Student[] studentsUpdated;
        if (isFull){
            capacity *= RESIZE_FACTOR; 
            studentsUpdated = new Student[capacity];
            for (int j = 0; j < size; j++){
                studentsUpdated[j] = students[j];
            }
            students = studentsUpdated; 
        }
    }

    /**
    * Adds a student to a database
    *
    * @param studentToAdd The name of the student to add
    */
    public void addStudent(Student studentToAdd) {
        boolean duplicate = false; 
        for (int i = 0; i < size; i++){
            if (students[i].getName().equals(studentToAdd.getName())){
                duplicate = true; 
            }
        }
        if (size == capacity){
            ensureCapacity(); 
        }

        if (duplicate){
            return;
        }
        
        for (int j = 0; j < size; j++){
            if (studentToAdd.getName().compareTo(students[j].getName()) < 0){
                for (int k = size - 1; k >= j; k--) {
                    students[k + 1] = students[k];
                }
                students[j] = studentToAdd;
                size++;
                return;
            }
        } 
        students[size] = studentToAdd;
        size++;
    }

    /**
    * Removes a student from a database
    *
    * @param studentToRemove The name of the student to remove
    */
    public void removeStudent(Student studentToRemove) {
        boolean nameExist = false; 
        int index = 0; 
        for (int i = 0; i < size; i++){
            if (students[i].getName().equals(studentToRemove.getName())){
                nameExist = true; 
                index = i; 
                break;
            }
        }
        if (!nameExist){
            return;
        }
        for (int k = index; k < size - 1; k++){
            students[k] = students[k + 1];
        }
        students[size - 1] = null;
        size--; 
    }

    /**
    * Finds a student in the students database
    *
    * @param name The name of the student to find
    * @return The student if found, or null if not
    */
    public Student findStudentByName(String name) { 
        for (int i = 0; i < size; i++){
            if (students[i].getName().equals(name)){
                return students[i]; 
            }
        }
        return null; 
    }

    /**
    * Getter of the size variable
    *
    * @return The size, as an integer
    */
    public int getSize() {
        return size;
    }

    /**
    * Getter of the capacity variable
    *
    * @return The capacity, as an integer
    */
    public int getCapacity() {
        return capacity;
    }

    /**
    * Gets all students in a database, after removing null
    *
    * @return The student database
    */
    public Student[] getAllStudents() {
        if (size == 0){
            return new Student[0];
        }
        Student[] allStudents = new Student[size];
        for (int i = 0; i < size; i++){
            allStudents[i] = students[i];
        }
        return allStudents; 
    }

    /**
    * Makes sure all students and their respective profiles are valid entries
    *
    * @return If it is valid, return true, else, return false
    */
    public boolean validateDatabase() {
        for (int i = 0; i < size; i++){
            if (students[i].getAge() <= 0){
                return false; 
            }
            String standing = students[i].getStanding();
            if (students[i].isGraduated() != true && 
                (!standing.toLowerCase().equals(STANDING_FRESHMAN) 
                && !standing.toLowerCase().equals(STANDING_SOPHOMORE)
                && !standing.toLowerCase().equals(STANDING_JUNIOR)
                && !standing.toLowerCase().equals(STANDING_SENIOR))){
                return false;
            }
            if (students[i].isGraduated() == true 
                && students[i].getStanding() != null){
                return false; 
            }
            if (students[i].isGraduated() == true && 
                (students[i].getOccupation() == null 
                || students[i].getOccupation().equals(""))){
                return false; 
            }
            
        }
        
        return true;
    }

    // TODO: Add more unit tests to ensure correctness of methods.
    /**
    * Unit tests to ensure accuracy of the program
    *
    * @return A boolean, representing if the tests pass or fail
    */
    @SuppressWarnings("checkstyle:MagicNumber") // DO NOT CHANGE THIS LINE!!!
    public static boolean unitTests() {
        boolean passed = true;
        // Test 1
        UCSDDatabase db1 = new UCSDDatabase();
        Student studentToAdd = new Student("Buddy", "freshman", 1);
        db1.addStudent(studentToAdd);
        if (db1.getSize() != 1) {
            System.out.println("UCSDDatabase Test 1 FAILED: "
                                + "Add student size check");
            passed = false;
        }

        // Test 2
        UCSDDatabase db2 = new UCSDDatabase();
        Student studentToAdd2 = new Student("Buddy", "freshman", 1);
        db2.addStudent(studentToAdd2);
        Student studentToRemove = new Student("Donald", "freshman", 1);
        db2.addStudent(studentToRemove);
        db2.removeStudent(studentToRemove);
        if (db2.getCapacity() != UCSDDatabase.INITIAL_CAPACITY) {
            System.out.println("UCSDDatabase Test 2 FAILED: "
                                + "Add student size check");
            passed = false;
        }

        // Test 3
        UCSDDatabase db3 = new UCSDDatabase();
        Student studentToAdd3 = new Student("Buddy", "freshman", 1);
        db3.addStudent(studentToAdd3);
        Student studentToRemove2 = new Student("Zach", "freshman", 1);
        db3.addStudent(studentToRemove2);
        db3.removeStudent(studentToRemove2);
        if (db3.validateDatabase() != true) {
            System.out.println("UCSDDatabase Test 3 FAILED: "
                                + "Add student size check");
            passed = false;
        }

        // Test 4
        UCSDDatabase db4 = new UCSDDatabase();
        Student studentToAdd4 = new Student("Buddy", "freshman", 1);
        db4.addStudent(studentToAdd4);
        Student studentToRemove4 = new Student("Poodles", "sophomore", 1);
        db4.addStudent(studentToRemove4);
        db4.removeStudent(studentToRemove4);
        Student found = db4.findStudentByName("Buddy");
        if (found == null || !found.getName().equals("Buddy")) {
            System.out.println("UCSDDatabase Test 4 FAILED: "
                + "find student check");
            passed = false;
        }


        return passed;
    }

    /**
    * A response to the unitTest return, printing out a corresponding statement
    *
    * @param args The argument passed in
    */
    public static void main(String[] args) {
        if (unitTests()) {
            System.out.println("UCSDDatabase: All unit tests passed.\n");
        } else {
            System.out.println("UCSDDatabase: ERROR: Failed test.\n");
            return;
        }
    }
}