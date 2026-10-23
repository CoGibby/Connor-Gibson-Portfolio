/*
   Name: Connor Gibson
   Email: cogibson@ucsd.edu
   PID: A18677169
   Sources used: None
  
   This file creates a linkedlist and extends abstractlist
 */
// If there are imports, they should go here.
import java.util.ListIterator;
/**
 * This is a public class that answers 
 * the challenge coding question 
 */
public class MyAlgorithm {

    /**
     * This method will take two lists and merge them 
     * 
     * @param <E> generic type to allow Integers, Strings, etc.
     * @param left The first list to be merged
     * @param right The second list to be merged
     * @return MyLinkedList<E> will be returned based off 
     * two LinkedLists
     */
    public static <E extends Comparable<E>> MyLinkedList<E> mergeLists(
        MyLinkedList<E> left, MyLinkedList<E> right){
        // Note: Use result to store the merged list
        if (left == null || right == null){
            throw new NullPointerException();
        }
        MyLinkedList<E> combine = new MyLinkedList<E>();
        ListIterator<E> combineIterator = combine.listIterator();
        ListIterator<E> leftCopy = left.listIterator();
        ListIterator<E> rightCopy = right.listIterator();
        boolean rightCapacity = rightCopy.hasNext();
        boolean leftCapacity = leftCopy.hasNext();
        E nextLeft = null;
        E nextRight = null;
        if (rightCapacity){
            nextRight = rightCopy.next();
        }
        if (leftCapacity){
            nextLeft = leftCopy.next();
        }


        while (rightCapacity && leftCapacity) {

            if (nextLeft.compareTo(nextRight) <= 0) {
                combineIterator.add(nextLeft);
                leftCapacity = leftCopy.hasNext();
                if (leftCapacity) {
                    nextLeft = leftCopy.next();
                }
            } else {
                combineIterator.add(nextRight);
                rightCapacity = rightCopy.hasNext();
                if (rightCapacity) {
                    nextRight = rightCopy.next();
                }
            }

        }
        while (leftCapacity) {
            combineIterator.add(nextLeft);
            leftCapacity = leftCopy.hasNext();
            if (leftCapacity){
                nextLeft = leftCopy.next();
            }
        }

        while (rightCapacity) {
            combineIterator.add(nextRight);
            rightCapacity = rightCopy.hasNext();
            if (rightCapacity){
                nextRight = rightCopy.next();
            }
        }

        return combine;
    }

} 
