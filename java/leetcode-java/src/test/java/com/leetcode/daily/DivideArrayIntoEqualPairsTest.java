package com.leetcode.daily;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DivideArrayIntoEqualPairsTest {
    @Test
    public void testDivideArrayIntoEqualPairs() {
        DivideArrayIntoEqualPairs solution = new DivideArrayIntoEqualPairs();
        assertEquals(true, solution.divideArray(new int[] { 3, 2, 3, 2, 2, 2 }));
        assertEquals(false, solution.divideArray(new int[] { 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3,
                2, 3, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3 }));
    }
}
