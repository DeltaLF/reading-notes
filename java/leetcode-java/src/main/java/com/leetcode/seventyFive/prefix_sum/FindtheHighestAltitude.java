package com.leetcode.seventyFive.prefix_sum;

public class FindtheHighestAltitude {
    public int largestAltitude(int[] gain) {
        int max = 0;
        int curr = 0;
        for (int currGain : gain) {
            curr += currGain;
            max = Math.max(max, curr);
        }
        return max;
    }
}
