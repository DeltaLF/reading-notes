package com.leetcode.seventyFive.two_pointer;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

public class MoveZeroesTest {
    @Test
    public void testMoveZeroesTest() {
        MoveZeroes solution = new MoveZeroes();
        int[] num = new int[] { 1 };
        solution.moveZeroes(num);
        assertArrayEquals(new int[] { 1 }, num);

        num = new int[] { 1, 2 };
        solution.moveZeroes(num);
        assertArrayEquals(new int[] { 1, 2 }, num);

        num = new int[] { 0, 1, 2 };
        solution.moveZeroes(num);
        assertArrayEquals(new int[] { 1, 2, 0 }, num);

        num = new int[] { 0, 0, 0, 1, 2, 3 };
        solution.moveZeroes(num);
        assertArrayEquals(new int[] { 1, 2, 3, 0, 0, 0 }, num);
    }
}
