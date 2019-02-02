package misc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Samuel Nuttall
 * <p>
 * Tests for the Queue class
 */
public class QueueTests {

    private static final Logger log = LoggerFactory.getLogger(QueueTests.class);

    /**
     * Tests that a queue is created with the max size given in the constructor parameter according to
     * the specification
     */
    @Test
    @DisplayName("Test Queue Create And Max()")
    public void testCreateQueueValidInput() {
        assertAll(
                () -> {
                    Queue queue = new Queue(1);
                    assertEquals(1, queue.max());
                },
                () -> {
                    Queue queue = new Queue(100);
                    assertEquals(100, queue.max());
                });
    }

    /**
     * Tests calling the constructor of Queue with max queue size of zero
     * Expected behavior would be to not allow this because it would be pointless, and would not
     * follow the producer consumer ideology behind a queue
     * <p>
     * Test Fails because no error is thrown with a size of zero
     */
    @Test
    @DisplayName("Test Create Queue Length Zero")
    public void testCreateQueueWithBorderCaseZero() {
        assertThrows(RuntimeException.class, () -> {
            Queue queue = new Queue(0);
        });
    }

    /**
     * Tests calling the constructor of Queue with invalid input to see if errors are thrown
     */
    @Test
    @DisplayName("Test Create Invalid Parameter")
    public void testCreateQueueInvalidParam() {
        assertThrows(RuntimeException.class, () -> {
            Queue queue = new Queue(-1);
        });
    }


    /**
     * Test that size() works correctly and gets the current amount that is in the queue
     * <p>
     * Test 1: 5 items
     * Test 2: Edge Case 10 items
     * Test 3: Enqueue, dequeue, then get size
     */
    @Test
    @DisplayName("Test Queue enqueue() and size()")
    public void testQueueSize() {

        assertAll(
                () -> {
                    Queue queue = new Queue(10);
                    // Add 5 elements to the queue
                    enqueueAmt(queue, 5);
                    assertEquals(5, queue.size());
                },
                () -> {
                    Queue queue = new Queue(10);
                    // Add 10 elements to the queue
                    enqueueAmt(queue, 10);
                    assertEquals(10, queue.size());
                },
                () -> {
                    Queue queue = new Queue(10);
                    // Add 8 elements to the queue
                    enqueueAmt(queue, 8);
                    //dequeue twice
                    queue.dequeue();
                    queue.dequeue();
                    assertEquals(6, queue.size());
                }
        );
    }

    /**
     * Tests that FIFO dequeue works as expected given standard input and that calling dequeue
     * when there is nothing left on the queue returns null as expected
     */
    @Test
    @DisplayName("Test Standard Dequeue")
    public void testStandardDequeue() {
        Queue queue = new Queue(4);
        enqueueAmt(queue, 4);
        String deque1 = (String) queue.dequeue();
        String deque2 = (String) queue.dequeue();
        String deque3 = (String) queue.dequeue();
        String deque4 = (String) queue.dequeue();

        assertEquals("Item 1", deque1);
        assertEquals("Item 2", deque2);
        assertEquals("Item 3", deque3);
        assertEquals("Item 4", deque4);
        assertNull(queue.dequeue());
    }

    /**
     * Tests enqueue past the max size of the queue and the behavior of Dequeue when this occurs
     * The expected behavior of a queue when enqueue past the max size would be to remove the front item
     * but maintain the FIFO order
     * <p>
     * This test fails because the the FIFO behavior of the queue is not maintained
     */
    @Test
    @DisplayName("Test Queue Behavior Past Max")
    public void testQueuePastMax() {
        Queue queue = new Queue(4);
        enqueueAmt(queue, 5);
        String deque1 = (String) queue.dequeue();
        String deque2 = (String) queue.dequeue();
        String deque3 = (String) queue.dequeue();
        String deque4 = (String) queue.dequeue();

        assertEquals("Item 2", deque1);
        assertEquals("Item 3", deque2);
        assertEquals("Item 4", deque3);
        assertEquals("Item 5", deque4);
        assertNull(queue.dequeue());
    }


    /**
     * Helper method for tests that enqueues an amount of Strings in the queue for testing output
     *
     * @param amt Amount of String objects that will be enqueued
     */
    private void enqueueAmt(Queue queue, int amt) {
        for (int i = 0; i < amt; i++) {
            queue.enqueue(("Item " + (i + 1)));
        }
    }


}
