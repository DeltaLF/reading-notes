package com.leetcode.daily;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class LongestNiceSubarrayTest {

    @Test
    public void testLongestNiceSubarray() {
        LongestNiceSubarray solution = new LongestNiceSubarray();
        assertEquals(1, solution.longestNiceSubarray(new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 11, 1 }));
        assertEquals(10, solution.longestNiceSubarray(
                new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 8, 48, 1, 2, 4, 8, 16, 32, 64, 128, 256, 512 }));
        assertEquals(8,
                solution.longestNiceSubarray(new int[] { 84139415, 693324769, 614626365, 497710833, 615598711, 264,
                        65552, 50331652, 1, 1048576, 16384, 544, 270532608, 151813349, 221976871, 678178917, 845710321,
                        751376227, 331656525, 739558112, 267703680 }));
    }
}