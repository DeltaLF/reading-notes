package com.leetcode.seventyFive.two_pointer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaxNumberofKSumPairs {
    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int num : nums) {
            int diff = k - num;
            if (map.containsKey(diff) && map.get(diff) > 0) {
                count++;
                int val = map.get(diff);
                map.put(diff, val - 1);
            } else {
                if (map.containsKey(num)) {
                    int numCount = map.get(num);
                    map.put(num, numCount + 1);
                } else {
                    map.put(num, 1);
                }
            }
        }
        return count;
    }

    public int maxOperationsInefficient(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                int val = map.get(num);
                map.put(num, val + 1);
            } else {
                map.put(num, 1);
            }
        }
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            int diff = k - key;
            if (diff > 0 && map.containsKey(diff)) {
                int matchedCount = map.get(diff);
                if (diff == key) {
                    count += Math.floor(matchedCount / 2);
                    map.put(key, 0);
                } else {
                    int min = Math.min(matchedCount, value);
                    count += min;
                    map.put(key, value - min);
                    map.put(diff, matchedCount - min);
                }

            }
        }
        return count;
    }

    public int maxOperationsSort(int[] nums, int k) {
        /*
         * [3,1,3,4,3], k = 6
         * 1 3 3 3 4 k=6
         * l r
         * 1. sort
         * 2. l-> <-r
         * 3. if l+r > k =>decrease r
         * 4. if l+r < k =>increase l
         *
         */
        Arrays.sort(nums);
        int l = 0;
        int r = nums.length - 1;
        int count = 0;
        while (l < r) {
            int sum = nums[l] + nums[r];
            if (sum > k) {
                r--;
            } else if (sum < k) {
                l++;
            } else {
                count++;
                l++;
                r--;
            }
        }
        return count;
    }
}
