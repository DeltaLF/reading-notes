package com.leetcode.daily;

public class MinimumOperationstoMakeBinaryArrayElementsEqualtoOneI {
    private int opCount = 0;
    private int[] nums;

    public int minOperations(int[] nums) {
        this.nums = nums;

        for (int i = 0; i < nums.length; i++) {
            operation(i);
        }

        this.opCount = (nums[nums.length - 1] == 0) | (nums[nums.length - 2] == 0) ? -1 : opCount;
        return this.opCount;
    }

    private void operation(int ind) {
        if (ind + 2 >= nums.length)
            return;
        if (this.nums[ind] == 0) {
            // switch
            this.opCount++;
            nums[ind + 1] ^= 1;
            nums[ind + 2] ^= 1;
        }
    }
}
