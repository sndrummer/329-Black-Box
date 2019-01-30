package misc;

public class Misc {

	private Misc() {
		// Do nothing
	}

	private static final Object[] EMPTY_ARRAY = {};
	
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

	public static void vectorAdd(Float[] a, Float[] b) {
		for (int i = 0; i < a.length; i++) {
			a[i] += b[i];
		}
	}

	/**
	 * Adds an element to an array if it is not already in the array.
	 * 
	 * @cs329.requires x and arr are non-null and there is an element in arr that is null
	 * @cs329.ensures when f returns to the caller there is an element in arr that is equal to x
	 * 
	 * @param x element to add
	 * @param arr array to which x is added
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
			for (i = 0;; i++) { // D1
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
