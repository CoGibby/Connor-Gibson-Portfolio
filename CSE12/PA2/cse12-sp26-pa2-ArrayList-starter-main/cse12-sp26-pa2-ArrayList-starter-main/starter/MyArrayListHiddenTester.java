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
import static org.junit.Assert.*;

import org.junit.*;
/**
 * This class has hidden tests for all of the methods
 */
public class MyArrayListHiddenTester {

    private MyArrayList<Integer> list;

    // Do not change the method signatures!
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test
     */
    @Before
    public void setUp() throws Exception {
        list = new MyArrayList<>();
    }

    // ----------------MyArrayList class----------------

    /**
     * Aims to test the constructor when the input argument
     * is not valid
     */
    @Test
    public void testConstructorInvalidArg() {
        try {
            new MyArrayList<Integer>(-1);
            fail();
        } catch (IllegalArgumentException e) {
        }

    }

    /**
     * Aims to test the constructor when the input argument is null
     */
    @Test
    public void testConstructorNullArg() {
        MyArrayList<Integer> list2 = new MyArrayList<>(null);

        assertEquals(0, list2.size);
        assertEquals(5, list2.getCapacity());
    }

    /**
     * Aims to test the constructor when the input array has null elements
     */
    @Test
    public void testConstructorArrayWithNull() {
        Integer[] array = {null, null, 3};
        MyArrayList<Integer> list2 = new MyArrayList<>(array);

        assertEquals(3, list2.size);
        assertArrayEquals(array, list2.data);
    }

    /**
     * Aims to test the append method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendAtCapacity() {
        MyArrayList<Integer> atCapacity = new MyArrayList<>(2);
        atCapacity.append(2);
        atCapacity.append(6);
        atCapacity.append(8);

        assertEquals(3, atCapacity.size);
        assertTrue(atCapacity.getCapacity() >= 3);
        assertArrayEquals(new Integer[]{2,6,8}, 
                          java.util.Arrays.copyOf(atCapacity.data, 3));
    }

    /**
     * Aims to test the append method when null is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendNull() {
        MyArrayList<Integer> max = new MyArrayList<>(1);
        max.append(10);
        max.append(null);
        assertEquals(2, max.size);
        assertNull(max.get(1));
    }

    /**
     * Aims to test the add method when input index is out of bounds
     */
    @Test
    public void testAddOutOfBounds() {
        try {
            list.add(4, 10);
            fail();
        }
        catch (IndexOutOfBoundsException e) {

        }
    }

    /**
     * Insert multiple (eg. 1000) elements sequentially beyond capacity -
     * Check reflection on size and capacity
     * Hint: for loop could come in handy
     */
    @Test
    public void testAddMultiple() {
        for (int i = 0; i < 100; i++) {
            list.add(i, i);
        }

        assertEquals(100, list.size);
        assertTrue(list.getCapacity() >= 100);

        for (int i = 0; i < 100; i++) {
            assertEquals((Integer)i, list.get(i));
        }
    }

    /**
     * Aims to test the get method when input index is out of bound
     */
    @Test
    public void testGetOutOfBound() {
        try {
            list.get(0);
            fail();
        } catch (IndexOutOfBoundsException e){

        }
    }

    /**
     * Aims to test the set method when input index is out of bound
     */
    @Test
    public void testSetOutOfBound() {
        try{
            list.set(2, 9);
            fail();
        } catch (IndexOutOfBoundsException e){

        }
    }

    /**
     * Aims to test the remove method when removing multiple items from a list
     */
    @Test
    public void testRemoveMultiple() {
        for (int i = 0; i < 5; i++){
            list.append(i);
        }

        list.remove(0); 
        list.remove(1); 

        assertEquals(3, list.size);
        assertArrayEquals(new Integer[]{1,3,4}, 
                          java.util.Arrays.copyOf(list.data, 3));
    }

    /**
     * Aims to test the remove method when input index is out of bound
     */
    @Test
    public void testRemoveOutOfBound() {
        try {
            list.remove(0);
            fail();
        } catch (IndexOutOfBoundsException e){

        }
    }

    /**
     * Aims to test the expandCapacity method when
     * requiredCapacity is strictly less than the current capacity
     */
    @Test
    public void testExpandCapacitySmaller() {
        MyArrayList<Integer> array = new MyArrayList<>(10);
        try {
            array.expandCapacity(5);
            fail();
        } catch (IllegalArgumentException e){

        }
        assertEquals(10, array.getCapacity());
    }

    /**
     * Aims to test the expandCapacity method when
     * requiredCapacity is greater than current capacity * 2 and default capacity
     */
    @Test
    public void testExpandCapacityLarge() {
        MyArrayList<Integer> array = new MyArrayList<>(5);
        array.expandCapacity(20);

        assertEquals(20, array.getCapacity());
    }
}
