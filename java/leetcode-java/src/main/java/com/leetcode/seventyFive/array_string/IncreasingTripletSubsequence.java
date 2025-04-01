package com.leetcode.seventyFive.array_string;

public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        /*
         * [1,2,3,4,5,6]
         * i j k
         * start moving k to right
         * if k > j
         * then check j > i
         * start move j to right
         * then check
         *
         * But what if
         * [1, 100, 2,3,4,5,6,7,8]
         *
         * Brute force
         * [n1, n2, n3, ... nn]
         * i=n1, j=n2, k=n3~nn
         * i=n1 j=n3, k=n4~nn
         *
         * O(n^3)
         *
         * how about make two list
         * from left to right
         * current min
         * [min(0,0), min(0~1),...min(0~n)]
         * from right to left
         * [max(1~n)...max(n-1~n),max(n)]
         *
         * now as long as there is a nunber
         * minList[x-1] < nums[x] < maxList[x+1]
         *
         */
        int n = nums.length;
        int[] minList = new int[n];
        minList[0] = nums[0];
        int[] maxList = new int[n];
        maxList[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            minList[i] = Math.min(minList[i - 1], nums[i]);
        }
        for (int i = n - 2; i >= 0; i--) {
            maxList[i] = Math.max(nums[i], maxList[i + 1]);
        }

        for (int i = 1; i < n - 1; i++) {
            if (minList[i - 1] < nums[i] && nums[i] < maxList[i + 1])
                return true;
        }
        return false;
    }
}
