package com.leetcode.seventyFive.array_string;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class GreatestCommonDivisorofStringsTest {

    @Test
    public void testGreatestCommonDivisorofStrings() {
        GreatestCommonDivisorofStrings solution = new GreatestCommonDivisorofStrings();
        assertEquals("A", solution.gcdOfStrings("AAAAAAAAAAAAAAAAA", "A"));
        assertEquals("", solution.gcdOfStrings("ABABABABABAB", "ABABABABABABC"));
    }
}
