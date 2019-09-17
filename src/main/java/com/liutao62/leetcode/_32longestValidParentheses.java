package com.liutao62.leetcode;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/longest-valid-parentheses/
 */
public class _32longestValidParentheses {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() <= 1) return 0;
        int count = 0;
        LinkedList<Character> list = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (!list.isEmpty()) {
                    list.pop();
                    count++;
                }
            } else {
                Character c;
                if ((c = list.peek()) == null || c == '(') {
                    list.push(s.charAt(i));
                }
            }
        }
        return count;
    }
}
