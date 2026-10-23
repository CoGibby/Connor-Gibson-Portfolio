/*
   Name: Connor Gibson
   Email: cogibson@ucsd.edu
   PID: A18677169
   Sources used: None
  
   This file creates a linkedlist and extends abstractlist
 */

// If there are imports, they should go here.
import java.util.AbstractList;
/**
 * This is a public class that extends abstractlist, 
 * which tries to mimick a linkedlist 
 */
public class MyLinkedList<E> extends AbstractList<E> {

    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next 
     * nodes
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /** 
         * Constructor to create a singleton Node
         * @param element the element to store in this node
         */
        public Node(E element) {
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /**
         * Set the previous node reference
         * @param prev the new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;
        }

        /**
         * Set the next node reference
         * @param next the new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /**
         * Set the element stored in this node
         * @param element the new element
         */
        public void setElement(E element) {
            this.data = element;
        }

        /**
         * Get the next node
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Get the previous node
         * @return the previous node
         */
        public Node getPrev() {
            return this.prev;
        }

        /**
         * Get the element stored in this node
         * @return the element in this node
         */
        public E getElement() {
            return this.data;
        }
    }
    /**
     * Construct an empty linked list with sentinel head and tail nodes
     */
    public MyLinkedList() {
        this.size = 0;
        this.head = new Node(null);
        this.tail = new Node(null);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    /**
     * Get the number of elements in the list
     * @return the size of the list
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Get the element at the given index
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = getNth(index);
        return node.data;
    }

    /**
     * Insert an element at the specified index
     * @param index the index at which to insert the element
     * @param data the element to insert
     */
    @Override
    public void add(int index, E data) {
        if (data == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }

        Node current = (index == this.size) ? this.tail : getNth(index);
        Node before = current.prev;

        Node newNode = new Node(data);
        newNode.prev = before;
        newNode.next = current;
        before.next = newNode;
        current.prev = newNode;

        this.size++;
    }

    /**
     * Append an element to the end of the list
     * @param data the element to add
     * @return true after insertion
     */
    @Override
    public boolean add(E data) {
        if (data == null) {
            throw new NullPointerException();
        }

        Node newNode = new Node(data);
        Node last = this.tail.prev;

        last.next = newNode;
        newNode.prev = last;
        newNode.next = this.tail;
        this.tail.prev = newNode;

        this.size++;
        return true;
    }

    /**
     * Replace the element at the given index with new data
     * @param index the index of the element to replace
     * @param data the new element
     * @return the old element previously stored at index
     */
    @Override
    public E set(int index, E data) {
        if (data == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        Node node = getNth(index);
        E old = node.data;
        node.data = data;
        return old;
    }

    /**
     * Remove the element at the given index
     * @param index the index of the element to remove
     * @return the removed element
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        Node removed = getNth(index);
        E data = (E) removed.data;

        removed.prev.next = removed.next;
        removed.next.prev = removed.prev;
        removed.next = null;
        removed.prev = null;
        this.size--;
        return data;
    }

    /**
     * Remove all elements from the list
     */
    @Override
    public void clear() {
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.size = 0;
    }

    /**
     * Check whether the list is empty
     * @return true if the list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return this.head.next == this.tail;
    }

    /**
     * Return the Node at the given index
     * @param index the index of the node to retrieve
     * @return the node at the specified index
     */
    protected Node getNth(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        Node curr = this.head.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }
}
