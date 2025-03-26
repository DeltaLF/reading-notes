package com.leetcode.seventyFive.array_string;

import java.util.ArrayList;
import java.util.List;

public class GreatestCommonDivisorofStrings {

    public String gcdOfStrings(String str1, String str2) {
        /**
         * Given a string:
         * list all the possible strings can divide it
         */
        List<String> divors1 = findAllDivisors(str1);
        List<String> divors2 = findAllDivisors(str2);
        String ans = "";
        for (int i = 0; i < divors1.size(); i++) {
            for (int j = 0; j < divors2.size(); j++) {
                String subStr1 = divors1.get(i);
                String subStr2 = divors2.get(j);
                if (subStr2.length() < subStr1.length())
                    break;
                if (subStr1.equals(subStr2)) {
                    if (subStr1.length() > ans.length()) {
                        ans = subStr1;
                    }

                }
            }
        }
        return ans;
    }

    private List<String> findAllDivisors(String str) {
        int strLen = str.length();
        // int sqrtLoot = (int) Math.floor(Math.sqrt(strLen));
        List<String> diviors = new ArrayList<>();
        diviors.add(str);
        for (int i = 2; i <= strLen; i++) {
            if (strLen % i == 0) {
                int subStrLen = strLen / i;
                String subStr = str.substring(0, subStrLen);

                if (isDivideAble(str, subStr)) {
                    diviors.add(subStr);
                }
            }
        }
        return diviors;

    }

    private Boolean isDivideAble(String str, String subString) {
        int strLen = str.length();
        int subStrLen = subString.length();
        if (subStrLen > strLen || ((strLen % subStrLen) != 0))
            return false;
        for (int i = 0; i < strLen / subStrLen; i++) {
            if (!subString.equals(str.substring(i * subStrLen, (i + 1) * (subStrLen)))) {
                return false;
            }
        }
        return true;

    }
}
