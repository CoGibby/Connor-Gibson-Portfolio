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
import static org.junit.Assert.*;
import org.junit.*;
/**
* This method will have custom tests for specific failures
*/
public class CustomTester {

    /**
     * Optional setup
     */
    @Before
    public void setup() {
        // optional
    }

    /**
     * Tests deque constructor by doing invalid capacity
     */
    @Test
    public void testDequeConstructorInvalidCapacity() {
        try {
            new MyDeque<>(-1);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * Tests deque by forcing capacity to expand
     */
    @Test
    public void testDequeExpandCapacityOrder() {
        MyDeque<Integer> deque = new MyDeque<>(2);
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        assertEquals(Integer.valueOf(1), deque.removeFirst());
        assertEquals(Integer.valueOf(2), deque.removeFirst());
        assertEquals(Integer.valueOf(3), deque.removeFirst());
        assertEquals(0, deque.size());
    }

    /**
     * Tests deque by wrapping around first
     */
    @Test
    public void testDequeAddFirstWrapAround() {
        MyDeque<Integer> deque = new MyDeque<>(3);
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.removeLast();
        deque.addFirst(0);
        assertEquals(Integer.valueOf(0), deque.peekFirst());
    }

    /**
     * Tests deque by adding last and wrapping around
     */
    @Test
    public void testDequeAddLastWrapAround() {
        MyDeque<Integer> deque = new MyDeque<>(3);
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.removeFirst();
        deque.addLast(4);
        assertEquals(Integer.valueOf(2), deque.removeFirst());
        assertEquals(Integer.valueOf(3), deque.removeFirst());
        assertEquals(Integer.valueOf(4), deque.removeFirst());
    }

    /**
     * Tests deque removeFirst basic functionality
     */
    @Test
    public void testDequeRemoveFirstBasic() {
        MyDeque<Integer> deque = new MyDeque<>(3);
        deque.addLast(1);
        deque.addLast(2);
        assertEquals(Integer.valueOf(1), deque.removeFirst());
        assertEquals(Integer.valueOf(2), deque.removeFirst());
        assertNull(deque.removeFirst());
    }

    /**
     * Tests deque removeLast basic functionality
     */
    @Test
    public void testDequeRemoveLastBasic() {
        MyDeque<Integer> deque = new MyDeque<>(3);
        deque.addLast(5);
        deque.addLast(6);
        assertEquals(Integer.valueOf(6), deque.removeLast());
        assertEquals(Integer.valueOf(5), deque.removeLast());
        assertNull(deque.removeLast());
    }

    /**
     * Tests stack push and pop for LIFO behavior
     */
    @Test
    public void testMyStackPushPopLIFO() {
        MyStack<Integer> stack = new MyStack<>(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(Integer.valueOf(3), stack.pop());
        assertEquals(Integer.valueOf(2), stack.pop());
        assertEquals(Integer.valueOf(1), stack.pop());
        assertNull(stack.pop());
    }

    /**
     * Tests queue enqueue and dequeue for FIFO behavior
     */
    @Test
    public void testMyQueueEnqueueDequeueFIFO() {
        MyQueue<Integer> queue = new MyQueue<>(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(Integer.valueOf(2), queue.dequeue());
        assertEquals(Integer.valueOf(3), queue.dequeue());
        assertNull(queue.dequeue());
    }

    /**
     * Tests deque size after mixed operations
     */
    @Test
    public void testDequeSizeMixedOps() {
        MyDeque<Integer> deque = new MyDeque<>(4);
        deque.addLast(1);
        deque.addLast(2);
        deque.addFirst(0);
        deque.removeLast();
        deque.addLast(3);
        assertEquals(3, deque.size());
    }

    /**
     * Tests queue peek functionality
     */
    @Test
    public void testMyQueuePeek() {
        MyQueue<Integer> queue = new MyQueue<>(3);
        queue.enqueue(5);
        queue.enqueue(6);
        assertEquals(Integer.valueOf(5), queue.peek());
        queue.dequeue();
        assertEquals(Integer.valueOf(6), queue.peek());
    }
}
