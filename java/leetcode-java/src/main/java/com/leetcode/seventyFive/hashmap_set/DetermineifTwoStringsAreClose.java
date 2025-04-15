package com.leetcode.seventyFive.hashmap_set;

import java.util.Arrays;
import java.util.HashMap;

public class DetermineifTwoStringsAreClose {
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length())
            return false;

        int[] occur1 = new int[26];
        int[] occur2 = new int[26];
        for (char c : word1.toCharArray()) {
            occur1[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            occur2[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (occur1[i] > 0 && occur2[i] == 0)
                return false;
            if (occur1[i] == 0 && occur2[i] > 0) {
                return false;
            }
        }
        Arrays.sort(occur1);
        Arrays.sort(occur2);

        for (int i = 0; i < 26; i++) {
            if (occur1[i] != occur2[i])
                return false;
        }

        return true;
    }

    public boolean closeStringsUnoptimized(String word1, String word2) {
        /*
         * 1. find character occurrence count for the two string
         * 2. make sure the counts are the same and the character combinations are the
         * same too
         * (regardless the distribution of the alphabet)
         * 2 2 2 1
         * 2 3
         */
        Integer len = word1.length();
        if (word2.length() != len) {
            return false;
        }
        HashMap<Character, Integer> map1 = findCharOccurInString(word1);
        HashMap<Character, Integer> map2 = findCharOccurInString(word2);
        if (map1.size() != map2.size())
            return false;
        int[] occurAarr = new int[len + 1];

        for (HashMap.Entry<Character, Integer> en : map1.entrySet()) {
            Character key = en.getKey();
            Integer val = en.getValue();
            if (!map2.containsKey(key)) {
                return false;
            }
            occurAarr[val] += 1;
        }
        // check occur count is the same
        for (HashMap.Entry<Character, Integer> en : map2.entrySet()) {
            Integer val = en.getValue();
            occurAarr[val] -= 1;
        }
        for (Integer count : occurAarr) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    private HashMap<Character, Integer> findCharOccurInString(String word) {

        int len = word.length();
        HashMap<Character, Integer> charOccur = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char cuurChar = word.charAt(i);
            Integer charCount = charOccur.getOrDefault(cuurChar, 0);
            charOccur.put(cuurChar, charCount + 1);
        }
        return charOccur;
    }
}
