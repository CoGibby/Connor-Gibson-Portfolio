/*
   Name: Connor Gibson
   Email: cogibson@ucsd.edu
   PID: A18677169
   Sources used: None
  
   This file creates a linkedlist and extends abstractlist
 */


/**
 * This class uses the hash set class to 
 * solve a programming problem 
 */
public class MyAlgorithm {
    /**
     * This method takes an integer list, and returns 
     * a boolean value which determines if two repeated 
     * integers are within certainity spaces apart
     * 
     * @param transactionIds This is the integer list of ids
     * @param certainty The maximum spaces between two duplicates
     * to be detected as fraud
     * @return A boolean showing if it is fraud or not
     */
    public static boolean hasFraudulentTRs(int[] transactionIds,
        int certainty) {
        /* Add your implementation here */
        // Note: You may find Math.abs() useful
        if (certainty <= 0){
            throw new IllegalArgumentException();
        }
        MyHashSet<Integer> ids = new MyHashSet<>();
        
        for (int i = 0; i < transactionIds.length; i++){
            int current = transactionIds[i];
            if (ids.contains(current)){
                return true;
            }

            ids.add(current);

            if (i >= certainty){
                int skip = transactionIds[i - certainty];
                ids.remove(skip);
            }
        }
        return false;
    }
}