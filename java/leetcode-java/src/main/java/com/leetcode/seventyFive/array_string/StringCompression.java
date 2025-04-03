package com.leetcode.seventyFive.array_string;

public class StringCompression {
    public int compress(char[] chars) {
        /*
         * ["a","a","b","b","c","c","c"]
         * i j
         * storedIndex
         */
        int n = chars.length;
        int i = 0;
        int j;
        int storeInd = 0;
        while (i < n) {
            char currChar = chars[i];
            j = i + 1;
            while (j < n && currChar == chars[j]) {
                j++;
            }
            // store
            chars[storeInd] = currChar;
            if (j - i > 1) {
                char[] numCharArr = this.fromIntToChars(j - i);
                for (int d = 0; d < numCharArr.length; d++) {
                    storeInd++;
                    chars[storeInd] = numCharArr[d];
                }
            }
            storeInd++;
            i = j;

        }

        return storeInd;
    }

    private char[] fromIntToChars(int num) {
        String numberString = String.valueOf(num);
        char[] charArray = numberString.toCharArray();
        return charArray;
    }
}
