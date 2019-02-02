package misc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Samuel Nuttall
 * <p>
 * Tests for the Misc.f() method
 */
public class FTests {

    private static final Logger log = LoggerFactory.getLogger(FTests.class);

    /**
     * Valid input for method Misc.f(), assumes x and arr must be of the same type given we are using
     * arrays
     */
    @Test
    @DisplayName("Test Valid Input and Add to Array Functionality")
    public void testFValidInput() {
        String x1 = "Colby Jack";
        String x2 = "Pepper Jack";
        Object[] arr = new String[]{null, "cheddar", "parmesan", null};

        assertAll(
                () -> {
                    Misc.f(x1, arr);
                    assertEquals("Colby Jack", arr[0]);
                    assertSame(x1, arr[0]);
                },
                () -> {
                    Misc.f(x2, arr);
                    assertEquals("Pepper Jack", arr[3]);
                    assertSame(x2, arr[3]);
                });
    }

    /**
     * Tests that nothing should happen to arr when x is equal to an element in the array
     */
    @Test
    @DisplayName("Test x Equal to arr element")
    public void testEqualInput() {
        String x1 = "cheddar";
        String x2 = "parmesan";
        Object[] arr = new String[]{null, "cheddar", "parmesan", null};


        assertAll(
                () -> {
                    Misc.f(x1, arr);
                    assertNull(arr[0]);
                    assertEquals("cheddar", arr[1]);
                    assertEquals("parmesan", arr[2]);
                    assertNull(arr[3]);


                },
                () -> {
                    Misc.f(x2, arr);
                    assertNull(arr[0]);
                    assertEquals("cheddar", arr[1]);
                    assertEquals("parmesan", arr[2]);
                    assertNull(arr[3]);
                });
    }



    /**
     * Test Invalid input
     * 1. Where x is null
     * 2. Where arr is null
     * 3. Where arr has no null elements and x is not equal to an element in arr
     */
    @Test
    @DisplayName("Test Invalid Input")
    public void testFInvalidInput() {
        assertAll(
                () -> {
                    // Test 1
                    Object x = null;
                    Object[] arr = new Integer[]{null, 2, 3, 4, null};
                    assertThrows(RuntimeException.class, () -> {
                        Misc.f(x, arr);
                    });

                },
                () -> {
                    // Test 2
                    Object x = 7;
                    Object[] arr = null;
                    assertThrows(RuntimeException.class, () -> {
                        Misc.f(x, arr);
                    });

                },
                () -> {
                    // Test 3
                    Object x = 7;
                    Object[] arr = new Integer[]{1, 2, 3, 4, 5};
                    assertThrows(RuntimeException.class, () -> {
                        Misc.f(x, arr);
                    });
                }
        );
    }

    /**
     * Test the case that is technically allowed by the revised spec
     * where x is equal to an element in arr and arr has no null elements
     */
    @Test
    @DisplayName("Test x equal with no null elements")
    public void testEdgeCase() {
        Object x = 3;
        Object[] arr = new Integer[]{1, 2, 3, 4, 5};
        Object[] temp = arr.clone();
        assertDoesNotThrow(()->{
            Misc.f(x, arr);
        });
    }

}
