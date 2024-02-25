import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Return the elements in a collection that are strictly greater than a specified
 * value.
 *
 */
public class GreaterThanSubset {

    // C O M P L E T E   T H I S   M E T H O D

    /**
     * Returns the elements in collection strictly greater than value.
     */
    public static <T extends Comparable<T>>
    Collection<T> greaterThanSubset(Collection<T> collection, T value) {
        ArrayList<T> result = new ArrayList<>();
        for (T i : collection){
            if( i.compareTo(value) > 0){
                result.add(i);
            }
        }
        return result;
    }
    public static void main (String[] args){
        ArrayList<Integer> test = new ArrayList<>(Arrays.asList(50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62));
        System.out.println(greaterThanSubset(test, 55));
    }

}

