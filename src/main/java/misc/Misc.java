package misc;

public class Misc {

    private Misc() {
        // Do nothing
    }

    private static final Object[] EMPTY_ARRAY = {};


    /**
     * Creates a new array with the reverse order of the input array
     *
     * @param a array to reverse
     * @return new array where the order of its elements is the reverse of array a.
     * <p>
     * a is null or empty then an empty array is returned
     * @cs329.requires none
     * @cs329.ensures that a new array is returned with the elements of a but in reverse order, if
     */
    public static Object[] reverse(Object[] a) {
        if (a == null) {
            return EMPTY_ARRAY.clone();
        }

        Object[] b = new Object[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[a.length - 1 - i];
        }

        return b;
    }

    /**
     * Computes the vector addition of parameters a and b
     *
     * @param a float array that represents a vector, also represents the result of the addition when returning
     * @param b float array that represents a vector, will be added to vector a
     * @cs329.requires that a and b are non-null, and that length of a is equal to the length of b
     * (according to the definition of vector addition given by linear algebra).
     * @cs329.ensures that input vector 'a' will be the result of a + b vector addition.
     */
    public static void vectorAdd(Float[] a, Float[] b) {
        for (int i = 0; i < a.length; i++) {
            a[i] += b[i];
        }
    }

    /**
     * Adds an element to an array if it is not already in the array.
     *
     * @param x   element to add
     * @param arr array to which x is added
     * @cs329.requires x and arr are non-null and there is an element in arr that is null
     * @cs329.ensures when f returns to the caller there is an element in arr that is equal to x
     */
    public static void f(Object x, Object[] arr) {
        int i;
        boolean exists = false;

        for (i = 0; i < arr.length; i++) { // A1
            if (x.equals(arr[i])) { // B1
                exists = true;
                break;
            } // B2
        } // A2
        if (!exists) // C1
        {
            for (i = 0; ; i++) { // D1
                if (arr[i] == null) { // E1
                    arr[i] = x;
                    break;
                } // E2
            } // D2
        } // C2
    }

    public static void dnfsort(int[] arr) {
        int a = 0;
        int b = 0;
        int c = arr.length;

        while (b < c) {
            if (arr[b] == 1) {
                arr[b] = arr[a];
                arr[a] = 1;
                a = a + 1;
                b = b + 1;
            } else {
                if (arr[b] == 2) {
                    b = b + 1;
                } else // arr[b] should be 3 here
                {
                    c = c - 1;
                    arr[b] = arr[c];
                    arr[c] = 3;
                }
            }
        }
    }
}
