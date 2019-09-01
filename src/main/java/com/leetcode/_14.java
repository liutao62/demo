package com.leetcode;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""
 */
public class _14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        int minIndex = getMinIndex(strs);
        int index = equalsIndex(strs, 0, minIndex);
        if (index < 0) return "";


        String substring = strs[0].substring(0, index);
        return substring;
    }

    private int equalsIndex(String[] strings, int beginIndex, int endIndex) {
        int idx = beginIndex;
        while (idx <= endIndex) {
            char temp = strings[0].charAt(idx);
            for (int i = 1; i < strings.length; i++) {
                String str = strings[i];
                if (str != null && str.length() > 0) {
                    if (str.charAt(idx) != temp) {
                        return idx;
                    }
                }
            }
            idx++;
        }
        return idx;
    }

    private int getMinIndex(String[] strs) {
        int index = Integer.MAX_VALUE;
        for (String str : strs) {
            if (str != null) {
                index = str.length() - 1 < index ? str.length() - 1 : index;
            } else return -1;
        }
        return index;
    }
}
