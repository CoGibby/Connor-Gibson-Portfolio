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
 * This class handmakes an arraylist with mylist methods
 */
public class MyArrayList<E> implements MyList<E> {
    Object[] data;
    int size; 
    private static final int DEFAULT = 5;    
    private static final int MULTIPLY = 2;    

    /**
     * Default no arg constructor 
     */
    public MyArrayList(){
        data = new Object[DEFAULT];
        size = 0;
    }
    /**
     * Arg constructor for capacity of the object
     *
     * @param initialCapacity Capacity of the object data
     */
    public MyArrayList(int initialCapacity){
        if (initialCapacity < 0){
            throw new IllegalArgumentException();
        }
        data = new Object[initialCapacity];
        size = 0;

    }
    /**
     * Arg constructor that assigns data to arr
     *
     * @param arr A list of elements E
     */
    public MyArrayList (E[] arr){
        if (arr == null){
            data = new Object[DEFAULT];
            size = 0;
        }
        else {
            size = arr.length;
            data = new Object[arr.length];
            for (int i = 0; i < arr.length; i++){
                data[i] = arr[i];
            }
        }

    }
    /**
     * Expands the capacity of the object data
     *
     * @param requiredCapacity The minimum capacity of the list
     */
    @Override
    public void expandCapacity (int requiredCapacity){
        if (requiredCapacity < data.length){
            throw new IllegalArgumentException();
        }
        int currentCapacity = data.length;
        int newCapacity = -1;
        if (currentCapacity != 0){
            newCapacity = currentCapacity * MULTIPLY;
        }
        if (currentCapacity == 0){
            newCapacity = DEFAULT;
        }
        if (newCapacity < requiredCapacity){
            newCapacity = requiredCapacity;
        }
        Object[] newData = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }

        data = newData;
    }

    /**
     * Getter for capacity
     *
     * @return An integer of the capacity
     */
    @Override
    public int getCapacity(){
        return data.length;
    }

    /**
    * Inserts an element at the specified index, shifting later elements right
    * Expands the underlying array if the list is at full capacity
    *
    * @param index  the position to insert the new element
    * @param element the value to be added to the list
    */
    @Override
    public void add(int index, E element){
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        if (size == data.length){
            expandCapacity(size + 1);

        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = element;

        size++;

    }

    /**
    * Adds an element to the end of the list
    * Expands the underlying array if the list is full
    *
    * @param element the value to append to the end
    */
    @Override
    public void append(E element){
        if (size == data.length){
            expandCapacity(size + 1);
        }
        data[size] = element;
        size++;
    }

    /**
    * Retrieves the element stored at the given index
    *
    * @param index the position of the element to return
    * @return the element at the specified index
    */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        E data = data[index];
        return data;
    }

    /**
    * Replaces the element at the given index with a new value
    * Returns the element that was previously stored there
    *
    * @param index the position to overwrite
    * @param element the new value to store
    * @return the old element previously at this index
    */
    @Override
    public E set(int index, E element){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        E overwrittenElem = (E) data[index];
        data[index] = element;
        return overwrittenElem;
    }

    /**
    * Removes and returns the element at the specified index
    * Shifts later elements left to fill the gap
    *
    * @param index the position of the element to remove
    * @return the removed element
    */
    @Override
    public E remove(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        E removed = (E) data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[size - 1] = null;

        size--;
        return removed;
    }
    
    /**
    * Returns the number of elements currently stored in the list
    *
    * @return the current size of the list
    */
    @Override
    public int size(){
        return size;
    }
}