import java.util.Arrays;

/**
 * Defines a library of selection methods
 * on arrays of ints.
 *
 * @author   Jordan Lee (jtl0071@auburn.edu)
 * @author   Dean Hendrix (dh@auburn.edu)
 * @version  1.18.2023
 *
 */
public final class Selector {

    /**
     * Can't instantiate this class.
     *
     * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
     *
     */
    private Selector() { }


    /**
     * Selects the minimum value from the array a. This method
     * throws IllegalArgumentException if a is null or has zero
     * length. The array a is not changed by this method.
     */
    public static int min(int[] a) {
        if (a == null || a.length == 0){
            throw new IllegalArgumentException();
        }
        int min = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i] <= min){
                min = a[i];
            }
        }
        return min;
    }


    /**
     * Selects the maximum value from the array a. This method
     * throws IllegalArgumentException if a is null or has zero
     * length. The array a is not changed by this method.
     */
    public static int max(int[] a) {
        if (a == null || a.length == 0){
            throw new IllegalArgumentException();
        }
        int max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i] >= max){
                max = a[i];
            }
        }
        return max;
    }


    /**
     * Selects the kth minimum value from the array a. This method
     * throws IllegalArgumentException if a is null, has zero length,
     * or if there is no kth minimum value. Note that there is no kth
     * minimum value if k < 1, k > a.length, or if k is larger than
     * the number of distinct values in the array. The array a is not
     * changed by this method.
     */
    public static int kmin(int[] a, int k) {

        // Errors
        if (a == null || a.length == 0 || k < 1 || k > a.length){
            throw new IllegalArgumentException();
        }
        if (k == 1){
            return min(a);
        }

        int[] temp = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            temp[i] = a[i];
        }
        Arrays.sort(temp);
        // if there is no kth minimum value
        if (temp[0] == temp[temp.length-1]){
            throw new IllegalArgumentException();
        }
        if (k == a.length){
            return max(a);
        }
        // Find kth min
        int count = 1;
        int kth = temp[0];
        for (int i = 1; i < temp.length; i++) {
            if (temp[i] != temp[i-1]){
                count++;
            }
            if (count == k){
                kth = temp[i];
            }
        }
        return kth;
    }


    /**
     * Selects the kth maximum value from the array a. This method
     * throws IllegalArgumentException if a is null, has zero length,
     * or if there is no kth maximum value. Note that there is no kth
     * maximum value if k < 1, k > a.length, or if k is larger than
     * the number of distinct values in the array. The array a is not
     * changed by this method.
     */
    public static int kmax(int[] a, int k) {

        // Errors
        if (a == null || a.length == 0 || k < 1 || k > a.length){
            throw new IllegalArgumentException();
        }
        if (k == 1){
            return max(a);
        }
        // Copy array
        int[] temp = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            temp[i] = a[i];
        }
        Arrays.sort(temp);
        // if there is no kth minimum value
        if (temp[0] == temp[temp.length-1]){
            throw new IllegalArgumentException();
        }
        if (k == a.length){
            return min(a);
        }
        // Find kth max
        int count = 1;
        int kth = temp[0];
        for (int i = temp.length - 2; i >= 0; i--) {
            if (temp[i] != temp[i+1]){
                count++;
            }
            if (count == k){
                kth = temp[i];
            }
        }
        return kth;
    }


    /**
     * Returns an array containing all the values in a in the
     * range [low..high]; that is, all the values that are greater
     * than or equal to low and less than or equal to high,
     * including duplicate values. The length of the returned array
     * is the same as the number of values in the range [low..high].
     * If there are no qualifying values, this method returns a
     * zero-length array. Note that low and high do not have
     * to be actual values in a. This method throws an
     * IllegalArgumentException if a is null or has zero length.
     * The array a is not changed by this method.
     */
    public static int[] range(int[] a, int low, int high) {
        if (a == null || a.length == 0){
            throw new IllegalArgumentException();
        }
        int[] temp = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            temp[i] = a[i];
        }
        Arrays.sort(temp);
        int count = 0;

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] >= low && temp[i] <= high){
                count++;
            }
        }
        int[] range = new int[count];
        count = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] >= low && temp[i] <= high){
                range[count++] = temp[i];
            }
        }
        return range;
    }


    /**
     * Returns the smallest value in a that is greater than or equal to
     * the given key. This method throws an IllegalArgumentException if
     * a is null or has zero length, or if there is no qualifying
     * value. Note that key does not have to be an actual value in a.
     * The array a is not changed by this method.
     */
    public static int ceiling(int[] a, int key) {
        return -99;
    }


    /**
     * Returns the largest value in a that is less than or equal to
     * the given key. This method throws an IllegalArgumentException if
     * a is null or has zero length, or if there is no qualifying
     * value. Note that key does not have to be an actual value in a.
     * The array a is not changed by this method.
     */
    public static int floor(int[] a, int key) {
        return -99;
    }

}
