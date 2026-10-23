/*
   Name: Connor Gibson
   Email: cogibson@ucsd.edu
   PID: A18677169
   Sources used: None
  
   This file is a custom junit tester to my code
 */

// If there are imports, they should go here.
import static org.junit.Assert.*;

import org.junit.*;
/**
 * Here is a custom tester for PA3 which 
 * was created by me
 */
public class MyLinkedListCustomTester {

	// Optional: add test variables here
	private MyLinkedList<Integer> emptyIntegerList;
    private MyLinkedList<String> threeStringList;
    private MyLinkedList<Integer> left, right;

	/**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
	@Before
	public void setUp() throws Exception {
		// Optional: add setup here
		emptyIntegerList = new MyLinkedList<>();
    	threeStringList = new MyLinkedList<>();
    	threeStringList.add("A");
    	threeStringList.add("B");
    	threeStringList.add("C");
    	left = new MyLinkedList<>();
    	right = new MyLinkedList<>();
	}

	/**
	 * Aims to test the add(E data) method with a valid argument.
	 */
	@Test
	public void testCustomAdd() {
		// TODO: add your test here
    	assertTrue(emptyIntegerList.add(1));
    	assertTrue(emptyIntegerList.add(2));
    	assertTrue(emptyIntegerList.add(3));
		
    	assertEquals(3, emptyIntegerList.size());
    	assertEquals((Integer)1, emptyIntegerList.get(0));
    	assertEquals((Integer)2, emptyIntegerList.get(1));
    	assertEquals((Integer)3, emptyIntegerList.get(2));

    	try {
        	emptyIntegerList.add(null);
        	fail();
    	} catch (NullPointerException e) {
    	}
	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the beginning of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToStart() {
		// TODO: add your test here
		threeStringList.add(0, "E");
        assertEquals(4, threeStringList.size());
        assertEquals("E", threeStringList.get(0));
        assertEquals("A", threeStringList.get(1));
	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the middle of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToMiddle() {
		// TODO: add your test here
		threeStringList.add(1, "F");
        assertEquals(4, threeStringList.size());
        assertEquals("A", threeStringList.get(0));
        assertEquals("F", threeStringList.get(1));
        assertEquals("B", threeStringList.get(2));
	}

	/**
	 * Aims to test the remove(int index) method. Remove from an empty list.
	 */
	@Test
	public void testCustomRemoveFromEmpty() {
		// TODO: add your test here
		try {
            emptyIntegerList.remove(0);
            fail();
        } catch (IndexOutOfBoundsException e) {
        }
	}

	/**
	 * Aims to test the remove(int index) method.
	 * Remove a valid argument from the middle of MyLinkedList.
	 */
	@Test
	public void testCustomRemoveFromMiddle() {
		// TODO: add your test here
		MyLinkedList<Integer> nums = new MyLinkedList<>();
    	nums.add(1);
    	nums.add(2);
    	nums.add(3);
    	nums.add(4);
    	nums.add(5);

    	Integer removed = nums.remove(2);
    	assertEquals((Integer)3, removed);
    	assertEquals(4, nums.size());
    	assertEquals((Integer)4, nums.get(2));

    	Integer removed2 = nums.remove(2);
    	assertEquals((Integer)4, removed2);
    	assertEquals(3, nums.size());
    	assertEquals((Integer)5, nums.get(2));
	}

	/**
	 * Aims to test the set(int index, E data) method.
	 * Set an out-of-bounds index with a valid data argument.
	 */
	@Test
	public void testCustomSetIdxOutOfBounds() {
		// TODO: add your test here
		try {
            emptyIntegerList.set(1, 5);
            fail();
        } catch (IndexOutOfBoundsException e) {
        }
	}

	/**
	 * Aims to test the mergeLists(MyLinkedList<E> left, MyLinkedList<E> right)
	 * method.
	 * Merge lists when one is longer than the other. 
	 */
	@Test
	public void testCustomMergeUnequalSize() {
		// TODO: add your test here
		left.add(98);
        left.add(101);
        left.add(102);
        right.add(99);
        MyLinkedList<Integer> merged = MyAlgorithm.mergeLists(left, right);
        assertEquals(4, merged.size());
        assertEquals((Integer)98, merged.get(0));
        assertEquals((Integer)99, merged.get(1));
        assertEquals((Integer)101, merged.get(2));
        assertEquals((Integer)102, merged.get(3));
	}

	/**
	 * Aims to test the mergeLists(MyLinkedList<E> left, MyLinkedList<E> right)
	 * method.
	 * Merge lists when both lists share a value.
	 */
	@Test
	public void testCustomMergeDuplicates() {
		// TODO: add your test here
		left.add(1);
        left.add(3);
        left.add(5);
        right.add(3);
        right.add(4);
        MyLinkedList<Integer> merged = MyAlgorithm.mergeLists(left, right);
        assertEquals(5, merged.size());
        assertEquals((Integer)1, merged.get(0));
        assertEquals((Integer)3, merged.get(1));
        assertEquals((Integer)3, merged.get(2));
        assertEquals((Integer)4, merged.get(3));
        assertEquals((Integer)5, merged.get(4));
	}

}
