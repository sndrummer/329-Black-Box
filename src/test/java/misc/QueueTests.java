package misc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Samuel Nuttall
 * <p>
 * Queue Tests
 */
public class QueueTests {

    private static final Logger log = LoggerFactory.getLogger(QueueTests.class);


    /**
     * Tests that a queue is created with the max size given in the constructor parameter according to
     * the specification
     */
    @Test
    @DisplayName("Queue Create Test And Max()")
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
     * Tests enqueue
     */
    @Test
    @DisplayName("Test Create Invalid Parameter")
    public void testDequeue() {
        Queue queue = new Queue(10);
        queue.enqueue(11);

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
                    for (int i = 0; i < 5; i++) {
                        queue.enqueue(("Item " + i));
                    }

                    assertEquals(5, queue.size());
                },
                () -> {
                    Queue queue = new Queue(10);
                    // Add 10 elements to the queue
                    for (int i = 0; i < 10; i++) {
                        queue.enqueue(("Item " + i));
                    }
                    assertEquals(10, queue.size());
                },
                () -> {
                    Queue queue = new Queue(10);
                    // Add 8 elements to the queue
                    for (int i = 0; i < 8; i++) {
                        queue.enqueue(("Item " + i));
                    }
                    //dequeue twice
                    queue.dequeue();
                    queue.dequeue();
                    assertEquals(6, queue.size());
                }
        );
    }


}
