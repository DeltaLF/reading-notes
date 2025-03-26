package com.leetcode.seventyFive.array_string;

import java.util.ArrayList;
import java.util.List;

public class KidsWiththeGreatestNumberofCandies {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = 0;
        for (int candyNum : candies) {
            if (candyNum > max) {
                max = candyNum;
            }
        }
        List<Boolean> ans = new ArrayList<>(candies.length);
        for (int i = 0; i < candies.length; i++) {
            if (candies[i] + extraCandies >= max) {
                ans.set(i, true);
            } else {
                ans.set(i, false);
            }
        }
        return ans;
    }
}
