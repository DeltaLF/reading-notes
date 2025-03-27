package com.leetcode.seventyFive.array_string;

public class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed.length == 1)
            return flowerbed[0] == 0 || n == 0;
        /*
         *
         * [x o x x x]
         * p c n
         */
        int availablePlots = 0;
        int prev = 0;
        int curr = flowerbed[0];
        int next = flowerbed[1];
        for (int i = 0; i < flowerbed.length - 2; i++) {
            if (prev == 0 && curr == 0 && next == 0) {
                availablePlots++;
                prev = 1;
            } else {
                prev = curr;
            }
            curr = flowerbed[i + 1];
            next = flowerbed[i + 2];
        }
        if ((prev == 0 && curr == 0 && next == 0) || (curr == 0 && next == 0)) {
            availablePlots++;
        }

        return availablePlots >= n;
    }
}
