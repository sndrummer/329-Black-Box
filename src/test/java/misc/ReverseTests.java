package misc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Samuel Nuttall
 * <p>
 * Reverse Tests
 */
public class ReverseTests {
    private static final Logger log = LoggerFactory.getLogger(ReverseTests.class);


    /**
     * Tests the different possible input types for the Misc.reverse() method
     * Test 1: calls reverse() with a String array and tests that it works correctly
     * Test 2: calls reverse() with an Integer array and tests that the output is as expected
     * Test 3: calls reverse() with null input and tests that it returns an empty array
     * Test 4: calls reverse() with an empty array as input and tests that it returns an empty array
     */
    @Test
    @DisplayName("Test different array object types")
    public void testReverse() {

        assertAll(
                () -> {
                    // Test 1
                    String[] strArr = new String[]{"1", "2", "three", "four"};
                    Object[] reversedStrArr = Misc.reverse(strArr);
                    assertArrayEquals(new String[]{"four", "three", "2", "1"}, reversedStrArr);

                },
                () -> {
                    // Test 2
                    Integer[] integerArr = new Integer[]{1, 2, 3, 4};
                    Object[] reversedIntArr = Misc.reverse(integerArr);
                    assertArrayEquals(new Integer[]{4, 3, 2, 1}, reversedIntArr);
                },
                () -> {
                    // Test 3
                    Integer[] integerArr = null;
                    Object[] reversedIntArr = Misc.reverse(integerArr);
                    assertArrayEquals(new Integer[]{}, reversedIntArr);
                },
                () -> {
                    // Test 4
                    Integer[] integerArr = {};
                    Object[] reversedIntArr = Misc.reverse(integerArr);
                    assertArrayEquals(new Integer[]{}, reversedIntArr);
                }
        );
    }

}
