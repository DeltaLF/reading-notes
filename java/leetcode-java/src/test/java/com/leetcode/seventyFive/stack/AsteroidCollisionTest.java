package com.leetcode.seventyFive.stack;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

public class AsteroidCollisionTest {
    @Test
    public void testAsteroidCollision() {
        AsteroidCollision solution = new AsteroidCollision();
        // assertArrayEquals(new int[] { 1, 2 }, num);
        assertArrayEquals(new int[] { -10 }, solution.asteroidCollision(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, -10 }));
        assertArrayEquals(new int[] { 10 }, solution.asteroidCollision(new int[] { 10, -1, -2, -3, -4, -5 }));
        assertArrayEquals(new int[] {}, solution.asteroidCollision(new int[] { 5, 4, 3, -1, -3, -4, -5 }));
        assertArrayEquals(new int[] { -2, -2 }, solution.asteroidCollision(new int[] { 1, -1, -2, -2 }));
    }
}
