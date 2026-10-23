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

// TODO: Complete class header
/**
 * Three methods that go through various selections
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public class Selections {

    // Feel free to create extra variables if necessary.

    /* Variables used for carpool */
    private static final int SUNDAY = 0;
    private static final int SATURDAY = 6;
    private static final int HOUR_START = 0;
    private static final int HOUR_LAST = 11;
    private static final int HOUR_TWO = 2;
    private static final int HOUR_SEVEN = 7;
    private static final int DAY_START = 0;
    private static final int DAY_LAST = 6;
    private static final String CARPOOL_INVALID_INPUT_ERR = 
                            "Invalid input provided";
    private static final String CARPOOL_CANCEL_MSG =
                            "Carpool canceled. Match chance is too low";
    private static final String CARPOOL_MATCHED_BASE =
                            "Information entered. Match chance: ";
    private static final String CHANCE_HIGH = "high";
    private static final String CHANCE_MEDIUM = "medium";

    /* Variables used for permitPrice */
    private static final String PERMIT_S = "S";
    private static final String PERMIT_A = "A";
    private static final String PERMIT_V = "V";
    private static final String PERMIT_NW = "NW";
    private static final String ID_STUDENT = "STUDENT";
    private static final String ID_FACULTY = "FACULTY";
    private static final String ID_VISITOR = "VISITOR";
    private static final double PERMIT_PRICE_S = 6.3;
    private static final double PERMIT_PRICE_A = 7.7;
    private static final double PERMIT_PRICE_V = 36.0;
    private static final double PERMIT_PRICE_NW = 4.7;
    private static final double PERMIT_DISCOUNT_SAVING = 0.25;

    /* Variables used for validEmailAndPassword */
    private static final String UCSD_DOMAIN_1 = "@ucsd.edu";
    private static final String UCSD_DOMAIN_2 = "@eng.ucsd.edu";
    private static final String DOMAIN_ERR =
                            "Invalid email address: Incorrect domain";
    private static final int MIN_PW_LENGTH = 8;
    private static final String PW_LEN_ERR =
                            "Invalid password: too short";
    private static final String PW_PREV_ERR =
                            "Invalid password: same as previous password";
    private static final String PW_USERNAME_ERR =
                            "Invalid password: contains username";
    private static final String VALID_EMAIL_AND_PW = 
                            "Valid email address and password";

    // TODO: complete method header and method body
    /**
    * Creating a carpooling app to reduce transportation costs
    *
    * @param dayOfWeek Which day of the week (int; 0-6)
    * @param hourOfDay The hour of day (int; 0-11)
    * @param amPM Whether it is am or pm 
    * @return A string representing the match chance
    */
    public static String carpool(int dayOfWeek, int hourOfDay, String amPM) {
        String loweramPM = amPM.toLowerCase();
        String chance = "";
        if ((loweramPM.equals("am") || loweramPM.equals("pm")) 
            && (hourOfDay >= SUNDAY && hourOfDay <= HOUR_LAST) 
            && (dayOfWeek >= SUNDAY && dayOfWeek < HOUR_SEVEN)){ 
            if (loweramPM.equals("am") && (hourOfDay <= HOUR_SEVEN
                && hourOfDay >= HOUR_TWO)){
                return "Carpool canceled. Match chance is too low"; 
            } else if (dayOfWeek < SATURDAY && dayOfWeek > SUNDAY){
                return "Information entered. Match chance: medium";
            } else if (dayOfWeek == SUNDAY || dayOfWeek == SATURDAY){
                return "Information entered. Match chance: high";
            }
        }
        return "Invalid input provided";
    }

    // TODO: complete method header and method body
    /**
    * Calculate the price of the UCSD parking permit
    *
    * @param numCar The number of cars paying for a permit (int)
    * @param permitType The type of permit being payed for (String)
    * @param identity The type of person paying for the permit (String)
    * @param hasDiscount If there is a discount (boolean)
    * @return A double representing the total price
    */
    public static double permitPrice(int numCar, String permitType, 
        String identity, boolean hasDiscount) {
        double pricePerCar = 0; 
        if (numCar < 0 || (permitType != "S" && permitType != "V" 
            && permitType != "A" && permitType != "NW") || 
            (identity != "STUDENT" && identity != "VISITOR" 
            && identity != "FACULTY")){
            return -1.0; 
        } else if ((identity == "STUDENT" && permitType == "A") || 
            (identity == "FACULTY" && permitType == "S") || 
            (identity == "VISITOR" && (permitType == "S" 
            || permitType == "A" || permitType == "NW"))){
            return -1.0;  
        } else if (identity == "STUDENT" && permitType == "V"){ 
            permitType = "S"; 
        } else if (identity == "FACULTY" && permitType == "V"){
            permitType = "A";
        } 
        if (permitType == "S"){ 
            pricePerCar = PERMIT_PRICE_S;
        } else if (permitType == "A"){ 
            pricePerCar = PERMIT_PRICE_A; 
        } else if (permitType == "V"){ 
            pricePerCar = PERMIT_PRICE_V; 
        } else if (permitType == "NW"){ 
            pricePerCar = PERMIT_PRICE_NW; 
        }
        double totalCost = numCar * pricePerCar;
        if (hasDiscount){ 
            totalCost -= totalCost * PERMIT_DISCOUNT_SAVING; 
        }
        return totalCost; 
    }

    // TODO: complete method header and method body
    /**
    * See if an email is ucsd approved, as well as the new password
    * meeting certain criteria
    *
    * @param emailAddr The raw email address (string)
    * @param password The password of the email (string)
    * @param prevPassword The previous password of the email (string)
    * @return A booleanis a password is valid or invald
    */
    public static boolean validEmailAndPassword(String emailAddr, 
        String password, String prevPassword) {
        int idxDomain = emailAddr.indexOf('@'); 
        String domainAddr = emailAddr.substring(idxDomain);
        String username = emailAddr.substring(0, idxDomain);
        if (!domainAddr.equals("@ucsd.edu") && 
            !domainAddr.equals("@eng.ucsd.edu")){
            System.out.print("Invalid email address: Incorrect domain");
            return false;
        } else if (username.trim().isEmpty()){
            System.out.print("Invalid email address: Empty username");
            return false;
        } else if (password.length() < MIN_PW_LENGTH){ 
            System.out.print("Invalid password: too short");
            return false;
        } else if (password.equals(prevPassword)){ 
            System.out.print("Invalid password: same as previous password");
            return false;
        } else if (password.toLowerCase().contains(username.toLowerCase())){ 
            System.out.print("Invalid password: contains username");
            return false;
        } 
        System.out.print("Valid email address and password");
        return true;
    }

    // TODO: Add more unit tests to ensure correctness of methods.
    // TODO: Add method headers for unitTests
    /**
    * Tests cases for each of the three methods
    * 
    * @return A boolean value representing whether the tests pass or fail
    */
    @SuppressWarnings("checkstyle:MagicNumber") // DO NOT CHANGE THIS LINE
    public static boolean unitTests() {
        System.out.println();

        // Test(s) for carpool
        // Test case 1: dayOfWeek = 5, hourOfDay = 11
        int day1 = 5;
        int hour1 = 11;
        String amPM1 = "am";
        String expectedCarpool1 =
                "Information entered. Match chance: medium";
        String actualCarpool1 = carpool(day1, hour1, amPM1);
        System.out.println("carpool Output 1: " + actualCarpool1);
        if (!actualCarpool1.equals(expectedCarpool1)) {
            System.out.println("FAILED: carpool 1");
            return false;
        }
        System.out.println();
        
        // Test case 2: dayOfWeek = 0, hourOfDay = 2
        int day2 = 0;
        int hour2 = 2;
        String amPM2 = "aM";
        String expectedCarpool2 =
                "Carpool canceled. Match chance is too low";
        String actualCarpool2 = carpool(day2, hour2, amPM2);
        System.out.println("carpool Output 2: " + actualCarpool2);
        if (!actualCarpool2.equals(expectedCarpool2)) {
            System.out.println("FAILED: carpool 2");
            return false;
        }
        System.out.println();
        
        // Test case 3: dayOfWeek = 6, hourOfDay = 10
        int day3 = 6;
        int hour3 = 10;
        String amPM3 = "PM";
        String expectedCarpool3 =
                "Information entered. Match chance: high";
        String actualCarpool3 = carpool(day3, hour3, amPM3);
        System.out.println("carpool Output 3: " + actualCarpool3);
        if (!actualCarpool3.equals(expectedCarpool3)) {
            System.out.println("FAILED: carpool 3");
            return false;
        }
        System.out.println();

        // Test(s) for permitPrice
        // Test case 1: numCar = 2, permitType = "S", 
        // couponCode = "STUDENT", hasDiscount = true
        int numCar1 = 2;
        String permitType1 = "S";
        String identity1 = "STUDENT";
        boolean hasDiscount1 = true;
        String expectedPrice1 = "9.45";
        String actualPrice1 = String.format("%.2f", permitPrice(numCar1, 
            permitType1, identity1, hasDiscount1));
        System.out.println("permitPrice Output 1: " + actualPrice1);
        if (!actualPrice1.equals(expectedPrice1)) {
            System.out.println("FAILED: permitPrice 1");
            return false;
        }
        // Test case 2: numCar = 1, permitType = "NW", 
        // couponCode = "None", hasDiscount = false
        int numCar2 = 1;
        String permitType2 = "NW";
        String identity2 = "FACULTY";
        boolean hasDiscount2 = false;
        String expectedPrice2 = "4.70";
        String actualPrice2 = String.format("%.2f", permitPrice(numCar2, 
            permitType2, identity2, hasDiscount2));
        System.out.println("permitPrice Output 2: " + actualPrice2);
        if (!actualPrice2.equals(expectedPrice2)) {
            System.out.println("FAILED: permitPrice 2");
            return false;
        }
        // Test case 3: numCar = 4, permitType = "V", 
        // couponCode = "VISITOR", hasDiscount = true
        int numCar3 = 4;
        String permitType3 = "V";
        String identity3 = "VISITOR";
        boolean hasDiscount3 = true;
        String expectedPrice3 = "108.00";
        String actualPrice3 = String.format("%.2f", permitPrice(numCar3, 
            permitType3, identity3, hasDiscount3));
        System.out.println("permitPrice Output 3: " + actualPrice3);
        if (!actualPrice3.equals(expectedPrice3)) {
            System.out.println("FAILED: permitPrice 3");
            return false;
        }

        // Test(s) for validEmailAndPassword
        // Test case 1: emailAddr = "jdoe123@ucsd.edu", 
        // password = "pw13579abc", prevPassword = "PW13579ABC"
        String emailAddr1 = "jdoe123@ucsd.edu";
        String password1 = "pw13579abc";
        String prevPassword1 = "PW13579ABC";
        boolean expectedAccountReturn1 = true;
        String expectedAccountPrint1 = "Valid email address and password";
        System.out.println("Expected validEmailAndPassword Print 1:");
        System.out.println(expectedAccountPrint1);
        System.out.println("-----------------");
        System.out.println("Actual validEmailAndPassword Print 1:");
        boolean actualAccountReturn1 = validEmailAndPassword(emailAddr1, 
            password1, prevPassword1);
        System.out.println();
        if (actualAccountReturn1 != expectedAccountReturn1) {
            System.out.println("FAILED: validEmailAndPassword 1");
            return false;
        }
        System.out.println();
        // Test case 2: emailAddr = "jdoe123@gmail.com", 
        // password = "pw13579abc", prevPassword = "PW13579ABC"
        String emailAddr2 = "jdoe123@gmail.com";
        String password2 = "pw13579abc";
        String prevPassword2 = "PW13579ABC";
        boolean expectedAccountReturn2 = false;
        String expectedAccountPrint2 = 
            "Invalid email address: Incorrect domain";
        System.out.println("Expected validEmailAndPassword Print 2:");
        System.out.println(expectedAccountPrint2);
        System.out.println("-----------------");
        System.out.println("Actual validEmailAndPassword Print 2:");
        boolean actualAccountReturn2 = validEmailAndPassword(emailAddr2, 
            password2, prevPassword2);
        System.out.println();
        if (actualAccountReturn2 != expectedAccountReturn2) {
            System.out.println("FAILED: validEmailAndPassword 2");
            return false;
        }
        // Test case 3: emailAddr = "jdoe123@eng.ucsd.edu", 
        // password = "123abc", prevPassword = "PW13579ABC"
        String emailAddr3 = "jdoe123@eng.ucsd.edu";
        String password3 = "123abc";
        String prevPassword3 = "PW13579ABC";
        boolean expectedAccountReturn3 = false;
        String expectedAccountPrint3 = "Invalid password: too short";
        System.out.println("Expected validEmailAndPassword Print 3:");
        System.out.println(expectedAccountPrint3);
        System.out.println("-----------------");
        System.out.println("Actual validEmailAndPassword Print 3:");
        boolean actualAccountReturn3 = validEmailAndPassword(emailAddr3, 
            password3, prevPassword3);
        System.out.println();
        if (actualAccountReturn3 != expectedAccountReturn3) {
            System.out.println("FAILED: validEmailAndPassword 3");
            return false;
        }
        System.out.println();
        // All test cases passed!
        return true;
    }
    // TODO: Complete the method header and the method body
    /**
    * Checks to see if unit tests is true or false, and prints 
    * a corresponding statement
    *
    * @param args Conventional value for arguments
    */
    @SuppressWarnings("checkstyle:MagicNumber") // DO NOT CHANGE THIS LINE
    public static void main(String[] args) {
        if (unitTests()) {
            System.out.println("All unit tests passed.\n");
        } else {
            System.out.println("ERROR: Failed test.\n");
            return;
        }
    }
}
