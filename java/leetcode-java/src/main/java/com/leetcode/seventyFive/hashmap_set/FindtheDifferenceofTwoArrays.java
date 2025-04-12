package com.leetcode.seventyFive.hashmap_set;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FindtheDifferenceofTwoArrays {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {

        List list1 = new LinkedList<>();
        List list2 = new LinkedList<>();
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        ans.add(list1);
        ans.add(list2);

        Set<Integer> set1 = Arrays.stream(nums1).boxed()
                .collect(Collectors.toSet());
        Set<Integer> set2 = Arrays.stream(nums2).boxed()
                .collect(Collectors.toSet());
        for (int num : set1) {
            if (!set2.contains(num)) {
                list1.add(num);
            }
        }
        for (int num : set2) {
            if (!set1.contains(num)) {
                list2.add(num);
            }
        }

        return ans;

    }
}
