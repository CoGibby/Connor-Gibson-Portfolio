///////////////////////////////////////////////////////////////////////////////
// Author:            Connor Gibson
// Email:             cogibson@ucsd.edu
// Class:             CSE 11 Winter 2026
// Instructor's Name: Ben Ochoa
///////////////////////////////////////////////////////////////////////////////
// TODO: Complete all methods, and follow ALL CSE 11 Coding Style Guidelines
// (method headers for EVERY single method, including Main and Unit Tests,
// class headers, file headers, etc)

// DO NOT IMPORT ANY OTHER PACKAGES.
// DOING SO MAY FAIL COMPILATION IN GRADESCOPE

import java.util.Arrays;

/**
 * Using arrays to solve various problems
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public class FunWithArrays {

    // Feel free to add constants here
    private static final double preLecWeight = 0.00625; 
    private static final double assignmentWeight = 0.07; 
    private static final double assignment1Weight = 0.03;
    private static final double midtermWeight = 0.20;
    private static final double finalWeight = 0.25;
    private static final double aPlus = 0.96;
    private static final double a = 0.93; 
    private static final double aMinus = 0.90;
    private static final double bPlus = 0.86;
    private static final double b = 0.83;
    private static final double bMinus = 0.80;
    private static final double cPlus = 0.76;
    private static final double c = 0.73;
    private static final double cMinus = 0.70;
    private static final double d = 0.60;


    /**
    * Returns a new array of each data point in
    * array data which is normalized (0.0-1.0 scale)
    *
    * @param data A double type array containing data points
    * @return A new array of the double type of the normalized
    * points
    */
    public static double[] normalizeData(double[] data) {
        // TODO: Implement normalization logic
        if (data == null){
            return null;
        } else if (data.length == 0){
            return new double[0]; 
        }

        double minimumValue = data[0];
        for (int i = 0; i < data.length; i++){
            if (data[i] < minimumValue){
                minimumValue = data[i];
            }
        } 
        double maximumValue = data[0];
        for (int j = 0; j < data.length; j++){
            if (data[j] > maximumValue){
                maximumValue = data[j];
            }
        } 
        double[] new_array = new double[data.length];
        if (minimumValue == maximumValue){
            for (int x = 0; x < data.length; x++){
                new_array[x] = Double.NaN;
            }
            return new_array;
        }
        double range = maximumValue - minimumValue; 
        for (int x = 0; x < data.length; x++){
            new_array[x] = (data[x] - minimumValue) / range; 
        }
        return new_array;
    }
    /**
    * Finds unique strings in an array of strings 
    * (only used once), and finds the longest 
    * lengthed string, then returns the index of 
    * the original position in stringsArray
    *
    * @param stringsArray A string type array with
    * a various amount of strings
    * @return An integer representing the index of the 
    * longest unique array
    */
    public static int findLongestUniquePosition(String[] stringsArray) {
        // TODO: Implement logic to find longest unique string
        if (stringsArray.length == 0 || stringsArray == null){
            return -1;
        }
        String uniqueVariable = "";
        String uniqueVariable2 = ""; 
        int count = 0;
        int count2 = 0;
        int uniqueCount = 0;
        int uniqueLength = 0;
        for (int i = 0; i < stringsArray.length; i++){
            count = 0; 
            uniqueVariable = stringsArray[i];
            for (int j = 0; j < stringsArray.length; j++){
                if (uniqueVariable.equals(stringsArray[j])){
                    count += 1;
                }
            } 
            if (count == 1){
                uniqueCount += 1;
            }
        }
        String[] uniqueArray = new String[uniqueCount];
        int index = 0;
        for (int c = 0; c < stringsArray.length; c++){
            count2 = 0; 
            uniqueVariable = stringsArray[c];
            for (int s = 0; s < stringsArray.length; s++){
                if (uniqueVariable.equals(stringsArray[s])){
                    count2 += 1;
                }
            } 
            if (count2 == 1){
                uniqueArray[index++] = uniqueVariable;
            }
        }
        if (uniqueArray.length == 0){
            return -1;
        }
        String best = "";
        for (int h = 0; h < uniqueArray.length; h++){
            if (uniqueArray[h].length() > uniqueLength){
                best = uniqueArray[h];
                uniqueLength = uniqueArray[h].length();
            } else if (uniqueArray[h].length() == best.length() &&
                     uniqueArray[h].compareTo(best) < 0) {
                best = uniqueArray[h];
            }
        }
        for (int g = 0; g < stringsArray.length; g++){
            if (stringsArray[g].equals(best)){
                return g;
            }
        }
        return -1;
    }
    /**
    * Simulates a race track using four inputs
    *
    * @param carPositions The original position of each car (int array)
    * @param speeds The speeds each car is going (int array)
    * @param trackLength The number of sectors on a track (int)
    * @param boostPads If a sector has a boost pad on it (boolean array)
    */
    public static void simulateRaceTrack(int[] carPositions, 
                                         int[] speeds, 
                                         int trackLength, 
                                         boolean[] boostPads) {
        // TODO: Update car positions handling wrapping and speed boosts
        if (carPositions == null || speeds == null 
            || carPositions.length != speeds.length
            || trackLength <= 0 || boostPads.length != trackLength 
            || boostPads == null){
            return; 
        }
        for (int i = 0; i < carPositions.length; i++){
            if (carPositions[i] < 0 || carPositions[i] >= trackLength){
                return;
            }
        }
        int newPos = 0;
        for (int j = 0; j < carPositions.length; j++){
            newPos = carPositions[j] + speeds[j];
            if (newPos < 0){
                newPos += trackLength;
                carPositions[j] = newPos;
            } else if (newPos >= trackLength){ 
                newPos %= trackLength;
                carPositions[j] = newPos;
            } else {
                carPositions[j] = newPos;
            }
            if (boostPads[newPos]){
                newPos += 1; 
                if (newPos >= trackLength){
                    newPos %= trackLength;
                    carPositions[j] = newPos;
                } else {
                    carPositions[j] = newPos;
                }
            }
        }
    }
    /**
    * Finds the length of the longest increasing sequence 
    * in an array of integers
    *
    * @param numberSequenceArray An array of integers that
    * @return An integer representing the length of the 
    * longest increasing sequence
    * points
    */
    public static int maxIncreasingSequence(int[] numberSequenceArray) {
        // TODO: Implement logic to find longest 
        // consecutive increasing sequence
        if (numberSequenceArray == null){
            return -1;
        } else if (numberSequenceArray.length == 0){
            return 0;
        }
        int highestValue = numberSequenceArray[0];
        int count = 1;
        int highestCount = 1; 
        for (int i = 1; i < numberSequenceArray.length; i++){
            if (numberSequenceArray[i] <= highestValue){
                highestValue = numberSequenceArray[i];
                count = 1;
                continue;
            } else if (numberSequenceArray[i] > highestValue){
                highestValue = numberSequenceArray[i];
                count += 1; 
                highestCount = count;
            }
        }
        if (highestCount >= count){
            return highestCount;
        } else { 
            return count;
        }
    }
    /**
    * Given all the graded scores in CSE 11, we will 
    * calculate the current, or final grade in the class
    *
    * @param prelectureQuizScores Double type array containing
    * all prelecture quiz scores
    * @param initialAssignmentScores The initial assignment score, 
    * in a double type array
    * @param resubmissionAssignmentScores The score after upgrading
    * the assignment, in a double type array
    * @param midtermScore The score received on the midterm, 
    * a double value 
    * @param finalAssessmentPartOneScore The score on part one 
    * of the final assessment (double)
    * @param finalAssessmentTotalScore The total score on the 
    * final assessment (double)
    * @return A string representing the final/current grade
    */
    public static String calculateCSE11CurrentGrade(
                                        double[] prelectureQuizScores,
                                        double[] initialAssignmentScores,
                                        double[] resubmissionAssignmentScores,
                                        double midtermScore,
                                        double finalAssessmentPartOneScore,
                                        double finalAssessmentTotalScore) {
        double weightedSumPrelecture = 0.0; 
        double weightedCountPrelecture = 0.0; 
        double weightedSumAssignments = 0.0; 
        double weightedCountAssignments = 0.0; 
        double newAssignmentScore = 0.0; 
        double weightedSumMidterm = 0.0; 
        double weightedCountMidterm = 0.0;
        double newMidtermScore = 0.0; 
        double weightedSumAssessmentTotal = 0.0; 
        double weightedCountAssessmentTotal = 0.0; 
        double totalSums = 0.0; 
        double totalCounts = 0.0; 
        if (prelectureQuizScores.length > 0){
            for (int i = 0; i < prelectureQuizScores.length; i++){
                if (prelectureQuizScores[i] >= 0.0){
                    weightedSumPrelecture += 
                        prelectureQuizScores[i]*preLecWeight; 
                    weightedCountPrelecture += preLecWeight;
                } 
            }
        } else {
            weightedSumPrelecture = 0.0; 
            weightedCountPrelecture = 0.0; 
        }
        if (initialAssignmentScores.length > 0){
            if (initialAssignmentScores[0] >= 0.0){
                weightedSumAssignments = 
                    initialAssignmentScores[0]*assignment1Weight;
                weightedCountAssignments = assignment1Weight;
            } else { 
                weightedSumAssignments = 0.0;
                weightedCountAssignments = 0.0;
            }
            for (int j = 1; j < initialAssignmentScores.length; j++){
                if (initialAssignmentScores[j] == -1.0){
                    continue;
                }
                if (initialAssignmentScores[j] >= 
                    resubmissionAssignmentScores[j]){
                    weightedSumAssignments += 
                        initialAssignmentScores[j]*assignmentWeight; 
                    weightedCountAssignments += assignmentWeight;
                } else if (initialAssignmentScores[j] < 
                    resubmissionAssignmentScores[j]){
                    newAssignmentScore = (initialAssignmentScores[j] 
                        + resubmissionAssignmentScores[j]) / 2; 
                    weightedSumAssignments += 
                        newAssignmentScore * assignmentWeight;
                    weightedCountAssignments += assignmentWeight; 
                }
            }
        } else {
            weightedSumAssignments = 0.0; 
            weightedCountAssignments = 0.0;
        }
        if (midtermScore == -1.0){
            weightedSumMidterm = 0.0;
            weightedCountMidterm = 0.0; 
        } else if (midtermScore < finalAssessmentPartOneScore){
            newMidtermScore = (midtermScore + finalAssessmentPartOneScore) / 2;
            weightedSumMidterm = newMidtermScore*midtermWeight; 
            weightedCountMidterm = midtermWeight; 
        } else if (midtermScore >= finalAssessmentPartOneScore){
            weightedSumMidterm = midtermScore * midtermWeight; 
            weightedCountMidterm = midtermWeight;
        }
        if (finalAssessmentTotalScore == -1.0){
            weightedSumAssessmentTotal = 0.0;
            weightedCountAssessmentTotal = 0.0; 
        } else if (finalAssessmentTotalScore >= 0.0){
            weightedSumAssessmentTotal = finalAssessmentTotalScore*finalWeight;
            weightedCountAssessmentTotal = finalWeight;
        }
        totalSums = weightedSumPrelecture + weightedSumAssignments
            + weightedSumMidterm + weightedSumAssessmentTotal;
        totalCounts = weightedCountPrelecture + weightedCountAssignments 
            + weightedCountMidterm + weightedCountAssessmentTotal;
        double finalPercent = totalSums / totalCounts; 
        if (finalPercent >= aPlus){
            return "A+";
        } else if (finalPercent >= a){
            return "A";
        } else if (finalPercent >= aMinus){
            return "A-";
        } else if (finalPercent >= bPlus){
            return "B+";
        } else if (finalPercent >= b){
            return "B";
        } else if (finalPercent >= bMinus){
            return "B-";
        } else if (finalPercent >= cPlus){
            return "C+";
        } else if (finalPercent >= c){
            return "C";
        } else if (finalPercent >= cMinus){
            return "C-";
        } else if (finalPercent >= d){
            return "D";
        } 
        return "F";
    }
    /**
    * Using a 2d integer array, we make it into a grid,
    * and add up all of the borders, then divide to 
    * find the average of the border values
    *
    * @param matrix A 2d integer array containing the subarrays
    * (rows) and values inside the subarrays (columns)
    * @return An integer representing the average of all
    * border values
    */
    public static int averageOuterBorders(int[][] matrix) {
        if (matrix == null || matrix.length == 0){
            return -1; 
        }
        int columns = 0; 
        int rows = 0; 
        double average = 0.0; 
        double  count = 0.0;
        double doubleAverage = 0.0;
        int intAverage = 0;
        rows = matrix.length; 
        columns = matrix[0].length;
        if (columns == 2 || columns == 1 || rows == 2 || rows == 1){
            for (int g = 0; g < matrix.length; g++){
                for (int u = 0; u < matrix[g].length; u++){
                    average += matrix[g][u];
                    count += 1;
                }
            }
            doubleAverage = Math.ceil(average / count);
            intAverage = (int) doubleAverage;
            return intAverage;
        }
        for (int c = 0; c < columns; c++){
            average += matrix[0][c];
            count += 1;
        }
        for (int b = 0; b < columns; b++){
            average += matrix[rows - 1][b];
            count += 1;
        }
        for (int r = 1; r < rows -1; r++){
            average += matrix[r][0];
            average += matrix[r][columns - 1];
            count += 2;
        }
        doubleAverage = Math.ceil(average / count);
        intAverage = (int) doubleAverage;
        return intAverage;
    }
    /**
    * Given a value n, we take the absolute value to represent the
    * number of columns and rows in a new array, and that
    * value squared gives us how many digits to go up too for 
    * each integer value.
    *
    * @param n An integer representing rows/columns, and then 
    * the final number (n^2)
    * @return A 2d integer array containing the sequential values
    */
    public static int[][] createSequentialMatrix(int n) {
        int numbers = Math.abs(n) * Math.abs(n); 
        int column = Math.abs(n);
        int row = Math.abs(n); 
        int count = 1;
        int[][] sequentialMatrix = new int[column][row]; 
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                sequentialMatrix[i][j] = count;
                count++;
            }

        }
        return sequentialMatrix;
    }
    /**
    * Multiple tests for each method to test accuracy 
    *
    * @return Boolean whether they pass (true) or fail (false)
    */
    @SuppressWarnings("checkstyle:MagicNumber") // DO NOT CHANGE THIS LINE!!!
    public static boolean unitTests() {
        // Test(s) for normalizeData
        // Test case 1: Standard Min-Max Case
        double[] data = {10.0, 20.0};
        double[] expectedOutput = {0.0, 1.0};
        double[] ans = normalizeData(data);
        
        System.out.println("normalizeData Test 1:" +
                            " expectedOutput is " 
                            + Arrays.toString(expectedOutput) 
                            + " and actual output is " + Arrays.toString(ans));
        
        if (!Arrays.equals(expectedOutput, ans)) {
            System.out.println("FAILED: normalizeData 1");
            return false;
        }
        // Test case 2: Advanced Min-Max Case
        double[] data2 = {2.0, 4.0, 8.0, 10.0};
        double[] expectedOutput2 = {0.0, 0.25, 0.75, 1.0};
        double[] ans2 = normalizeData(data2);
        
        System.out.println("normalizeData Test 2:" +
                            " expectedOutput is " 
                            + Arrays.toString(expectedOutput2) 
                            + " and actual output is " + Arrays.toString(ans2));
        
        if (!Arrays.equals(expectedOutput2, ans2)) {
            System.out.println("FAILED: normalizeData 2");
            return false;
        }
        // Test case 3: Unique Min-Max Case
        double[] data3 = {5.0, 5.0, 5.0};
        double[] expectedOutput3 = {Double.NaN, Double.NaN, Double.NaN};
        double[] ans3 = normalizeData(data3);
        
        System.out.println("normalizeData Test 3:" +
                            " expectedOutput is " 
                            + Arrays.toString(expectedOutput3) 
                            + " and actual output is " + Arrays.toString(ans3));
        
        if (!Arrays.equals(expectedOutput3, ans3)) {
            System.out.println("FAILED: normalizeData 3");
            return false;
        }

        // Test(s) for findLongestUniquePosition
        // Test case 1:
        String[] strings = new String[]{"cats", "dog", "rat", "dog"};
        System.out.println("findLongestUniquePosition Test 1:" +
            " expectedOutput is 0" +
            " and actual output is " 
            + findLongestUniquePosition(strings));
        if (findLongestUniquePosition(strings) != 0) {
            System.out.println("FAILED: findLongestUniquePosition 1");
            return false;
        }
        // Test case 2:
        String[] strings2 = new String[]{"apple", "berry", "cherry", "date"};
        System.out.println("findLongestUniquePosition Test 2:" +
            " expectedOutput is 2" +
            " and actual output is " 
            + findLongestUniquePosition(strings2));
        if (findLongestUniquePosition(strings2) != 2) {
            System.out.println("FAILED: findLongestUniquePosition 2");
            return false;
        }
        // Test case 3:
        String[] strings3 = new String[]{"b", "aa", "cc", "b"};
        System.out.println("findLongestUniquePosition Test 3:" +
            " expectedOutput is 1" +
            " and actual output is " 
            + findLongestUniquePosition(strings3));
        if (findLongestUniquePosition(strings3) != 1) {
            System.out.println("FAILED: findLongestUniquePosition 3");
            return false;
        }
        // Test(s) for simulateRaceTrack
        // Test case 1: Simple movement with NO boosts (All false)
        int[] locations = {0, 1};
        int[] speeds = {1, 2};
        int trackLen = 5;
        boolean[] boosts = new boolean[trackLen]; // All false
        
        // Expected: 0+1=1, 1+2=3
        int[] expectedLocs = {1, 3};
        
        simulateRaceTrack(locations, speeds, trackLen, boosts);
        System.out.println("simulateRaceTrack Test 1: " +
            "expectedOutput is " + Arrays.toString(expectedLocs) +
                " and actual output is " + Arrays.toString(locations));
        
        if (!Arrays.equals(locations, expectedLocs)){
            System.out.println("simulateRaceTrack test 1 failed");
            return false;
        }
        // Test case 2: Simple movement + boost 
        int[] locations2 = {0};
        int[] speeds2 = {2};
        int trackLen2 = 5;
        boolean[] boosts2 = {false, false, true, false, false}; // one true 
        
        // Expected: 0 + 2 + 1 = 3
        int[] expectedLocs2 = {3};
        
        simulateRaceTrack(locations2, speeds2, trackLen2, boosts2);
        System.out.println("simulateRaceTrack Test 2: " +
            "expectedOutput is " + Arrays.toString(expectedLocs2) +
                " and actual output is " + Arrays.toString(locations2));
        
        if (!Arrays.equals(locations2, expectedLocs2)){
            System.out.println("simulateRaceTrack test 2 failed");
            return false;
        }
        // Test case 3: Complex  
        int[] locations3 = {0,1,2,3};
        int[] speeds3 = {2,1,-3,4};
        int trackLen3 = 5;
        boolean[] boosts3 = {false, true, true, false, true};
        
        // Expected: 0 + 2 + 1 = 3
        int[] expectedLocs3 = {3,3,0,3};
        
        simulateRaceTrack(locations3, speeds3, trackLen3, boosts3);
        System.out.println("simulateRaceTrack Test 3: " +
            "expectedOutput is " + Arrays.toString(expectedLocs3) +
                " and actual output is " + Arrays.toString(locations3));
        
        if (!Arrays.equals(locations3, expectedLocs3)){
            System.out.println("simulateRaceTrack test 3 failed");
            return false;
        }
        // Test(s) for maxIncreasingSequence
        // Test case 1:
        int[] sequence1 = {1, 2, 3};
        System.out.println("maxIncreasingSequence Test 1: " +
            "expectedOutput is 3 " +
                "and actual output is " +
                maxIncreasingSequence(sequence1));
        if (maxIncreasingSequence(sequence1) != 3) {
            System.out.println("maxIncreasingSequence test 1 failed");
            return false;
        }
        // Test case 2:
        int[] sequence2 = {1, 5, 2, 4, 6};
        System.out.println("maxIncreasingSequence Test 2: " +
            "expectedOutput is 3 " +
                "and actual output is " +
                maxIncreasingSequence(sequence2));
        if (maxIncreasingSequence(sequence2) != 3) {
            System.out.println("maxIncreasingSequence test 2 failed");
            return false;
        }
        // Test case 3:
        int[] sequence3 = {1, 2, 2, 3};
        System.out.println("maxIncreasingSequence Test 3: " +
            "expectedOutput is 2 " +
                "and actual output is " +
                maxIncreasingSequence(sequence3));
        if (maxIncreasingSequence(sequence3) != 2) {
            System.out.println("maxIncreasingSequence test 3 failed");
            return false;
        }
        // Test(s) for calculateCSE11CurrentGrade
        // Test case 1:
        double[] prelectureQuizScores = new double[]{1.0, 0.9, 1.0, 1.0, 1.0, 
                                                     1.0, 1.0, 0.0, 1.0, 0.85, 
                                                     1.0, 0.0, 0.0, 0.77,
                                                     1.0, 1.0};
        double[] initialAssignmentScores = new double[]{1.0, 0.85, 0.96, 0.68,
                                                        0.88, 0.92, 0.75};
        
        // UPDATED: Removed last element to make length 7
        double[] resubmissionAssignmentScores = new double[]{-1.0, 1.0, 0.0, 
                                                             0.9, 0.9, 0.0, 
                                                             0.86};
                                                             
        double midtermScore = 0.72;
        double finalAssessmentPartOneScore = 0.84;
        double finalAssessmentTotalScore = 0.82;
        String expectedGrade1 = "B";
        System.out.println("calculateCSE11CurrentGrade Test 1: " +
            "expectedGrade is " + expectedGrade1 +
                " and actualGrade is " 
                + calculateCSE11CurrentGrade(prelectureQuizScores, 
                                             initialAssignmentScores, 
                                             resubmissionAssignmentScores, 
                                             midtermScore, 
                                             finalAssessmentPartOneScore, 
                                             finalAssessmentTotalScore));
        if (!calculateCSE11CurrentGrade(prelectureQuizScores, 
                                       initialAssignmentScores, 
                                       resubmissionAssignmentScores, 
                                       midtermScore, 
                                       finalAssessmentPartOneScore, 
                                       finalAssessmentTotalScore)
                                       .equals(expectedGrade1)) {
            System.out.println("calculateCSE11CurrentGrade test 1 failed");
            return false;
        }
        // Test case 2:
        double[] prelectureQuizScores2 = new double[]{1.0, 1.0, 1.0, 1.0, 
                                                     1.0, 1.0, 1.0, -1.0, 
                                                     -1.0, -1.0, -1.0, -1.0, 
                                                     -1.0,-1.0, -1.0, -1.0};
        double[] initialAssignmentScores2 = new double[]{1.0, 0.85, 0.96, 
                                                        -1.0,-1.0, -1.0, 
                                                        -1.0};
        
        // UPDATED: Removed last element to make length 7
        double[] resubmissionAssignmentScores2 = new double[]{-1.0, 1.0, 0.0, 
                                                             -1.0, -1.0, 
                                                             -1.0 ,-1.0};
                                                             
        double midtermScore2 = -1.0;
        double finalAssessmentPartOneScore2 = -1.0;
        double finalAssessmentTotalScore2 = -1.0;
        String expectedGrade2 = "A+";
        System.out.println("calculateCSE11CurrentGrade Test 2: " +
            "expectedGrade is " + expectedGrade2 +
                " and actualGrade is " 
                + calculateCSE11CurrentGrade(prelectureQuizScores2, 
                                             initialAssignmentScores2, 
                                             resubmissionAssignmentScores2, 
                                             midtermScore2, 
                                             finalAssessmentPartOneScore2, 
                                             finalAssessmentTotalScore2));
        if (!calculateCSE11CurrentGrade(prelectureQuizScores2, 
                                       initialAssignmentScores2, 
                                       resubmissionAssignmentScores2, 
                                       midtermScore2, 
                                       finalAssessmentPartOneScore2, 
                                       finalAssessmentTotalScore2)
                                       .equals(expectedGrade2)) {
            System.out.println("calculateCSE11CurrentGrade test 2 failed");
            return false;
        }
        // Test case 3:
        double[] prelectureQuizScores3 = new double[]{1.0, 0.9, 0.8, 0.7, 
                                                    0.6, 0.6, 0.5, 
                                                    -1.0, -1.0, -1.0, -1.0, 
                                                    -1.0, -1.0, -1.0};
        double[] initialAssignmentScores3 = new double[]{0.85, 0.70, 
                                                        0.60, 0.75, 
                                                        -1.0, -1.0, -1.0};
        
        double[] resubmissionAssignmentScores3 = new double[]{-1.0, 0.90, 
                                                            0.80, 0.60, 
                                                            -1.0, -1.0, -1.0};
                                                             
        double midtermScore3 = -1.0;
        double finalAssessmentPartOneScore3 = -1.0;
        double finalAssessmentTotalScore3 = -1.0;
        String expectedGrade3 = "C";
        System.out.println("calculateCSE11CurrentGrade Test 3: " +
            "expectedGrade is " + expectedGrade3 +
                " and actualGrade is " 
                + calculateCSE11CurrentGrade(prelectureQuizScores3, 
                                             initialAssignmentScores3, 
                                             resubmissionAssignmentScores3, 
                                             midtermScore3, 
                                             finalAssessmentPartOneScore3, 
                                             finalAssessmentTotalScore3));
        if (!calculateCSE11CurrentGrade(prelectureQuizScores3, 
                                       initialAssignmentScores3, 
                                       resubmissionAssignmentScores3, 
                                       midtermScore3, 
                                       finalAssessmentPartOneScore3, 
                                       finalAssessmentTotalScore3)
                                       .equals(expectedGrade3)) {
            System.out.println("calculateCSE11CurrentGrade test 3 failed");
            return false;
        }

        // Test(s) for averageOuterBorders
        // Test case 1:
        int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        // Border: 1, 2, 3, 6, 9, 8, 7, 4. Sum = 40. Count = 8. Avg = 5.
        int expectedAverage1 = 5;
        System.out.println("averageOuterBorders Test 1: " +
            "expectedOutput is " + expectedAverage1 
            + " and actual output is " +
            averageOuterBorders(matrix1));
        if (averageOuterBorders(matrix1) != expectedAverage1) {
            System.out.println("FAILED: averageOuterBorders 1");
            return false;
        }
        // Test case 2:
        int[][] matrix2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, 
            {10, 11, 12}};
        // Border: 1, 2, 3, 4, 6, 7, 9, 10, 11, 12 Sum = 65. 
        // Count = 10. Avg = 6.5--7.
        int expectedAverage2 = 7;
        System.out.println("averageOuterBorders Test 2: " +
            "expectedOutput is " + expectedAverage2 
            + " and actual output is " +
            averageOuterBorders(matrix2));
        if (averageOuterBorders(matrix2) != expectedAverage2) {
            System.out.println("FAILED: averageOuterBorders 2");
            return false;
        }
        // Test case 3:
        int[][] matrix3 = {{1, 2, 3, 4}, {4, 5, 6, 7}, {7, 8, 9, 10}, 
            {10, 11, 12, 13}};
        // Border: 1, 2, 3, 4, 4, 7, 7, 10, 10, 11,12, 13 
        // Sum = 84. Count = 12. Avg = 7.
        int expectedAverage3 = 7;
        System.out.println("averageOuterBorders Test 3: " +
            "expectedOutput is " + expectedAverage3 
            + " and actual output is " +
            averageOuterBorders(matrix3));
        if (averageOuterBorders(matrix3) != expectedAverage3) {
            System.out.println("FAILED: averageOuterBorders 3");
            return false;
        }
        // Test(s) for createSequentialMatrix
        // Test case 1:
        int[][] expectedMatrix = new int[][]{{1, 2, 3}, {4, 5, 6}, 
            {7, 8, 9}};
        System.out.println("createSequentialMatrix Test 1: " +
            "expectedOutput is " + Arrays.deepToString(expectedMatrix) +
                " and actual output is " +
                    Arrays.deepToString(createSequentialMatrix(3)));
        if (!Arrays.deepEquals(expectedMatrix, createSequentialMatrix(3))) {
            System.out.println("createSequentialMatrix test 1 failed");
            return false;
        }
        // Test case 2:
        int[][] expectedMatrix2 = new int[][]{{1, 2}, {3, 4}};
        System.out.println("createSequentialMatrix Test 2: " +
            "expectedOutput is " + Arrays.deepToString(expectedMatrix2)
            +    " and actual output is " +
                    Arrays.deepToString(createSequentialMatrix(-2)));
        if (!Arrays.deepEquals(expectedMatrix2, 
            createSequentialMatrix(-2))) {
            System.out.println("createSequentialMatrix test 2 failed");
            return false;
        }
        // Test case 3:
        int[][] expectedMatrix3 = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, 
            {9, 10, 11, 12}, {13, 14, 15, 16}};
        System.out.println("createSequentialMatrix Test 3: " +
            "expectedOutput is " + Arrays.deepToString(expectedMatrix3) +
                " and actual output is " +
                    Arrays.deepToString(createSequentialMatrix(4)));
        if (!Arrays.deepEquals(expectedMatrix3, createSequentialMatrix(4))) {
            System.out.println("createSequentialMatrix test 3 failed");
            return false;
        }
        return true;
    }
    /**
    * See if all the unit tests passed or not  
    *
    * @param args Part of the program
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