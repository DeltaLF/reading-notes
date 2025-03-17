package com.leetcode.daily;

import java.util.HashSet;
import java.util.Set;

public class DivideArrayIntoEqualPairs {
    public boolean divideArray(int[] nums) {
        Set<Integer> checkSet = new HashSet<>();
        for (Integer num : nums) {
            if (checkSet.contains(num)) {
                checkSet.remove(num);
            } else {
                checkSet.add(num);
            }
        }
        return checkSet.size() == 0;
    }

}
