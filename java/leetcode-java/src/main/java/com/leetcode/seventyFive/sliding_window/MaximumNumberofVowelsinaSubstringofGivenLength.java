package com.leetcode.seventyFive.sliding_window;

public class MaximumNumberofVowelsinaSubstringofGivenLength {
    public int maxVowels(String s, int k) {
        /*
         * s = "abciiidef", k = 3
         * l r
         */
        String vowels = "aeiou";
        int max = 0;
        int count = 0;
        int l = 0;
        int r = 0;
        while (r < s.length()) {
            if (vowels.indexOf(s.charAt(r)) != -1) {
                count++;
                max = Math.max(count, max);
            }
            if (r - l + 1 >= k) {
                if (vowels.indexOf(s.charAt(l)) != -1) {
                    count--;
                }
                l++;
            }
            r++;
        }

        return max;
    }

    public int maxVowelsCharArr(String s, int k) {
        /*
         * s = "abciiidef", k = 3
         * l r
         */
        String vowels = "aeiou";
        char[] sArr = s.toCharArray();
        int n = sArr.length;
        int max = 0;
        int count = 0;
        int l = 0;
        int r = 0;
        while (r < n) {
            if (vowels.indexOf(sArr[r]) != -1) {
                count++;
                max = Math.max(count, max);
            }
            if (r - l + 1 >= k) {
                if (vowels.indexOf(sArr[l]) != -1) {
                    count--;
                }
                l++;
            }
            r++;
        }

        return max;
    }
}
