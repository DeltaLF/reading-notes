package com.leetcode.seventyFive.array_string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReverseVowelsofaString {
    public String reverseVowels(String s) {
        Set<Character> vowelSet = new HashSet<>();
        vowelSet.add('a');
        vowelSet.add('e');
        vowelSet.add('i');
        vowelSet.add('o');
        vowelSet.add('u');
        vowelSet.add('A');
        vowelSet.add('E');
        vowelSet.add('I');
        vowelSet.add('O');
        vowelSet.add('U');
        char[] sArray = s.toCharArray();
        int l = 0;
        int r = s.length() - 1;
        int sLen = s.length();
        while (l < sLen && r > 0 && l < r) {
            while (l < sLen && !vowelSet.contains(sArray[l])) {
                l++;
            }
            while (r > 0 && !vowelSet.contains(sArray[r])) {
                r--;
            }
            if (l < r) {
                char temp = sArray[l];
                sArray[l] = sArray[r];
                sArray[r] = temp;
                l++;
                r--;
            }
        }
        return new String(sArray);
    }

    public String reverseVowelsInefficient(String s) {
        Set<String> vowelSet = new HashSet<>(Arrays.asList("a", "e", "i", "o", "u", "A", "E", "I", "O", "U"));
        List<String> vowelArr = new ArrayList<>();
        StringBuilder strBuilder = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            String c = String.valueOf(s.charAt(i));
            if (vowelSet.contains(c)) {
                vowelArr.add(String.valueOf(c));
            }
        }
        int j = vowelArr.size() - 1;
        for (int i = 0; i < s.length(); i++) {
            String c = String.valueOf(s.charAt(i));
            if (vowelSet.contains(c)) {
                strBuilder.setCharAt(i, vowelArr.get(j).charAt(0));
                j--;
            }
        }

        return strBuilder.toString();
    }
}