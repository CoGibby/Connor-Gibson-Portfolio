/*
 * Name: Casey Hild
 * Email: child@ucsd.edu
 * PID: A16953257
 * Sources Used: JDK 17 Doc
 *
 * IMPORTANT: Do not change the method headers. Your tests will be run against
 * good and bad implementations of Student/Course/Sanctuary. You will only
 * receive points if your test passes when run on a good implementation and
 * fails for the corresponding bad implementation.
 */

import static org.junit.Assert.*;
import org.junit.*;

/**
 * The custom tester for PA5, which covers some basic test cases.
 */
public class CustomTester {
    // Optional: add test variables here

    /**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
    @Before
    public void setup() {
        // Optional: add setup here
    }

    // ----------------MyHashMap class----------------

    /**
     * Test MyHashMap constructor with an invalid initial capacity
     */
    @Test
    public void testMyHashMapConstructorInvalidCapacity() {
        // TODO: add your test here
        try {
            new MyHashMap<>(0);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * Test MyHashMap get with a null key
     */
    @Test
    public void testMyHashMapGetNullKey() {
        // TODO: add your test here
        MyHashMap<Integer, Integer> map = new MyHashMap<>();
        try {
            map.get(null);
            fail();
        } catch (NullPointerException e) {
        }
    }

    /**
     * Test MyHashMap get with a key that does not exist in the hash table
     */
    @Test
    public void testMyHashMapGetKeyDoesNotExist() {
        // TODO: add your test here
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("z", "zebra");
        assertNull(map.get("y"));

    }

    /**
     * Test MyHashMap put with a null key
     */
    @Test
    public void testMyHashMapPutNullKey() {
        // TODO: add your test here
        MyHashMap<Integer, Integer> map = new MyHashMap<>();
        try {
            map.put(null, 1);
            fail();
        } catch (NullPointerException e) {
        }
    }

    /**
     * Test MyHashMap put with a key that already exists in the hash table
     */
    @Test
    public void testMyHashMapPutKeyAlreadyExists() {
        // TODO: add your test here
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("z", 1);
        int old = map.put("z", 2);
        assertEquals(1, old);
    }

    /**
     * Test MyHashMap remove with a null key
     */
    @Test
    public void testMyHashMapRemoveNullKey() {
        // TODO: add your test here
        MyHashMap<Integer, Integer> map = new MyHashMap<>();
        try {
            map.remove(null);
            fail();
        } catch (NullPointerException e) {
        }
    }

    /**
     * Test MyHashMap remove with a key that does not exist in the hash table
     */
    @Test
    public void testMyHashMapRemoveKeyDoesNotExist() {
        // TODO: add your test here
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("z", "zebra");
        assertNull(map.remove("y"));
    }

    /**
     * Test MyHashMap getHash with a null key
     */
    @Test
    public void testMyHashMapGetHashNullKey() {
        // TODO: add your test here
        MyHashMap<Integer, Integer> map = new MyHashMap<>();
        try {
            map.getHash(null, 2);
            fail();
        } catch (NullPointerException e) {
        }
    }

    // ----------------MyHashSet class----------------

    /**
     * Test MyHashMap put with a key that already exists in the hash table
     */
    @Test
    public void testMyHashSetAddAlreadyExists() {
        // TODO: add your test here
        MyHashSet<Integer> set = new MyHashSet<>();
        assertTrue(set.add(2));
        assertFalse(set.add(2));
    }

    /**
     * Test MyHashSet remove with a key that does not exist in the hash table
     */
    @Test
    public void testMyHashSetRemoveDoesNotExist() {
        // TODO: add your test here
        MyHashSet<String> set = new MyHashSet<>();
        set.add("hello");
        assertFalse(set.remove("yellow"));
    }
}