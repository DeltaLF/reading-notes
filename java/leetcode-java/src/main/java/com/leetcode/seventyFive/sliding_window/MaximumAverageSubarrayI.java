package com.leetcode.seventyFive.sliding_window;

public class MaximumAverageSubarrayI {
    public double findMaxAverage(int[] nums, int k) {
        /*
         * [1,12,-5,-6,50,3], k = 4
         * l ,r
         */
        double sum = 0;
        int l = 0;
        int r = k;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        double max = sum / k;
        while (r < nums.length) {
            sum += nums[r];
            sum -= nums[l];
            r++;
            l++;
            max = Math.max((double) sum / k, max);
        }
        return max;
    }
}
