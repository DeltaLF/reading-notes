package com.leetcode.seventyFive.array_string;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CanPlaceFlowersTest {
    @Test
    public void testCanPlaceFlowers() {
        CanPlaceFlowers solution = new CanPlaceFlowers();
        assertEquals(true, solution.canPlaceFlowers(new int[] { 1 }, 0));
        assertEquals(true, solution.canPlaceFlowers(new int[] { 1 }, 0));
        assertEquals(true, solution.canPlaceFlowers(new int[] { 0, 0 }, 1));
        assertEquals(false, solution.canPlaceFlowers(new int[] { 0, 0 }, 2));
        assertEquals(false, solution.canPlaceFlowers(new int[] { 0, 1, 0 }, 1));
    }
}
