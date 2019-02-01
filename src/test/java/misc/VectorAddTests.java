package misc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Samuel Nuttall
 * <p>
 * VectorAdd Tests
 */
public class VectorAddTests {

    private static final Logger log = LoggerFactory.getLogger(VectorAddTests.class);


    /**
     * Tests that vectorAdd works according to the spec
     * Tests both vectors of equal size and where length of b is > length of a
     */
    @Test
    @DisplayName("Vector Add Equal Lengths Test")
    public void testVectorAddEqualLengths() {

        // Test 1, length of 'a' equals length of 'b'
        Float[] a = new Float[]{1f, 2f, 3f, 4f};
        Float[] b = new Float[]{1f, 2f, 3f, 4f};
        Misc.vectorAdd(a, b);
        assertArrayEquals(new Float[]{2f, 4f, 6f, 8f}, a);

    }

    /**
     * Tests when input vectors a and b are unequal
     * 1. b > a
     * 2. a > b
     *
     * According to the spec and the definition of vector addition, using different lengths if vectors
     * should throw a runtime exception, if it does not that could be a problem
     *
     * Test Fails because Test1 does not throw an exception, this means that vectorAdd does allow vectors
     * of different lengths as long as b > a, which is not what is expected given how the mathematical
     * definition of vector addition
     */
    @Test
    @DisplayName("Vector Add Different Lengths Test")
    public void testVectorAddDifferentLengths() {
        assertAll(
                () -> {
                    // Test 1, length of b > length of a
                    Float[] a = new Float[]{1f, 2f, 3f, 4f};
                    Float[] b = new Float[]{1f, 2f, 3f, 4f, 5f};
                    assertThrows(RuntimeException.class, ()-> {
                        Misc.vectorAdd(a, b);
                    });
                },
                () -> {
                    // Test 2, length of a > length of b
                    Float[] a = new Float[]{1f, 2f, 3f, 4f, 5f};
                    Float[] b = new Float[]{1f, 2f, 3f, 4f};

                    assertThrows(RuntimeException.class, ()-> {
                        Misc.vectorAdd(a, b);
                    });
                });
    }


    /**
     * Tests the set of invalid null input
     * 1. a = null
     * 2. b = null
     * 3. a and b = null
     */
    @Test
    @DisplayName("Vector Add With Null Input Test")
    public void testNullVectorAdd() {
        assertAll(
                () -> {
                    // Test 1
                    Float[] v1 = null;
                    Float[] v2 = new Float[]{1f, 2f, 3f, 4f};
                    assertThrows(RuntimeException.class, () -> {
                        Misc.vectorAdd(v1, v2);
                    });

                },
                () -> {
                    // Test 2
                    Float[] v1 = new Float[]{1f, 2f, 3f, 4f};
                    Float[] v2 = null;
                    assertThrows(RuntimeException.class, () -> {
                        Misc.vectorAdd(v1, v2);
                    });
                },
                () -> {
                    // Test 3
                    Float[] v1 = null;
                    Float[] v2 = null;
                    assertThrows(RuntimeException.class, () -> {
                        Misc.vectorAdd(v1, v2);
                    });
                }
        );
    }
}
