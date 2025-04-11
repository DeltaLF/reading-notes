package com.leetcode.seventyFive.prefix_sum;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class FindPivotIndexTest {

    @Test
    public void testFindPivotIndex() {
        FindPivotIndex solution = new FindPivotIndex();
        // assertArrayEquals(new int[] { 1, 2 }, num);
        assertEquals(0, solution.pivotIndex(new int[] { 1 }));
        assertEquals(4, solution.pivotIndex(new int[] { -1, -1, 1, 1, 0, 0 }));
    }
}
