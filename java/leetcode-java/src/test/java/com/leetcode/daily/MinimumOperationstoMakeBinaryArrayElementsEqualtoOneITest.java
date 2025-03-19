package com.leetcode.daily;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MinimumOperationstoMakeBinaryArrayElementsEqualtoOneITest {

    @Test
    public void testMinimumOperationstoMakeBinaryArrayElementsEqualtoOneITest() {
        MinimumOperationstoMakeBinaryArrayElementsEqualtoOneI solution = new MinimumOperationstoMakeBinaryArrayElementsEqualtoOneI();

        assertEquals(3, solution.minOperations(new int[] { 0, 1, 1, 1, 0, 0 }));
        assertEquals(-1, solution.minOperations(new int[] { 1, 0, 0 }));

    }

}
