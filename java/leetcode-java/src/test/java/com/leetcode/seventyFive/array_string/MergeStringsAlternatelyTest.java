package com.leetcode.seventyFive.array_string;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MergeStringsAlternatelyTest {
    @Test
    public void testMergeStringsAlternately() {
        MergeStringsAlternately solution = new MergeStringsAlternately();
        assertEquals("abbbbb", solution.mergeAlternately("a", "bbbbb"));
        assertEquals("abaaaa", solution.mergeAlternately("aaaaa", "b"));
    }
}
