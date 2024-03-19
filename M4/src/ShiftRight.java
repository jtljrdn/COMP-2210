import java.util.Arrays;

/**
 * Implements shift-right behavior in an array.
 *
 */

public class ShiftRight {


    /**
     * Shifts the elements at a[index] through a[a.length - 2] one
     * position to the right.
     */
    public static void shiftRight(Object[] array, int index) {
        for (int i = array.length - 1; i > index; i--) {
            array[i] = array[i - 1];
            array[i - 1] = null;
        }
    }

    public static void main(String[] args){
        Object[] a = {10, 11, 16, 16, 33, 39, 40, 49, 51, 57, null, null, null, null, null};
        shiftRight(a, 10);
        System.out.println(Arrays.toString(a));
    }

}

