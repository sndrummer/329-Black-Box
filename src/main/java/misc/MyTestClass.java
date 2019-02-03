package misc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author Samuel Nuttall
 */
public class MyTestClass {

    private static final Logger log = LoggerFactory.getLogger(MyTestClass.class);

    public static void main(String[] args) {

        WorkSchedule testSchedule = new WorkSchedule(4);
        testSchedule.setRequiredNumber(1, 1, 3);
        for (int i = 0; i < 4; i++) {
            WorkSchedule.Hour hour = testSchedule.readSchedule(i);
            log.debug("HOUR REQ {}", hour.requiredNumber);

        }




    /*    Object x = 3;
        Object[] arr = new Integer[]{1, 2, null, 4, 5};
        Object[] temp = arr.clone();

        Misc.f(x, arr);*/








        /*Queue queue = new Queue(10);
        // Add 5 elements to the queue
        for (int i = 0; i < 10; i++) {
            queue.enqueue(("Item " + (i + 1)));
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(queue.dequeue());
        }

        System.out.println("\n\n");

        Queue queue2 = new Queue(4);
        // Add 5 elements to the queue
        for (int i = 0; i < 5; i++) {
            String item = "Item " + (i + 1);
            System.out.println("Adding " + item);
            queue2.enqueue(item);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(queue2.dequeue());
        }*/

    }
}
