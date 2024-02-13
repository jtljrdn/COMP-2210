/**
 * Count the number of even values in an array.
 *
 */
public class CountEvens {

    //  C O M P L E T E   T H I S    M E T H O D

    /**
     * Returns the number of even values in the paramter.
     */
    public static int countEvens(int[] values) {
       int count = 0;
        for (int i : values ) {
            if (i % 2 == 0){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args){
        int[] a = {2, 4, 6, 8, 10};
        System.out.println(countEvens(a));
    }

}

