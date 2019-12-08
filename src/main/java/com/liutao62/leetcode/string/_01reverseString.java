package com.liutao62.leetcode.string;

public class _01reverseString {
    public void reverseString(char[] s) {
        if (s == null || s.length < 2) {
            return;
        }
        for (int i = 0; i < s.length >> 1; i++) {
            char c = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = c;
        }
    }
}
