package com.leetcode.daily;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CounttheNumberofCompleteComponentsTest {
    @Test
    public void testCounttheNumberofCompleteComponents() {
        CounttheNumberofCompleteComponents solution = new CounttheNumberofCompleteComponents();
        assertEquals(9, solution.countCompleteComponents(10, new int[][] { { 0, 1 } }));
        assertEquals(0, solution.countCompleteComponents(5, new int[][] { { 2, 0 }, { 3, 2 }, { 4, 1 }, { 4, 2 } }));
    }
}
