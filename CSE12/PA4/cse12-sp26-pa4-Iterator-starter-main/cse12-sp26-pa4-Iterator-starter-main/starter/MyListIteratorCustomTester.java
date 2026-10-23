/*
   Name: Connor Gibson
   Email: cogibson@ucsd.edu
   PID: A18677169
   Sources used: None
  
   This file creates a linkedlist and extends abstractlist
 */
// If there are imports, they should go here.
import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import java.util.ListIterator;
import org.junit.*;
/**
 * Here is a custom tester that I implemented 
 */
public class MyListIteratorCustomTester {

    private MyLinkedList<Integer> list;
    private ListIterator<Integer> iterator;
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() throws Exception {
        list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        iterator = list.listIterator();
    }

    /**
     * Aims to test the next() method when iterator is at end of the list 
     */
    @Test
    public void testNextEnd() {
        iterator.next(); 
        iterator.next();
        iterator.next();
        try {
            iterator.next();
            fail();
        } catch (NoSuchElementException e) {
        }
    }

    /**
     * Aims to test the previous() method when iterator is at the start of the 
     * list 
     */
    @Test
    public void testPreviousStart() {
        try {
            iterator.previous();
            fail();
        } catch (NoSuchElementException e) {
        }
    }

    /**
     * Aims to test the previous() method when iterator is at the start of the 
     * list 
     */
    @Test
    public void testAddInvalid() {
        try {
            iterator.add(null);
            fail();
        } catch (NullPointerException e) {
        }
    }

    /**
     * Aims to test the set(E e) method when canRemoveOrSet is false
     */
    @Test
    public void testCantSet() {
        try {
            iterator.set(10);
            fail();
        } catch (IllegalStateException e) {
        }
    }

    /**
     * Aims to test the remove() method when canRemoveOrSet is false
     */
    @Test
    public void testCantRemove() {
        try {
            iterator.remove();
            fail();
        } catch (IllegalStateException e) {
        }
    }

    /**
     * Aims to tests the hasNext() method at the end of a list
     */
    @Test
    public void testHasNextEnd() {
        iterator.next();
        iterator.next();
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    /**
     * Aims to test the hasPrevious() method at the start of a list
     */
    @Test
    public void testHasPreviousStart() {
        assertFalse(iterator.hasPrevious());
    }

    /**
     * Aims to test the previousIndex() method at the start of a list
     */
    @Test
    public void testPreviousIndexStart() {
        assertEquals(-1, iterator.previousIndex());
    }

    /**
     * Aims to test the nextIndex() method at the end of a list
     */
    @Test
    public void testNextIndexEnd() {
        iterator.next();
        iterator.next();
        iterator.next();
        assertEquals(3, iterator.nextIndex());
    }
}
