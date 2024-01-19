import org.junit.Test;

import static org.junit.Assert.*;

public class SelectorTest {

    @Test()
    public void min() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertEquals(1, Selector.min(a));
        // a test for an empty array which returns an exception
        int[] b = {};
        try {
            Selector.min(b);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void max() {
        int[] a = {1,2,3,4,5,6,7,8,9,10};
        assertEquals(10,Selector.max(a));
        int[] b = {};
        try {
            Selector.max(b);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    public void kmin() {
        int[] a = {-4,-4};
        assertEquals(-4, Selector.kmin(a,2));
        int[] b = {};
        try {
            Selector.kmin(b, 3);
            Selector.kmin(a, 8);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    public void kmax() {
        int[] a = {5,6,6,2,6,8,10,42,3,4};
        assertEquals(5, Selector.kmax(a, 5));
        int[] b = {1, 3, 5, 7, 9};
        assertEquals(9, Selector.kmax(b, 1));
        int[] c = {5, 7};
        assertEquals(7, Selector.kmax(c, 1));
    }
}