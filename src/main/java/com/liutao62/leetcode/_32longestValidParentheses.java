package com.liutao62.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/longest-valid-parentheses/
 */
public class _32longestValidParentheses {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() <= 1) return 0;
        LinkedList<Integer> list = new LinkedList<>();
        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (!list.isEmpty()) {
                    // 保存(的位置
                    index.add(list.pop());
                    // 保存)的位置
                    index.add(i);
                }
            } else {
                list.push(i);
            }
        }
        // 最长连续有效()  -> 最长连续递增
        index.sort(Comparator.comparingInt(v -> v));
        // 这里处理的有点傻
        int maxLen = 2;
        int len = 2;
        for (int i = 1; i < index.size() - 1; i++) {
            if (index.get(i + 1) - index.get(i) == 1) {
                len++;
            } else {
                len = 1;
            }
            maxLen = Math.max(len, maxLen);
        }
        return index.size() == 0 ? 0 : maxLen;
    }

    public static void main(String[] args) {
        int i = new _32longestValidParentheses().longestValidParentheses("()(())");
        System.out.println(i);
    }
}
