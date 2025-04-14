package com.leetcode.seventyFive.hashmap_set;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class UniqueNumberofOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        /*
         * [1,2,2,1,1,3]
         * 1:3
         * 2:2
         * 3:1
         */
        HashMap<Integer, Integer> occurMap = new HashMap<>();
        LinkedList<Integer> compareList = new LinkedList<>();
        HashSet<Integer> compareSet = new HashSet<>();
        for (int num : arr) {
            Integer currntCount = occurMap.getOrDefault(num, 0);
            occurMap.put(num, currntCount + 1);
        }

        for (HashMap.Entry<Integer, Integer> en : occurMap.entrySet()) {
            Integer val = en.getValue();
            compareList.add(val);
            compareSet.add(val);
        }
        return compareList.size() == compareSet.size();
    }
}
