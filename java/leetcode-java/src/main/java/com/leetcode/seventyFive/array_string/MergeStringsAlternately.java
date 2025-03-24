package com.leetcode.seventyFive.array_string;

public class MergeStringsAlternately {

    public String mergeAlternately(String word1, String word2) {
        int a = 0;
        int aLen = word1.length();
        int b = 0;
        int bLen = word2.length();
        StringBuilder strBuilder = new StringBuilder();
        while (a < aLen & b < bLen) {
            strBuilder.append(word1.charAt(a));
            strBuilder.append(word2.charAt(b));
            a++;
            b++;
        }
        while (a < aLen) {
            strBuilder.append(word1.charAt(a));
            a++;
        }
        while (b < bLen) {
            strBuilder.append(word2.charAt(b));
            b++;
        }

        return strBuilder.toString();

    }

}
