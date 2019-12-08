package com.liutao62.leetcode.string;

public class _05isPalindrome {
    public boolean isPalindrome(String s) {
        if (s == null || "".equals(s.trim())) {
            return true;
        }
        int i = 0, j = s.length() - 1;
        while (i < j) {
            char c = s.charAt(i);
            char c1 = s.charAt(j);
            if (Character.isLetterOrDigit(c) && Character.isLetterOrDigit(c1)) {
                if (String.valueOf(c).toLowerCase().equals(String.valueOf(c1).toLowerCase())) {
                    i++;
                    j--;
                } else {
                    return false;
                }
            } else if (!Character.isLetterOrDigit(c)) {
                i++;
            } else if (!Character.isLetterOrDigit(c1)) {
                j--;
            }
        }
        return true;
    }
}
