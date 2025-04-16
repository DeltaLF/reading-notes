package com.leetcode.seventyFive.stack;

public class RemovingStarsFromaString {
    public String removeStars(String s) {
        /*
         * s = "leet**cod*e"
         *
         * from right to left
         * store * in stack
         */
        char[] words = s.toCharArray();
        char star = '*';
        Integer n = s.length();
        Integer starCount = 0;
        char[] reverseStr = new char[n];
        int ind = 0;
        for (int i = n - 1; i >= 0; i--) {
            char c = words[i];
            if (c == star) {
                starCount++;
                continue;
            }
            if (starCount > 0) {
                starCount--;
                continue;
            }
            reverseStr[ind] = c;
            ind++;
        }
        // reverseStr.toString()
        char[] ans = new char[ind];
        for (int i = 0; i < ind; i++) {
            ans[i] = reverseStr[ind - i - 1];
        }
        return new String(ans);
    }
}
