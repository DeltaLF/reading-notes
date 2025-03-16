package com.leetcode.daily;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class HouseRobberIVTest {

    @Test
    public void testHouseRobberIV() {
        HouseRobberIV solution = new HouseRobberIV();
        assertEquals(2, solution.minCapability(new int[] { 2, 7, 9, 3, 1 }, 2));
        assertEquals(21,
                solution.minCapability(new int[] { 1, 23, 1, 3, 21, 2, 3, 1, 23, 1, 3123,
                        1236, 7, 123, 123, 213, 23, 1,
                        3, 21, 2, 3, 1, 23, 1, 3123, 1236, 7, 123, 123, 21, 23, 1, 3, 21, 2, 3, 1,
                        23, 1, 3123, 1236, 7,
                        123, 123, 21, 23, 1, 3, 21, 2, 3, 1, 23, 1, 3123, 1236, 7, 123, 123, 21, 23,
                        1, 3, 21, 2, 3, 1,
                        23, 1, 3123, 1236, 7, 123, 123, 21, 23, 1, 3, 21, 2, 3, 1, 23, 1, 3123, 1236,
                        7, 123, 123, 21,
                        23, 1, 3, 21, 2, 3, 1, 23, 1, 3123, 1236, 7, 123, 123, 21, 23, 1, 3, 21, 2,
                        3, 1, 23, 1, 3123,
                        1236, 7, 123, 123, 21, 23, 1, 3, 21, 2, 3, 1, 23, 1, 3123, 1236, 7, 123, 123,
                        21 }, 50));
    }
}
