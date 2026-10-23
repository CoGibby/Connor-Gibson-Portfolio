/*
   Name: Connor Gibson
   Email: cogibson@ucsd.edu
   PID: A18677169
   Sources used: None
  
   This file is used for my algorithm to see if my code 
   can combine two linkedlists into one.
 */
/**
 * This class sees if my implementation can take two 
 * linkedlists and return a combined one
 */
public class MyAlgorithm {

    /**
     * This method will take two linkedlists and merge them
     * 
     * @param <E> generic type to allow Integers, Strings, etc.
     * @param left The left list to merge
     * @param right The right list to merge
     * @return MyLinkedList<E> merged list
     */
    public static <E extends Comparable<E>> MyLinkedList<E> mergeLists(
        MyLinkedList<E> left, MyLinkedList<E> right){
        // Note: You might find compareTo() useful
        // TODO
        if (left == null || right == null){
            throw new NullPointerException();
        }
        MyLinkedList<E> combine = new MyLinkedList<E>();

        int leftCount = 0;
        int rightCount = 0;
        int totalSize = left.size() + right.size();

        while (totalSize > 0) {

            if (leftCount >= left.size()) {
                combine.add(right.get(rightCount));
                rightCount++;
            }
            else if (rightCount >= right.size()) {
                combine.add(left.get(leftCount));
                leftCount++;
            }
            else {
                E leftSpecific = left.get(leftCount);
                E rightSpecific = right.get(rightCount);

                if (leftSpecific.compareTo(rightSpecific) <= 0) {
                    combine.add(leftSpecific);
                    leftCount++;
                } else {
                    combine.add(rightSpecific);
                    rightCount++;
                }
            }

            totalSize--;
        }
        
        return combine;

    }

}