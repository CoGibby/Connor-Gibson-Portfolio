/*
   Name: Connor Gibson
   Email: cogibson@ucsd.edu
   PID: A18677169
   Sources used: None
  
   This file creates a linkedlist and extends abstractlist
 */

/**
 * Creates a hash set like object implementation 
 */
public class MyHashSet<E> {
    public static final Object DEFAULT_OBJECT = new Object();
    MyHashMap<E, Object> hashMap;
    /**
     * No arg constructor that initializes the hash map
     * with its default capacity
     */
    public MyHashSet() {
        /* Add your implementation here */
        // TODO
        this.hashMap = new MyHashMap<>();
    }
    /**
     * Arg constructor that initializes the hash map to have
     * the specified initial capacity
     *
     * @param initialCapacity The intended capacity of the hash set
     */
    public MyHashSet(int initialCapacity) {
        /* Add your implementation here */
        if (initialCapacity <= 0){
            throw new IllegalArgumentException();
        }
        // TODO
        this.hashMap = new MyHashMap<>(initialCapacity);
    }
    /**
     * This method adds the specified element to the hash set if it is
     * not already present, or if new, returns true; otherwise it 
     * returns false
     *
     * @param element The element to be added to the hash set
     * @return boolean true if the element was not already present,
     * false otherwise
     */
    public boolean add(E element) {
        /* Add your implementation here */
        if (element == null){
            throw new NullPointerException();
        }
        Object previous = hashMap.put(element, DEFAULT_OBJECT);
        if (previous == null){
            return true;
        }
        return false; // TODO
    }
    /**
     * This method removes the specified element from the hash set if
     * it exists; If found, removed, & the method
     * returns true; otherwise it returns false
     *
     * @param element The element to be removed from the hash set
     * @return boolean true if the element was present and removed,
     * false otherwise
     */
    public boolean remove(E element) {
        /* Add your implementation here */
        if (element == null){
            throw new NullPointerException();
        }
        Object present = hashMap.get(element);
        if (present != null){
            hashMap.remove(element);
            return true;
        } else {
            return false;
        }
    }
    /**
     * This method checks whether the specified element is included
     * within the hash set
     *
     * @param element The element to check for inclusion
     * @return boolean true if the element is in the hash set,
     * false otherwise
     */
    public boolean contains(E element) {
        /* Add your implementation here */
        if (element == null){
            throw new NullPointerException();
        }
        Object present = hashMap.get(element);
        if (present == null){
            return false;
        }
        return true; // TODO
    }
    /**
     * This method returns the number of elements currently stored
     * in the hash set
     *
     * @return int the number of elements in the hash set
     */
    public int size() {
        /* Add your implementation here */
        return hashMap.size(); // TODO
    }
    /**
     * This method removes all elements from the hash set by clearing
     * the hash map,then the set will be empty
     */
    public void clear() {
        /* Add your implementation here */
        // TODO
        hashMap.clear();
    }
    /**
     * This method checks whether the hash set contains any elements
     *
     * @return boolean true if the hash set is empty, false otherwise
     */
    public boolean isEmpty() {
        /* Add your implementation here */
        if (hashMap.size() == 0){
            return true;
        }
        return false; // TODO
    }
}