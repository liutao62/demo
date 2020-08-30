package com.liutao62.leetcode.string;

public class _07strStr {
    public static void main(String[] args) {
        System.out.println(new _07strStr().strStr("a", "a"));
    }

    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        if (needle.length() == 0) {
            return 0;
        }
        if (haystack.length() == 0) {
            return -1;
        }
        return kmp(haystack, needle);
    }

    public int kmp(String src, String target) {
        if (src == null || target == null) {
            return -1;
        }
        int[] next = getNext(target);
        int i = 0, j = 0;
        while (i < src.length()) {
            if (j < target.length()) {
                if (src.charAt(i) == target.charAt(j)) {
                    j++;
                    i++;
                } else {
                    j = next[j];
                    if (j == -1) {
                        j = 0;
                        i++;
                    }
                }
            } else {
                return i - j;
            }
        }
        return j < target.length() ? -1 : i - j;
    }

    private int[] getNext(String target) {
        char[] chars = target.toCharArray();
        int[] next = new int[chars.length];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < chars.length - 1) {
            if (k == -1 || chars[j] == chars[k]) {
                if (chars[++j] == chars[++k]) {
                    next[j] = next[k];
                } else {
                    next[j] = k;
                }
            } else {
                k = next[k];
            }
        }

        return next;
    }
}
