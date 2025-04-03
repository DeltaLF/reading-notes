package com.leetcode.seventyFive.two_pointer;

public class MoveZeroes {
    public void moveZeroes(int[] nums) {

        /*
         * [0,1,0,3,12]
         * l r
         *
         */
        if (nums.length < 2)
            return;

        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            if (nums[l] != 0) {
                l++;
            } else {
                // move zero from l to r
                if (nums[l] == 0) {
                    this.moveNumFromLeftToRight(nums, l, r);
                    r--;
                }
            }
        }
    }

    private void moveNumFromLeftToRight(int[] nums, int left, int right) {
        for (int i = left; i < right; i++) {
            int temp = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = temp;
        }
    }
}