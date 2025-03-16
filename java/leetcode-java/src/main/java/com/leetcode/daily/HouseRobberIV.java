package com.leetcode.daily;

import java.util.Arrays;

public class HouseRobberIV {
    private int k;
    private int[] nums;

    public int minCapability(int[] nums, int k) {
        // goal: find the one number in nums that satisfies the requirement
        this.k = k;
        this.nums = nums;
        int l = Arrays.stream(nums).min().getAsInt();
        int r = Arrays.stream(nums).max().getAsInt();
        int m = (r + l) / 2;

        while (r > l) {
            if (canSteal(m)) {
                r = m;
            } else {
                l = m + 1;
            }
            m = (r + l) / 2;

        }

        return m;
    }

    private boolean canSteal(int target) {
        int steal = 0;
        int i = 0;
        while (i < this.nums.length) {
            if (this.nums[i] <= target) {
                steal++;
                i += 2;
            } else {
                i++;
            }
        }
        return steal >= k;
    }

}

// memory limit exceeded
class FailedAttemptHouseRobberIVMLE {
    private int[] houses;
    private int numsLen;
    private int inf;
    private int memo[][][];
    private int max;

    public int minCapability(int[] nums, int k) {
        // nums: [house0, house1, ...]
        this.houses = nums;
        this.numsLen = nums.length;
        this.max = findMax(nums);
        this.inf = Integer.MAX_VALUE;
        this.memo = new int[this.numsLen + 1][k + 1][this.max + 1];

        return findMinCap(0, k, 0);
    }

    private int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i]; // Update max if a larger value is found
            }
        }
        return max;
    }

    // (currIndex,requirement) => maxCap
    private int findMinCap(int curr, int req, int currMax) {
        if (req == 0)
            return currMax;
        if (numsLen <= curr)
            return this.inf;
        if (this.memo[curr][req][currMax] > 0)
            return this.memo[curr][req][currMax];

        int robCurr = findMinCap(curr + 2, req - 1, Math.max(currMax, this.houses[curr]));
        int skipCurr = findMinCap(curr + 1, req, currMax);
        int min = Math.min(robCurr, skipCurr);
        this.memo[curr][req][currMax] = min;
        return min;
    }
}

/*
 * [2, 3, 5, 9]
 * start
 * 2 n
 * 5 9 3 5 9
 *
 *
 *
 *
 */