package misc;


/**
 * @author Samuel Nuttall
 */
public class MyTestClass {

    public static void main(String[] args) {

        Queue queue = new Queue(10);
        // Add 5 elements to the queue
        for (int i = 0; i < 11; i++) {
            queue.enqueue(("Item " + i));
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(queue.dequeue());
        }

    }
}
