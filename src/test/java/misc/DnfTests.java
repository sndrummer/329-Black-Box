package misc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Samuel Nuttall
 * <p>
 * Tests for the Misc.dnfsort() method
 */
public class DnfTests {

    private static final Logger log = LoggerFactory.getLogger(QueueTests.class);

    /**
     * Tests the functionality of the dnfsort algorithm with valid input
     */
    @Test
    @DisplayName("Valid DnfSort Input Test")
    public void testDnfsortValid() {
        int[] arr = new int[]{3, 2, 1, 2, 1, 3, 2, 1, 3}; // sorted should be 1, 1, 1, 2, 2, 2, 3, 3, 3
        int[] expected = new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3};
        Misc.dnfsort(arr);

        for (int i = 0; i < arr.length; i++) {
            assertEquals(expected[i], arr[i]);
        }
    }


    /**
     * Tests invalid input in the dnfSort method. Uses numbers outside of the set {1,2,3}
     * in the input array
     * <p>
     * Test fails because no error is thrown when using input outside of the range
     * The expected would be to not allow input outside of the range and an error to be thrown
     */
    @Test
    @DisplayName("Invalid Input Dnfsort Test")
    public void testInvalidInput() {
        int[] arr = new int[]{0, 1, 2, 3, 4};
        assertThrows(RuntimeException.class, () -> {
            Misc.dnfsort(arr);
        });

    }

    /**
     * Tests dnfsort with an empty array, should not throw an error since the empty set should
     * covered
     */
    @Test
    @DisplayName("Empty Array Dnfsort Test")
    public void testDnfsortEmpty() {
        int[] arr = new int[]{}; // sorted should be 1, 1, 1, 2, 2, 2, 3, 3, 3
        assertDoesNotThrow(() -> {
            Misc.dnfsort(arr);
        });
    }

    /**
     * Tests input of all one number from the valid set
     */
    @Test
    @DisplayName("One Number DnfSort Input Test")
    public void testOneNumberDnfsort() {
        int[] arr = new int[]{1, 1, 1, 1, 1}; // sorted should be 1, 1, 1, 2, 2, 2, 3, 3, 3
        assertDoesNotThrow(()-> {
            Misc.dnfsort(arr);
        });
    }

}
