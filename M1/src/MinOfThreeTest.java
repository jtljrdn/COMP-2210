import static org.junit.Assert.*;

public class MinOfThreeTest {

    @org.junit.Test
    public void min1() {
        assertEquals(1, MinOfThree.min1(1,2,3));
        assertEquals(1, MinOfThree.min1(2,1,3));
        assertEquals(1, MinOfThree.min1(3,2,1));
        assertEquals(1, MinOfThree.min1(1,1,1));
        assertEquals(1, MinOfThree.min1(1,1,2));
        assertEquals(1, MinOfThree.min1(1,2,1));
        assertEquals(1, MinOfThree.min1(2,1,1));
        assertEquals(1, MinOfThree.min1(2,2,1));
        assertEquals(1, MinOfThree.min1(2,1,2));
        assertEquals(1, MinOfThree.min1(1,2,2));
    }

    @org.junit.Test
    public void min2() {
        assertEquals(1, MinOfThree.min2(1,2,3));
        assertEquals(1, MinOfThree.min2(2,1,3));
        assertEquals(1, MinOfThree.min2(1,1,1));
        assertEquals(1, MinOfThree.min2(1,1,2));
        assertEquals(1, MinOfThree.min2(1,2,1));
        assertEquals(1, MinOfThree.min2(2,1,1));
        assertEquals(1, MinOfThree.min2(3,2,1));
        assertEquals(1, MinOfThree.min2(2,2,1));
        assertEquals(1, MinOfThree.min2(2,1,2));
        assertEquals(1, MinOfThree.min2(1,2,2));
    }
}