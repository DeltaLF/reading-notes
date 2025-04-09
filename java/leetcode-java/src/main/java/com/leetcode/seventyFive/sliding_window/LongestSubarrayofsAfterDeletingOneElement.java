package com.leetcode.seventyFive.sliding_window;

public class LongestSubarrayofsAfterDeletingOneElement {
    public int longestSubarray(int[] nums) {
        /*
         * 0,1,1,1,0,1,1,0,1
         *
         */
        int l = 0;
        int lNext = -1;
        int r = 0;
        int max = 0;
        while (r < nums.length) {
            if (nums[r] == 0) {
                if (lNext >= 0) {
                    l = lNext + 1;
                }
                lNext = r;
            }
            r++;
            if (lNext >= 0) {
                max = Math.max(r - l - 1, max);
            } else {
                max = Math.max(r - l, max);
            }
        }
        if (lNext < 0) {
            max--;
        }
        return max;
    }

}
