import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author Jordan Lee (jtl0071@auburn.edu)
 */
public final class Selector {

    /**
     * Can't instantiate this class.
     * <p>
     * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
     */
    private Selector() {
    }


    /**
     * Returns the minimum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll the Collection from which the minimum is selected
     * @param comp the Comparator that defines the total order on T
     * @return the minimum value in coll
     * @throws IllegalArgumentException as per above
     * @throws NoSuchElementException   as per above
     */
    public static <T> T min(Collection<T> coll, Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw new IllegalArgumentException("coll or comp are null");
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException("coll is empty");
        }
        T minValue = null;
        for (T element : coll) {
            if (minValue == null || comp.compare(element, minValue) < 0) {
                minValue = element;
            }
        }
        return minValue;
    }


    /**
     * Selects the maximum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll the Collection from which the maximum is selected
     * @param comp the Comparator that defines the total order on T
     * @return the maximum value in coll
     * @throws IllegalArgumentException as per above
     * @throws NoSuchElementException   as per above
     */
    public static <T> T max(Collection<T> coll, Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw new IllegalArgumentException("coll or comp are null");
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException("coll is empty");
        }
        T maxValue = null;
        for (T element : coll) {
            if (maxValue == null || comp.compare(element, maxValue) > 0) {
                maxValue = element;
            }
        }
        return maxValue;
    }


    /**
     * Selects the kth minimum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth minimum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * @param coll the Collection from which the kth minimum is selected
     * @param k    the k-selection value
     * @param comp the Comparator that defines the total order on T
     * @return the kth minimum value in coll
     * @throws IllegalArgumentException as per above
     * @throws NoSuchElementException   as per above
     */
    public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException();
        }
        // Child interface of collection. It is an ordered collection of objects
        // List = [a,b,c]
        // T is generic type, is not integer
        // Copy into collCopy and do operation on collCopy
        List<T> coll1 = new ArrayList<>(coll);
        // Sort collCopy in ascending order
        coll1.sort(comp);
        // Find kth min value
        // Retrieve element from collection by using iterator
        // Get iterator for the element
        Iterator<T> itr = coll1.iterator();
        if (k == 1) {
            // Use next method on the iterator interface to retrieve element
            // next will return the next element in the collection
            // The first time we use next function it will return the first element in the collection
            // Below will help us return the first element in the collection
            return itr.next();
        }
        // Count is the number of different elements in the collection
        int count = 0;
        // If the first time execute this next, what is currently
        T current = itr.next();
        while (itr.hasNext()) {
            T next = itr.next();
            if (comp.compare(current, next) != 0) {
                count++;
            }
            if (count == k - 1) {
                return next;
            }
            current = next;

        }
        throw new NoSuchElementException();
    }


    /**
     * Selects the kth maximum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth maximum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * @param coll the Collection from which the kth maximum is selected
     * @param k    the k-selection value
     * @param comp the Comparator that defines the total order on T
     * @return the kth maximum value in coll
     * @throws IllegalArgumentException as per above
     * @throws NoSuchElementException   as per above
     */
    public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException();
        }
        // Child interface of collection. It is an ordered collection of objects
        // List = [a,b,c]
        // T is generic type, is not integer
        // Copy into collCopy and do operation on collCopy
        List<T> coll1 = new ArrayList<>(coll);
        // Sort collCopy in ascending order
        coll1.sort(comp.reversed());
        // Find kth min value
        // Retrieve element from collection by using iterator
        // Get iterator for the element
        Iterator<T> itr = coll1.iterator();
        if (k == 1) {
            // Use next method on the iterator interface to retrieve element
            // next will return the next element in the collection
            // The first time we use next function it will return the first element in the collection
            // Below will help us return the first element in the collection
            return itr.next();
        }
        // Count is the number of different elements in the collection
        int count = 0;
        // If the first time execute this next, what is currently
        T current = itr.next();
        while (itr.hasNext()) {
            T next = itr.next();
            if (comp.compare(current, next) != 0) {
                count++;
            }
            if (count == k - 1) {
                return next;
            }
            current = next;

        }
        throw new NoSuchElementException();
    }


    /**
     * Returns a new Collection containing all the values in the Collection coll
     * that are greater than or equal to low and less than or equal to high, as
     * defined by the Comparator comp. The returned collection must contain only
     * these values and no others. The values low and high themselves do not have
     * to be in coll. Any duplicate values that are in coll must also be in the
     * returned Collection. If no values in coll fall into the specified range or
     * if coll is empty, this method throws a NoSuchElementException. If either
     * coll or comp is null, this method throws an IllegalArgumentException. This
     * method will not change coll in any way.
     *
     * @param coll the Collection from which the range values are selected
     * @param low  the lower bound of the range
     * @param high the upper bound of the range
     * @param comp the Comparator that defines the total order on T
     * @return a Collection of values between low and high
     * @throws IllegalArgumentException as per above
     * @throws NoSuchElementException   as per above
     */
    public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                          Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException("coll is empty");
        }
        List<T> newColl = new ArrayList<>();
        for (T i : coll) {
            if ((comp.compare(i, low) >= 0) && (comp.compare(i, high) <= 0)) {
                newColl.add(i);
            }
        }
        if (newColl.isEmpty()) {
            throw new NoSuchElementException("newColl is empty");
        }
        return newColl;
    }


    /**
     * Returns the smallest value in the Collection coll that is greater than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll the Collection from which the ceiling value is selected
     * @param key  the reference value
     * @param comp the Comparator that defines the total order on T
     * @return the ceiling value of key in coll
     * @throws IllegalArgumentException as per above
     * @throws NoSuchElementException   as per above
     */
    public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException("coll is empty");
        }
        List<T> newColl = new ArrayList<>(coll);
        T currCeil = null;
        for (T i : newColl) {
            if (comp.compare(i, key) >= 0) {
                if (currCeil == null || comp.compare(i, currCeil) < 0) {
                    currCeil = i;
                }
            }
        }
        if (currCeil == null) {
            throw new NoSuchElementException("no ceiling");
        }
        return currCeil;
    }


    /**
     * Returns the largest value in the Collection coll that is less than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll the Collection from which the floor value is selected
     * @param key  the reference value
     * @param comp the Comparator that defines the total order on T
     * @return the floor value of key in coll
     * @throws IllegalArgumentException as per above
     * @throws NoSuchElementException   as per above
     */
    public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException("coll is empty");
        }
        List<T> newColl = new ArrayList<>();
        T currFloor = null;
        for (T i : coll) {
            if (comp.compare(i, key) <= 0) {
                if (currFloor == null || comp.compare(i, currFloor) > 0) {
                    currFloor = i;
                }
            }
        }
        if (currFloor == null) {
            throw new NoSuchElementException("no ceiling");
        }
        return currFloor;
    }

}
