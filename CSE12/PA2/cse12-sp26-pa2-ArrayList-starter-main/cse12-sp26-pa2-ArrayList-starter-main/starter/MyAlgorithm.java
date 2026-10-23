/*
   Name: Connor Gibson
   Email: cogibson@ucsd.edu
   PID: A18677169
   Sources used: None
  
   This file is used to demonstrate what a properly styled file looks like.
   It comes with class and method headers samples that you can use as a
   template for styling your assignment files. (Remember 1-4 sentences)
 */

// If there are imports, they should go here.
/**
 * A concrete class that implements a static method utilizing MyArrayList
 */
public class MyAlgorithm {

    /**
     * Moves all occurrences of the target integer to the end of the list 
     * in-place.
     * 
     * @param nums   The MyArrayList of integers.
     * @param target The integer value to move to the end.
     * @return The modified MyArrayList with all occurrences of the target 
     *         integer moved to the end.
     */
    public static MyArrayList<Integer> moveTargetToEnd(
            MyArrayList<Integer> nums, int target) {
        if (nums == null){
            throw new NullPointerException();
        }
        int size = nums.size;
        int count = 0;

        for (int i = 0; i < size; i++) {
            if (nums.data[i] != null && nums.data[i].equals(target)) {
                count++;
            }
        }

        if (count == 0) {
            return nums;
        }

        int secondCount = 0;

        for (int i = 0; i < size; i++) {
            if (!nums.data[i].equals(target)) {
                nums.data[secondCount] = nums.data[i];
                secondCount++;
            }
        }

        while (count > 0) {
            nums.data[secondCount] = target;
            secondCount++;
            count--;
        }

        return nums;
    }
}
