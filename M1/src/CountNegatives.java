/**
 * Applies the linear scan strategy to counting the number of negative
 * values in an array.
 */
public class CountNegatives {

    /**
     * Returns the number of negative values in the given array.
     */
    public static int countNegatives(int[]a) {
        int neg = 0;
        for(int i = 0; i < a.length; i++){
            if(a[i]<0){
                neg++;
            }
        }
        return neg;
    }
    public static void main(String[] args){
        int[] a = {-1,-2,-3,-4,0,1,2,3,4,5,6,7,8,9,10};
        System.out.println(countNegatives(a));
    }
}
