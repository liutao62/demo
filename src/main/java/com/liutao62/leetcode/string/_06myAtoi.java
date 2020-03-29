package com.liutao62.leetcode.string;

/**
 * https://leetcode-cn.com/explore/featured/card/top-interview-questions-easy/5/strings/37/
 */
public class _06myAtoi {
    public int myAtoi(String str) {
        if (str == null || "".equals((str = str.trim()))) {
            return 0;
        }
        boolean flag = true;
        int index = 0;
        if (str.charAt(index) == '-') {
            flag = false;
            index++;
        }
        if (index == 0 && str.charAt(index) == '+') {
            index++;
        }
        StringBuilder sb = new StringBuilder();
        while (index < str.length() && Character.isDigit(str.charAt(index))) {
            if (str.charAt(index) == '0' && sb.length() == 0){
                index++;
                continue;
            }
            sb.append(str.charAt(index++));
        }
        if (sb.length() == 0) {
            return 0;
        }
        if (sb.length() > 10){
            if (flag){
                return Integer.MAX_VALUE;
            }
            return Integer.MIN_VALUE;
        }
        long num = Long.parseLong(sb.toString());
        if (!flag) {
            num = -num;
            if (num < Integer.MIN_VALUE){
                return Integer.MIN_VALUE;
            }
        }else if (num > Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }
        return (int) num;
    }
}
