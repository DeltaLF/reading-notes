package com.leetcode.seventyFive.prefix_sum;

public class FindPivotIndex {
    public int pivotIndex(int[] nums) {
        if (nums.length == 1)
            return 0;
        /*
         * [1,7,3,6,5,6]
         * from left
         * 1,8,11,17,22,28
         * from right
         * 28,27,20,17,11,6
         */
        int n = nums.length;
        int[] fromLeft = new int[n];
        int[] fromRight = new int[n];
        int left = 0;
        int right = 0;
        for (int i = 0; i < n; i++) {
            left += nums[i];
            fromLeft[i] = left;
            right += nums[n - 1 - i];
            fromRight[n - 1 - i] = right;
        }
        // check
        if (fromRight[1] == 0)
            return 0;

        for (int i = 1; i < n - 1; i++) {
            if (fromLeft[i - 1] == fromRight[i + 1])
                return i;
        }
        if (fromLeft[n - 2] == 0)
            return n - 1;
        return -1;
    }
}
