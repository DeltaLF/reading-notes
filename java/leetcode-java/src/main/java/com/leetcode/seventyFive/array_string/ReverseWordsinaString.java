package com.leetcode.seventyFive.array_string;

import java.util.ArrayList;

public class ReverseWordsinaString {

    public String reverseWords(String s) {
        /*
         * "abc def  ghiaaa  "
         * _| |
         * _j i
         * -> k
         */
        char[] words = s.toCharArray();
        ArrayList<Character> ans = new ArrayList<>();
        int i = s.length() - 1;
        while (i >= 0) {
            if (words[i] == ' ') {
                i--;
            } else {
                int j = i;
                while (j >= 0 && words[j] != ' ') {
                    j--;
                }
                // append word
                if (!ans.isEmpty()) {
                    ans.add(' ');
                }
                for (int k = j + 1; k <= i; k++) {
                    ans.add(words[k]);
                }
                i = j;
            }
        }

        StringBuilder sb = new StringBuilder(ans.size());
        for (Character c : ans) {
            sb.append(c);
        }
        return sb.toString();
    }

}