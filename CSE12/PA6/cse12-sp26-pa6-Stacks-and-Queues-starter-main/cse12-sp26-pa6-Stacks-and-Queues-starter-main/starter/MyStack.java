/*
 * Name: Connor Gibson
 * Email: cogibosn@ucsd.edu
 * PID: A18677169
 *
 * IMPORTANT: Do not change the method headers. Your tests will be run against
 * good and bad implementations of Student/Course/Sanctuary. You will only
 * receive points if your test passes when run on a good implementation and
 * fails for the corresponding bad implementation.
 */
/**
 * This class implements the Stack ADT using a MyDeque instance variable called
 * theStack.
 */
public class MyStack<E> implements StackInterface<E> {
    MyDeque<E> theStack;

    /**
     * Constructor to create new MyStack that holds a MyDeque.
     *
     * @param initialCapacity The max amount of elements this data structure
     *                        can hold.
     */
    public MyStack(int initialCapacity) {
        theStack = new MyDeque<E>(initialCapacity);
    }

    /**
     * Checks if the stack is empty.
     *
     * @return True if there are no elements in the stack, false otherwise.
     */
    @Override
    public boolean empty() {
        if (theStack.size() == 0){
            return true;
        }
        return false;
    }

    /**
     * Adds the specified element to the top of this StackInterface.
     *
     * @param element the element to add to the stack
     */
    @Override
    public void push(E element) {
        theStack.addLast(element);
    }

    /**
     * Removes the element at the top of this StackInterface.
     * Returns the element removed, or null if there was no such element.
     *
     * @return the element removed, or null if the size was zero.
     */
    @Override
    public E pop() {
        return theStack.removeLast();
    }

    /**
     * Returns the element at the top of this stack, or null if there was no
     * such element.
     *
     * @return the element at the top, or null if the size was zero
     */
    @Override
    public E peek() {
        return theStack.peekLast();
    }

    /**
     * Returns the number of elements in this stack.
     *
     * @return the number of elements in this stack.
     */
    @Override
    public int size() {
        return theStack.size();
    }

}
