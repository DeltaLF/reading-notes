package com.leetcode.seventyFive.sliding_window;

import java.util.LinkedList;
import java.util.Queue;

public class MaxConsecutiveOnesIII {
    public int longestOnes(int[] nums, int k) {
        // space optimized
        int l = 0;
        int r = 0;
        int zeroCount = 0;
        int max = 0;
        int n = nums.length;
        while (r < n) {
            if (nums[r] == 0) {
                // flip
                zeroCount++;
                if (zeroCount > k) {
                    zeroCount--;
                    while (nums[l] != 0) {
                        l++;
                    }
                    l++;
                }
            }
            r++;
            max = Math.max(max, r - l);
        }
        return max;
    }

    public int longestOnesWithList(int[] nums, int k) {
        /*
         * [1,1,1,0,0,0,1,1,1,1,0] k=2
         * l r
         *
         * r=3 l=0 k=2-1
         * queue:[3]
         */
        int l = 0;
        int r = 0;
        int max = 0;
        int i = 0;
        int leftInedex = 0;
        int n = nums.length;
        int[] kCount = new int[n];
        while (r < n) {
            if (nums[r] == 0) {
                // flip
                kCount[i] = r;
                i++;
                if (i - leftInedex > k) {
                    l = kCount[leftInedex] + 1;
                    leftInedex++;
                }
            }
            r++;
            max = Math.max(max, r - l);
        }
        return max;
    }

    public int longestOnesWithQueue(int[] nums, int k) {
        /*
         * [1,1,1,0,0,0,1,1,1,1,0] k=2
         * l r
         *
         * r=3 l=0 k=2-1
         * queue:[3]
         */
        int l = 0;
        int r = 0;
        int max = 0;
        Queue<Integer> flipQueue = new LinkedList<>();
        while (r < nums.length) {
            if (nums[r] == 0) {
                // flip
                flipQueue.add(r);
                if (flipQueue.size() > k) {
                    // left most zero
                    l = flipQueue.poll() + 1;
                }
            }
            r++;
            max = Math.max(max, r - l);
        }
        return max;
    }
}
