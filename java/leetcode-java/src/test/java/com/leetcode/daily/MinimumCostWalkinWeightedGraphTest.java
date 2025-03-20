package com.leetcode.daily;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

public class MinimumCostWalkinWeightedGraphTest {

        @Test
        public void testMinimumCostWalkinWeightedGraph() {
                MinimumCostWalkinWeightedGraph solution = new MinimumCostWalkinWeightedGraph();
                int arg1 = 10;
                int[][] arg2 = {
                                { 0, 1, 7 },
                                { 1, 3, 7 },
                                { 1, 2, 1 }
                };
                int[][] arg3 = {
                                { 0, 3 },
                                { 3, 4 }
                };
                int[] ans = { 1, -1 };
                assertArrayEquals(ans, solution.minimumCost(arg1, arg2, arg3));

                arg1 = 10;
                arg2 = new int[][] {};
                arg3 = new int[][] { { 0, 1 } };
                ans = new int[] { -1 };

        }
}
