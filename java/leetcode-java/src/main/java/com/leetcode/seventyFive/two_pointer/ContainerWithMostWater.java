package com.leetcode.seventyFive.two_pointer;

public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        /*
         * 5 8 6
         * l r
         *
         * 1,5,8,6,2,5,4,8,3,5,4
         * l r
         * max
         * for brute force
         * i j
         * i j
         * i j
         * ...
         * i j
         * i j...
         * O(n^2)
         *
         * is it possible O(n)?
         */
        int n = height.length;
        int l = 0;
        int r = n - 1;
        int max = this.getMaxAmount(l, r, height);
        while (l < r) {
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
            max = Math.max(max, this.getMaxAmount(l, r, height));
        }

        return max;
    }

    private int getMaxAmount(int l, int r, int[] height) {
        return Math.abs(r - l) * Math.min(height[l], height[r]);
    }
}
