/*
   Name: Connor Gibson
   Email: cogibson@ucsd.edu
   PID: A18677169
   Sources used: None
  
   This file creates a linkedlist and extends abstractlist
 */


/**
 * This is a public class that creates a hash map
 * object 
 */
public class MyHashMap<K,V> {
    private static final int DEFAULT_CAPACITY = 5;
    private static final double LOAD_FACTOR = 0.8;
    private static final int EXPAND_CAPACITY_RATIO = 2;
    private static final int HASH_CODE_MASK = 0x7fffffff;

    Node[] hashTable;
    int size;
    /**
     * No arg constructor that initialized size and hashtable
     */
    public MyHashMap() {
        /* Add your implementation here */
        // TODO
        this.hashTable = (Node<K,V>[]) new Node[DEFAULT_CAPACITY];
        this.size = 0;
    }
    /**
     * Arg constructor that initializes size and hashtable to
     * have the size equal to the parameter initialCapacity
     * @param initialCapacity The intended capacity of the hashmap
     */
    public MyHashMap(int initialCapacity) {
        /* Add your implementation here */
        // TODO
        if (initialCapacity <= 0){
            throw new IllegalArgumentException();
        }
        this.hashTable = (Node<K,V>[]) new Node[initialCapacity];
        this.size = 0;
    }
    /**
     * This method gets the value of a key in the list
     * 
     * @param key The key in the list to find the value 
     * @return V the value associated with the respective key
     */
    public V get(K key) {
        /* Add your implementation here */
        if (key == null){
            throw new NullPointerException();
        }
        int index = Math.abs(key.hashCode()) % hashTable.length;
        Node<K,V> iterate = hashTable[index];
        while (iterate != null) {
            if (iterate.key.equals(key)) {
                return iterate.value;
            }
            iterate = iterate.next;
        }
        return null;
    }

    /**
     * This method puts the specified value with the specified key
     * in the hash map. If the key already exists, its value is replaced
     * and the old value is returned. If the key is new, it is added to
     * the end of the linked list at its hashed index
     *
     * @param key The key whose value is to be inserted/updated
     * @param value The value to associate with the given key
     * @return V the previous value mapped to the key, or null if the key
     * was not previously in the hash map
     */
    public V put(K key, V value) {
        /* Add your implementation here */
        if (key == null || value == null){
            throw new NullPointerException();
        }
        if (size >= hashTable.length * LOAD_FACTOR){
            expandCapacity();
        }
        int index = Math.abs(key.hashCode()) % hashTable.length;
        Node<K,V> iterate = hashTable[index];
        while (iterate != null) {
            if (iterate.key.equals(key)) {
                V oldValue = iterate.value;
                iterate.value = value; 
                return oldValue;
            }
            iterate = iterate.next;
        }
        Node<K,V> newNode = new Node<>(key, value);

        if (hashTable[index] == null) {
            hashTable[index] = newNode;
        } else {
            Node<K,V> tail = hashTable[index];
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = newNode;
        }
        size++;
        return null;
    }
    /**
     * This method removes the mapping for the specified key from the
     * hash map if it exists. If the key is found, the associated value
     * is returned or null, otherwise.
     *
     * @param key The key whose mapping is to be removed
     * @return V the value previously associated with the key, or null
     * if the key was not found in the hash map
     */
    public V remove(K key) {
        /* Add your implementation here */
        Node<K,V> previous = null;
        if (key == null){
            throw new NullPointerException();
        }
        int index = Math.abs(key.hashCode()) % hashTable.length;
        Node<K,V> iterate = hashTable[index];
        while (iterate != null) {
            if (iterate.key.equals(key)) {
                if (previous == null){
                    hashTable[index] = iterate.next;
                }
                else {
                    previous.next = iterate.next;
                }
                size--;
                return iterate.value;

            }
            previous = iterate;
            iterate = iterate.next;
        }
        
        return null;
    }
    /**
     * This method returns the number of key & value mappings currently
     * stored in the hash map
     *
     * @return int the number of elements in the hash map
     */
    public int size() {
        /* Add your implementation here */
        return this.size; // TODO
    }
    /**
     * This method returns the current capacity of the hash table
     *
     * @return int the capacity of the underlying hash table
     */
    public int getCapacity() {
        /* Add your implementation here */
        return hashTable.length;
    }
    /**
     * This method removes all key & value mappings from the hash map by
     * resetting the hash table and size
     */
    public void clear() {
        /* Add your implementation here */
        // TODO
        this.hashTable = (Node<K,V>[]) new Node[hashTable.length];
        this.size = 0;
    }
    /**
     * This method checks whether the hash map contains any key & value
     * mappings
     *
     * @return boolean true if the hash map has no elements, false otherwise
     */ 
    public boolean isEmpty() {
        /* Add your implementation here */
        if (this.size == 0){
            return true;
        }
        return false; // TODO
    }
    /**
     * This method doubles the capacity of the hash table and rehashes
     * all existing key & value mappings into the new table; order 
     * is preserved
     */
    public void expandCapacity() {
        /* Add your implementation here */
        Node<K,V>[] prevHashTable = hashTable;
        hashTable = 
                (Node<K,V>[]) new Node[hashTable.length * EXPAND_CAPACITY_RATIO];
        // TODO
        size = 0;
        for (int i = 0; i < prevHashTable.length; i++) {
            Node<K,V> iterate = prevHashTable[i];
            while (iterate != null) {
                put(iterate.key, iterate.value);
                iterate = iterate.next;
            }
        }

    }
    /**
     * This method computes the hash index for a given key using the
     * provided capacity, while ensuring the hash code is !=0 and
     * fits within the bounds of the hash table
     *
     * @param key The key whose hash index is to be computed
     * @param capacity The capacity used to compute the hash index
     * @return int the computed hash index for the key
     */

    public int getHash(K key, int capacity) {
        /* Add your implementation here */
        // TODO: Add checks for invalid inputs
        if (key == null){
            throw new NullPointerException();
        }
        if (capacity <= 0){
            throw new IllegalArgumentException();
        }
        // Hashing function is given here. DO NOT MODIFY THIS
        // You do not need to understand how it works
        return (key.hashCode() & HASH_CODE_MASK) % capacity;
    }

    /**
     * A Node class that holds (key, value) pairs and references to the next node in the linked list
     */
    protected class Node<K,V> {
        K key;
        V value;
        Node next;

        /**
         * Constructor to create a single node
         * @param key key to store in this node
         * @param value value to store in this node
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        /**
         * Accessor to get the next node in the list
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Set the next node in the list
         * @param n the new next node
         */
        public void setNext(Node n) {
            next = n;
        }

        /**
         * Accessor to get the node's key
         * @return this node's key
         */
        public K getKey() {
            return this.key;
        }

        /**
         * Set the node's key
         * @param key the new key
         */
        public void setKey(K key) {
            this.key = key;
        }

        /**
         * Accessor to get the node's value
         * @return this node's value
         */
        public V getValue() {
            return this.value;
        }

        /**
         * Set the node's value
         * @param value the new value
         */
        public void setValue(V value) {
            this.value = value;
        }

        /**
         * Check if this node is equal to another node
         * @param other the other node to check equality with
         * @return whether or not this node is equal to the other node
         */
        public boolean equals(Node<K, V> other) {
            return this.key.equals(other.key) && this.value.equals(other.value);
        }
    }
}
