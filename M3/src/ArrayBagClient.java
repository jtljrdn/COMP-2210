/**
 * ArrayBagClient.java
 * Provides a sample client of Bag behavior from
 * the ArrayBag class.
 *
 */
public class ArrayBagClient {
    /** Drives execution. */
    public static void main(String[] args) {
        Bag<Integer> bag = new ArrayBag<>();
        for (int i = 1; i <= 18; i++) {
            bag.add(i);
        }
        for (int i = 1; i <= 15; i++) {
            bag.remove(i);
        }
        System.out.println(bag);
    }
}