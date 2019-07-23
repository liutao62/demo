package com.leetcode;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""
 */
public class _14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        return "";
    }

    private boolean equals(String[] strings, int index) throws Exception {
        char temp = strings[0].charAt(index);
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].charAt(index) != temp) {
                return false;
            }
        }
        return true;
    }
}
