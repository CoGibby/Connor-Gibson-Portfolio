///////////////////////////////////////////////////////////////////////////////
// Author:            Connor Gibson
// Email:             cogibson@ucsd.edu
// Class:             CSE 11 Winter 2026
// Instructor's Name: Ben Ochoa
///////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;   // Import the Scanner class

//
// NOTE: YOU SHOULD NOT HAVE TO IMPORT ANY OTHER PACKAGES TO COMPLETE THIS
// ASSIGNMENT.
//
// If you add extraneous packages (intentionally or not), then your
// code might fail Gradescope compilation.
//

// TODO: Complete the class header (Update description, @author tags, etc.)
/**
 * Assignment 2
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public class Assignment2 {

    // Feel free to create extra constants if necessary.

    // Used to prompt the user for their name
    private static final String PROMPT_MSG_NAME = "Please enter your name.";
    private static final double SQUARE_POWER = 2; // Used for hypotenuseLength

    // Discount rate is 5%
    private static final double DISCOUNT_RATE_MULTIPLIER = 0.05;

    private static final int HOUR_START_INDEX = 0; // for addMinutesToTime
    private static final int HOUR_END_INDEX = 2; // for addMinutesToTime
    private static final int MINUTE_START_INDEX = 3; // for addMinutesToTime
    private static final int MINUTE_END_INDEX = 5; // for addMinutesToTime
    private static final int MINUTES_PER_HOUR = 60; // for addMinutesToTime
    private static final int HOURS_PER_DAY = 24; // for addMinutesToTime


    /**
    * Computes the volume of a hollow cylinder given the inner radius, 
    * outer radius, and height values. 
    *
    * @param radiusOuter Radius of the outer cylinder stored in a double 
    * @param radiusInner  Radius of the inner cylinder stored in a double
    * @param height Height of the cylinder stored in a double  
    * @return The volume of the hollow cylinder as a double  
    */
    public static double hollowCylinderVolume(double radiusOuter, 
                                            double radiusInner, 
                                            double height) {
        double volume =
                Math.PI
                * ((radiusOuter * radiusOuter) - (radiusInner * radiusInner))
                * height; 
        return volume;
    }

    /**
    * Calculates the travel cost between two points
    *  2 dimensionally given rates per unit of distance.  
    *
    * @param startX The starting x coordinate
    * @param startY The starting y coordinate 
    * @param endX The ending x coordinate 
    * @param endY The ending y coordinate 
    * @param xRate The cost rate per unit distance (x-axis)
    * @param yRate The cost rate per unit distance (y-axis)
    * @return Total travel cost in a double value
    */
    public static double travelCost(double startX, double startY,
                                    double endX, double endY,
                                    double xRate, double yRate) {
        double cost = 
            (Math.abs(startX - endX) * xRate)
            + (Math.abs(startY - endY) * yRate);
        return cost;
    }

    /**
    *Computes the sum of two fractions as a double
    *
    * @param a Numerator of first fraction
    * @param b Denominator of first fraction 
    * @param c Numerator of second fraction 
    * @param d Denominator of second fraction 
    * @return The sum of two fractions as a double 
    */
    public static double sumCorrectDivision(int a, int b, int c, int d) {
        double result = ((double) a / b) + ((double) c / d); 
        return result;
    }

    /**
    *Splits a string representing an integer into two parts, takes the abs
    * value of each part, adds them, appends it to the original string,
    * and return as an integer
    *
    * @param num String representing the int
    * @param a Starting index for the first substring 
    * @param b Ending index for the first substring 
    * @return The integer value of the original string + sum appended 
    */
    public static int appendAbsSum(String num, int a, int b) {
        String firstNum = num.substring(a,b);
        String secondNum = num.substring(b);
        int intFirstNum = Math.abs(Integer.parseInt(firstNum));
        int intSecondNum = Math.abs(Integer.parseInt(secondNum));
        int lastNum = intFirstNum + intSecondNum; 
        String intString = Integer.toString(lastNum);
        String strResult = num.concat(intString);
        int result = Integer.parseInt(strResult);
        return result;
    }

    /**
    *Concatenates two strings, while converting the first n characters 
    * to uppercase
    *
    * @param word1 First string
    * @param word2 Second string
    * @param n Number of characters to convert to uppercase (str) 
    * @return Concatenated string with first n characters in uppercase 
    */
    public static String concatAndUpperPrefix(String word1, String word2, 
                                            String n) {
        String concatWords = word1 + word2; 
        int intN = Integer.parseInt(n);
        String firstSubstring = concatWords.substring(0,intN).toUpperCase();
        String secondSubstring = concatWords.substring(intN);
        String result = firstSubstring + secondSubstring;
        return result;
    }

    /**
    *Finds the first case-insensitive occurence of b in a, replaces it 
    * with c, and appends the uppercase b to the end of the str
    *
    * @param a Original string
    * @param b Substring to search in a for
    * @param c String to replace first occurence of b in a 
    * @return The modified str after replacing + appending uppercase b 
    */
    public static String replaceAndAppend(String a, String b,
                                        String c) {
        String aLower = a.toLowerCase(); 
        String bLower = b.toLowerCase(); 
        int firstIdx = aLower.indexOf(bLower); 
        String substringA = a.substring(0, firstIdx);
        String substringEnd = a.substring(firstIdx + b.length()); 
        String answer = substringA + c + substringEnd + b.toUpperCase(); 
        return answer;
    }

    /**
    *Creates a string representation of a butterfly using '*' 
    *and a 9 column width
    *
    * @return A string containing the butterfly with newline characters 
    */
    public static String createButterfly() {
        String result =
                "*       *\n"
                + "**     **\n"
                + "***   ***\n"
                + "**** ****\n"
                + "*********\n"
                + "**** ****\n"
                + "***   ***\n"
                + "**     **\n"
                + "*       *\n";
        return result;
    }
    private static final int minutesPerHour = 60;
    private static final int hoursInDay = 24;
    private static final int totalMinutesInDay = hoursInDay * minutesPerHour;
    private static final int hh = 10;
    /**
    *Adds numbers to a given time, returning the new time 
    *in a format (leading zeros)
    *
    * @param hhmm Original time in HH:MM format
    * @param deltaMinutes Number of minutes to add to the time
    * @return New time after the minutes have been aded in the 
    * original format (str) 
    */
    public static String addMinutesToTime(String hhmm, int deltaMinutes) {
        int idxColon = hhmm.indexOf(':');
        String hours = hhmm.substring(0, idxColon);
        String minutes = hhmm.substring(idxColon + 1);
        int intHours = Integer.parseInt(hours);
        int intMinutes = Integer.parseInt(minutes);
        int totalMinutes = intHours*minutesPerHour+intMinutes + deltaMinutes; 
        totalMinutes = totalMinutes % totalMinutesInDay; 
        int newHours = totalMinutes / minutesPerHour; 
        int newMinutes = totalMinutes % minutesPerHour;
        String strNewHours = Integer.toString(newHours);
        String strNewMinutes = Integer.toString(newMinutes);
        if (newHours < hh) {
            strNewHours = "0" + strNewHours;
        }
        if (newMinutes < hh) {
            strNewMinutes = "0" + strNewMinutes;
        }
        String result = strNewHours + ':' + strNewMinutes;
        return result;
        

    }

    /**
    * Calculates the total of a trip given a base fare, miles traveled, 
    * the rate per mile, and a discount
    *
    * @param baseFare Original The base price (str)
    * @param miles The distance traveled (str)
    * @param ratePerMile The rate per mile (str)
    * @param discountUnits  Number of discount units (str)
    * @return Formatted string showing the subtotal, 
    * discount, and total (2 decimal rounding) 
    */
    public static String computeTripCost(String baseFare, String miles,
                                        String ratePerMile, 
                                        String discountUnits) {
        double Base = Double.parseDouble(baseFare);
        double Miles = Double.parseDouble(miles);
        double Rate = Double.parseDouble(ratePerMile);
        double subtotal = Base + (Miles * Rate);
        String strSubtotal = Double.toString(subtotal);
        double discount = Double.parseDouble(discountUnits);
        double discountRate = discount * DISCOUNT_RATE_MULTIPLIER; 
        String strDiscountRate = Double.toString(discountRate);
        double discountAmount = subtotal * discountRate; 
        double total = subtotal - discountAmount;
        String strTotal = Double.toString(total);

        String result = String.format(
            "Subtotal: %.2f, Discount: %.2f, Total: %.2f", 
            subtotal, discountAmount, total);
        return result;
    }

    /**
    * Lowercases an email address, removes dots from the local part, 
    * ignores characters
    * after '+' in the local part. Adds the domain to the end of 
    * the new local part.
    *
    * @param email Unchanged original email
    * @return Changed email address (str)
    */
    public static String normalizeEmail(String email) {
        String e = email.toLowerCase(); 
        int atIdx = e.indexOf('@');
        String localPart = e.substring(0, atIdx);
        String domainPart = e.substring(atIdx);
        int atPlus = localPart.indexOf('+'); 
        if (atPlus != -1) { 
            localPart = localPart.substring(0, atPlus);
        }
        localPart = localPart.replace(".", "");
        String result = localPart + domainPart;
        return result;
    }

    /**
     * Unit tests for the implemented methods.
     * TODO: Add at least two more distinct test cases for each method
     * (except createButterfly). Update expected values.
     * @return Whether all unit tests have passed (as a boolean)
     */
    @SuppressWarnings("checkstyle:MagicNumber") // DO NOT CHANGE THIS LINE!!!
    public static boolean unitTests() {
        System.out.println();

        boolean passed = true; // Keep track of overall test results

        // Test(s) for hollowCylinderVolume
        // Test case 1: (Using sample values)
        double rOuter = 6.0;
        double rInner = 4.0;
        double height = 5.0;
        String ans_vol1 = String.format("%.2f", 
            hollowCylinderVolume(rOuter, rInner, height));
        String expectedOutput_vol1 = "314.16";
        // TODO: Verify expected output after implementation
        System.out.println("hollowCylinderVolume Output 1: " + ans_vol1);
        if (!ans_vol1.equals(expectedOutput_vol1)) {
            System.out.println("FAILED: hollowCylinderVolume 1");
            passed = false;
        }
        System.out.println();

        // TODO: Add Test case 2 for hollowCylinderVolume
        double ans_vol2 = hollowCylinderVolume(10, 3, 2);
        String out_vol2 = String.format("%.2f", ans_vol2);
        String exp_vol2 = "571.77";
        System.out.println("hollowCylinderVolume Output 2: " + out_vol2);
        if (!out_vol2.equals(exp_vol2)) {
            System.out.println("FAILED: hollowCylinderVolume 2");
            passed = false;
        }
        System.out.println();

        // TODO: Add Test case 3 for hollowCylinderVolume
        double ans_vol3 = hollowCylinderVolume(5, 0, 10);
        String out_vol3 = String.format("%.2f", ans_vol3);
        String exp_vol3 = "785.40";
        System.out.println("hollowCylinderVolume Output 3: " + out_vol3);
        if (!out_vol3.equals(exp_vol3)) {
            System.out.println("FAILED: hollowCylinderVolume 3");
            passed = false;
        }
        System.out.println();

        // Test(s) for travelCost
        // Test case 1: (Using sample values similar to previous spec)
        double startX = 4.5;
        double startY = 3.7;
        double endX = 5.4;
        double endY = 7.3;
        double xRate = 10.0;
        double yRate = 0.8333333333;

        String ans_travel1 = String.format("%.2f",
                travelCost(startX, startY, endX, endY, xRate, yRate));
        String expectedOutput_travel1 = "12.00";
        // TODO: Verify expected output after implementation
        System.out.println("travelCost Output 1: " + ans_travel1);
        if (!ans_travel1.equals(expectedOutput_travel1)) {
            System.out.println("FAILED: travelCost 1");
            passed = false;
        }
        System.out.println();

        // TODO: Add Test case 2 for travelCost
        String ans_travel2 = String.format("%.2f",
                travelCost(0, 0, 10, 10, 1, 1));
        String expectedOutput_travel2 = "20.00";
        // TODO: Verify expected output after implementation
        System.out.println("travelCost Output 2: " + ans_travel2);
        if (!ans_travel2.equals(expectedOutput_travel2)) {
            System.out.println("FAILED: travelCost 2");
            passed = false;
        }
        System.out.println();
        // TODO: Add Test case 3 for travelCost
        String ans_travel3 = String.format("%.2f",
                travelCost(5, 5, 5, 1, 10, 2));
        String expectedOutput_travel3 = "8.00";
        // TODO: Verify expected output after implementation
        System.out.println("travelCost Output 3: " + ans_travel3);
        if (!ans_travel3.equals(expectedOutput_travel3)) {
            System.out.println("FAILED: travelCost 3");
            passed = false;
        }
        System.out.println();

        // Test(s) for sumCorrectDivision
        // Test case 1: (Using values similar to previous spec)
        int a_s = 10;
        int b_s = 7;
        int c_s = 2;
        int d_s = 3;

        double test1_sumDiv = sumCorrectDivision(a_s, b_s, c_s, d_s);
        String ans_sumDiv1 = String.format("%.3f", test1_sumDiv);
        String expectedOutput_sumDiv1 = "2.095"; // 10/7 + 2/3 ≈ 2.095...
        // TODO: Verify expected output after implementation
        System.out.println("sumCorrectDivision Output 1: " + ans_sumDiv1);
        if (!ans_sumDiv1.equals(expectedOutput_sumDiv1)) {
            System.out.println("FAILED: sumCorrectDivision 1");
            passed = false;
        }
        System.out.println();

        // TODO: Add Test case 2 for sumCorrectDivision
        double test2_sumDiv = sumCorrectDivision(1, 2, 1, 4);
        String ans_sumDiv2 = String.format("%.3f", test2_sumDiv);
        String expectedOutput_sumDiv2 = "0.750"; // 1/2 + 1/4 = 3/4...
        // TODO: Verify expected output after implementation
        System.out.println("sumCorrectDivision Output 2: " + ans_sumDiv2);
        if (!ans_sumDiv2.equals(expectedOutput_sumDiv2)) {
            System.out.println("FAILED: sumCorrectDivision 2");
            passed = false;
        }
        System.out.println();
        // TODO: Add Test case 3 for sumCorrectDivision
        double test3_sumDiv = sumCorrectDivision(2, 5, 0, 2);
        String ans_sumDiv3 = String.format("%.3f", test3_sumDiv);
        String expectedOutput_sumDiv3 = "0.400"; // 2/5 + 0/2 = 2/5...
        // TODO: Verify expected output after implementation
        System.out.println("sumCorrectDivision Output 3: " + ans_sumDiv3);
        if (!ans_sumDiv3.equals(expectedOutput_sumDiv3)) {
            System.out.println("FAILED: sumCorrectDivision 3");
            passed = false;
        }
        System.out.println();

        // Test(s) for appendAbsSum
        // Test case 1: (Sample)
        String num1 = "1234";
        int a1 = 0;
        int b1 = 2;
        int ans_append1 = appendAbsSum(num1, a1, b1);
        int expectedOutput_append1 = 123446;
        // TODO: Verify expected output after implementation
        System.out.println("appendAbsSum Output 1: " + ans_append1);
        if (ans_append1 != expectedOutput_append1) {
            System.out.println("FAILED: appendAbsSum 1");
            passed = false;
        }
        System.out.println();

        // TODO: Add Test case 2 for appendAbsSum
        int ans_append2 = appendAbsSum("5678", 1, 3);
        int expectedOutput_append2 = 567875;
        // TODO: Verify expected output after implementation
        System.out.println("appendAbsSum Output 2: " + ans_append2);
        if (ans_append2 != expectedOutput_append2) {
            System.out.println("FAILED: appendAbsSum 2");
            passed = false;
        }
        System.out.println();
        // TODO: Add Test case 3 for appendAbsSum
        int ans_append3 = appendAbsSum("9001", 0, 1);
        int expectedOutput_append3 = 900110;
        // TODO: Verify expected output after implementation
        System.out.println("appendAbsSum Output 3: " + ans_append3);
        if (ans_append3 != expectedOutput_append3) {
            System.out.println("FAILED: appendAbsSum 3");
            passed = false;
        }
        System.out.println();

        // Test(s) for concatAndUpperPrefix
        // Test case 1: (Sample)
        String word1 = "hello";
        String word2 = "world";
        String n = "5";
        String ans_concat1 = concatAndUpperPrefix(word1, word2, n);
        String expectedOutput_concat1 = "HELLOworld";
        // TODO: Verify expected output after implementation
        System.out.println("concatAndUpperPrefix Output 1: " + ans_concat1);
        if (!ans_concat1.equals(expectedOutput_concat1)) {
            System.out.println("FAILED: concatAndUpperPrefix 1");
            passed = false;
        }
        System.out.println();

        // TODO: Add Test case 2 for concatAndUpperPrefix
        String ans_concat2 = concatAndUpperPrefix("cat", "dog", "2");
        String expectedOutput_concat2 = "CAtdog";
        // TODO: Verify expected output after implementation
        System.out.println("concatAndUpperPrefix Output 2: " + ans_concat2);
        if (!ans_concat2.equals(expectedOutput_concat2)) {
            System.out.println("FAILED: concatAndUpperPrefix 2");
            passed = false;
        }
        System.out.println();
        // TODO: Add Test case 3 for concatAndUpperPrefix
        String ans_concat3 = concatAndUpperPrefix("Bob", "Ross", "5");
        String expectedOutput_concat3 = "BOBROss";
        // TODO: Verify expected output after implementation
        System.out.println("concatAndUpperPrefix Output 3: " + ans_concat3);
        if (!ans_concat3.equals(expectedOutput_concat3)) {
            System.out.println("FAILED: concatAndUpperPrefix 3");
            passed = false;
        }
        System.out.println();

        // Test(s) for replaceAndAppend
        // Test case 1: (Using sample values similar to previous spec)
        String base_r = "helloworld!!!";
        String sub_r = "World";
        String patch_r = "sara";
        String ans_replace1 = replaceAndAppend(base_r, sub_r, patch_r);
        String expectedOutput_replace1 = "hellosara!!!WORLD";
        // TODO: Verify expected output after implementation
        System.out.println("replaceAndAppend Output 1: " + ans_replace1);
        if (!ans_replace1.equals(expectedOutput_replace1)) {
            System.out.println("FAILED: replaceAndAppend 1");
            passed = false;
        }
        System.out.println();

        // TODO: Add Test case 2 for replaceAndAppend
        String ans_replace2 = replaceAndAppend("banana", "na", "XY");
        String expectedOutput_replace2 = "baXYnaNA";
        // TODO: Verify expected output after implementation
        System.out.println("replaceAndAppend Output 2: " + ans_replace2);
        if (!ans_replace2.equals(expectedOutput_replace2)) {
            System.out.println("FAILED: replaceAndAppend 2");
            passed = false;
        }
        System.out.println();
        // TODO: Add Test case 3 for replaceAndAppend
        String ans_replace3 = replaceAndAppend("professor", "fess", "teach");
        String expectedOutput_replace3 = "proteachorFESS";
        // TODO: Verify expected output after implementation
        System.out.println("replaceAndAppend Output 3: " + ans_replace3);
        if (!ans_replace3.equals(expectedOutput_replace3)) {
            System.out.println("FAILED: replaceAndAppend 3");
            passed = false;
        }
        System.out.println();

        // Note: createButterfly testing can be done manually or by adding
        // a test here comparing the output string, but it's not required
        // by the starter spec to have 3 automated tests here.
        System.out.println(createButterfly());

        // Test(s) for addMinutesToTime
        // Test case 1: (Using sample values)
        String hhmm1 = "23:50";
        int delta1 = 20;
        String ans_time1 = addMinutesToTime(hhmm1, delta1);
        String expectedOutput_time1 = "00:10";
        // TODO: Verify expected output after implementation
        System.out.println("addMinutesToTime Output 1: " + ans_time1);
        if (!ans_time1.equals(expectedOutput_time1)) {
            System.out.println("FAILED: addMinutesToTime 1");
            passed = false;
        }
        System.out.println();

        // TODO: Add Test case 2 for addMinutesToTime
        String ans_time2 = addMinutesToTime("13:30", 30);
        String expectedOutput_time2 = "14:00";
        // TODO: Verify expected output after implementation
        System.out.println("addMinutesToTime Output 2: " + ans_time2);
        if (!ans_time2.equals(expectedOutput_time2)) {
            System.out.println("FAILED: addMinutesToTime 2");
            passed = false;
        }
        System.out.println();
        // TODO: Add Test case 3 for addMinutesToTime
        String ans_time3 = addMinutesToTime("02:20", 30);
        String expectedOutput_time3 = "02:50";
        // TODO: Verify expected output after implementation
        System.out.println("addMinutesToTime Output 3: " + ans_time3);
        if (!ans_time3.equals(expectedOutput_time3)) {
            System.out.println("FAILED: addMinutesToTime 3");
            passed = false;
        }
        System.out.println();

        // Test(s) for computeTripCost
        // Test case 1: (Sample values)
        String baseFare = "3.50";
        String miles = "10";
        String ratePerMile = "2.00";
        String discountUnits = "1";

        String ans_trip1 = computeTripCost(baseFare, miles, 
                                    ratePerMile, discountUnits);
        String expectedOutput_trip1 = 
            "Subtotal: 23.50, Discount: 1.18, Total: 22.33";
        // TODO: Verify expected output after implementation
        System.out.println("computeTripCost Output 1: " + ans_trip1);
        if (!ans_trip1.equals(expectedOutput_trip1)) {
            System.out.println("FAILED: computeTripCost 1");
            passed = false;
        }
        System.out.println();

        // TODO: Add Test case 2 for computeTripCost
        String ans_trip2 = computeTripCost("5", "0", 
                                    "10", "0");
        String expectedOutput_trip2 = 
            "Subtotal: 5.00, Discount: 0.00, Total: 5.00";
        // TODO: Verify expected output after implementation
        System.out.println("computeTripCost Output 2: " + ans_trip2);
        if (!ans_trip2.equals(expectedOutput_trip2)) {
            System.out.println("FAILED: computeTripCost 2");
            passed = false;
        }
        System.out.println();
        // TODO: Add Test case 3 for computeTripCost
        String ans_trip3 = computeTripCost("0", "10", 
                                    "1.50", "2");
        String expectedOutput_trip3 = 
            "Subtotal: 15.00, Discount: 1.50, Total: 13.50";
        // TODO: Verify expected output after implementation
        System.out.println("computeTripCost Output 3: " + ans_trip3);
        if (!ans_trip3.equals(expectedOutput_trip3)) {
            System.out.println("FAILED: computeTripCost 3");
            passed = false;
        }
        System.out.println();

        // Test(s) for normalizeEmail
        // Test case 1: (Using sample values)
        String email1 = "Jo.Hn+news@GMAIL.com";
        String ans_email1 = normalizeEmail(email1);
        String expectedOutput_email1 = "john@gmail.com";
        // TODO: Verify expected output after implementation
        System.out.println("normalizeEmail Output 1: " + ans_email1);
        if (!ans_email1.equals(expectedOutput_email1)) {
            System.out.println("FAILED: normalizeEmail 1");
            passed = false;
        }
        System.out.println();

        // TODO: Add Test case 2 for normalizeEmail
        String ans_email2 = normalizeEmail("CaMmie.W8@gmail.net");
        String expectedOutput_email2 = "cammiew8@gmail.net";
        // TODO: Verify expected output after implementation
        System.out.println("normalizeEmail Output 2: " + ans_email2);
        if (!ans_email2.equals(expectedOutput_email2)) {
            System.out.println("FAILED: normalizeEmail 2");
            passed = false;
        }
        System.out.println();
        // TODO: Add Test case 3 for normalizeEmail
        String ans_email3 = normalizeEmail("HELLO!+world@charter.net");
        String expectedOutput_email3 = "hello!@charter.net";
        // TODO: Verify expected output after implementation
        System.out.println("normalizeEmail Output 3: " + ans_email3);
        if (!ans_email3.equals(expectedOutput_email3)) {
            System.out.println("FAILED: normalizeEmail 3");
            passed = false;
        }
        System.out.println();

        // If all tests passed up to this point
        return passed;
    }

    /**
     * Entry point for execution of the program.
     * Runs unit tests, then prompts user for name and prints a greeting.
     * TODO: Complete the method header (Javadoc) and add the required code
     * in the main method body.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Run unit tests first
        if (unitTests()) {
            System.out.println("All unit tests passed.\n");
        } else {
            System.out.println("ERROR: unit test failure.\n");
            return; // Exit if tests fail
        }

        // Set up scanner for user input
        Scanner scanner = new Scanner(System.in);
        System.out.println(PROMPT_MSG_NAME); // Print prompt for name

        // TODO: Read the user's name using the scanner.
        String name = scanner.nextLine(); 
        // TODO: Print the greeting 
        System.out.println(
                "Hello " + name
                + "! Nice to meet you and"
                + " welcome to CSE 11!");
        //Close the scanner
        scanner.close();
    }
}