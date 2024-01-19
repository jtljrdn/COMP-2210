import org.junit.Test;

import java.util.Arrays;

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

    @Test
    public void range() {
        // Write tests for the range method
        int[] a = {1,2,3,4,5,6,7,8,9,10};
        int[] resultA = {2,3,4,5,6};
        assertArrayEquals(resultA, Selector.range(a,2,6));
        System.out.println("Test 1 passed");
        int[] b = {3,6,2,7,1,3,8};
        int[] resultB = {2,3,3,6};
        assertArrayEquals(resultB, Selector.range(b,2,6));
        System.out.println("Test 2 passed");
        int[] c = {1,2,3,4,5,6,7,8,9,10};
        int[] resultC = {2,3,4,5,6};
        assertArrayEquals(resultC, Selector.range(c,2,6));
        System.out.println("Test 3 passed");

    }
}