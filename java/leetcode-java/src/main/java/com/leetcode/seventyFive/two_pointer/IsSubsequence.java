package com.leetcode.seventyFive.two_pointer;

public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0)
            return true;
        if (t.length() == 0)
            return false;
        int si = 0;
        int ti = 0;
        while (ti < t.length()) {

            if (s.charAt(si) == t.charAt(ti)) {
                si++;
                ti++;
                if (si == s.length())
                    return true;
            } else {
                ti++;
            }
        }

        return false;

    }
}
