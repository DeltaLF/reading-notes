package com.leetcode.daily;

public class LongestNiceSubarray {

    public int longestNiceSubarray(int[] nums) {
        /*
         * 1 <= nums[i] <= 10^9
         * 1111111111111111111111111111111
         *
         * 3: 000000000000000000000011
         * 8: 000000000000000000000100
         *
         */
        Integer l = 0;
        Integer r = 0;
        Integer maxLen = 0;
        while (r < nums.length) {
            /*
             * l=0 r=2
             *
             */

            for (int i = l; i < r; i++) {
                if ((nums[i] & nums[r]) != 0) {
                    l = i + 1;
                }
            }
            r++;
            maxLen = Math.max(r - l, maxLen);

        }

        return maxLen;
    }

}
