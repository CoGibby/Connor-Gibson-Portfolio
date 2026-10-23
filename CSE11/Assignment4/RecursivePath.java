///////////////////////////////////////////////////////////////////////////////
// Author:            Connor Gibson
// Email:             cogibson@ucsd.edu
// Class:             CSE 11 Winter 2026
// Instructor's Name: Ben Ochoa
///////////////////////////////////////////////////////////////////////////////
// TODO: Complete all methods, and follow ALL CSE 11 Coding Style Guidelines
// (method headers for EVERY single method, including main() and unitTests(),
// class headers, file headers, etc)

// DO NOT IMPORT ANY OTHER PACKAGES.
// DOING SO MAY FAIL COMPILATION IN GRADESCOPE

/**
 * Uses a recursive approach to find the best path along fishingHoles
 * to maximize the fish caught
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public class RecursivePath {
    private static final int multiple = 5;
    public static int recursionCallCount = 0; // WARNING: DO NOT MODIFY THIS
    // LINE FOR STAFF TO TEST RECURSIVE CALLS

    // Feel free to add more constants

    // Add javadoc comment
    /**
    * Finds the optimal route along fishing holes
    * to capture the most amount of fish
    *
    * @param fishingHoles Integer array representing the 
    * amount of fish at each index
    * @param startingPosition Intenger representing the 
    * starting position (index)
    * @return An intenger representing the total amount of 
    * fish captured after reaching the end
    */
    public static int optimizeFishCatch(int[] fishingHoles, 
                                        int startingPosition) {
        recursionCallCount++; // WARNING: DO NOT MODIFY THIS LINE, FOR STAFF
        // TODO: Implement recursive logic
        if (startingPosition >= fishingHoles.length){
            return 0;
        }
        int startingValue = fishingHoles[startingPosition];
        if (startingValue > 0 && startingValue % multiple == 0){
            startingValue += 1;
        } else if (startingValue < 0 && startingValue % multiple == 0){
            startingValue -= 1; 
        }
        int oneStep = optimizeFishCatch(fishingHoles, startingPosition + 1);
        int twoStep = optimizeFishCatch(fishingHoles, startingPosition + 2);
        int threeStep = optimizeFishCatch(fishingHoles, startingPosition + 3);
        int bestNext = Math.max(oneStep, Math.max(twoStep, threeStep));
        return startingValue + bestNext;

    }
    /**
    * Multiple tests of the method to test accuracy
    *
    * @return A boolean whether the tests pass (true) or fail (false)
    */
    @SuppressWarnings("checkstyle:MagicNumber") // DO NOT CHANGE THIS LINE!!!
    public static boolean unitTests() {
        // Test(s) for optimizeFishCatch
        // Test case 1:
        int[] fishPath = {1, -6, 10};
        System.out.println("optimizeFishCatch Test 1: " +
            "expectedOutput is 12 " + "and actual output is " +
                optimizeFishCatch(fishPath, 0));
        if (optimizeFishCatch(fishPath, 0) != 12) {
            System.out.println("FAILED: optimizeFishCatch 1");
            return false;
        }
        // Test case 2:
        int[] fishPath2 = {3, 4, -10, -11, -32, 6, -4, 11, 4};
        System.out.println("optimizeFishCatch Test 2: " +
            "expectedOutput is 17 " + "and actual output is " +
                optimizeFishCatch(fishPath2, 0));
        if (optimizeFishCatch(fishPath2, 0) != 17) {
            System.out.println("FAILED: optimizeFishCatch 2");
            return false;
        }
        // Test case 3:
        int[] fishPath3 = {3, -15, 1, -100, -10, -1, 11, -10};
        System.out.println("optimizeFishCatch Test 3: " +
            "expectedOutput is 14 " + "and actual output is " +
                optimizeFishCatch(fishPath3, 0));
        if (optimizeFishCatch(fishPath3, 0) != 14) {
            System.out.println("FAILED: optimizeFishCatch 3");
            return false;
        }
        return true;
    }
    /**
    * Sees is unit tests is true or false
    * and prints a corresponding statement
    *
    * @param args Part of program for command line
    */
    public static void main(String[] args) {
        if (unitTests()) {
            System.out.println("All unit tests passed.\n");
        } else {
            System.out.println("ERROR: Failed test.\n");
            return;
        }
    }
}