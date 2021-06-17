package com.liutao62.leetcode;

/**
 * @author liutao
 * @date Created in 2021/6/17 22:34
 * @description 有效数字（按顺序）可以分成以下几个部分：1一个 小数 或者 整数,2（可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * <p>
 * 小数（按顺序）可以分成以下几个部分：
 * 1（可选）一个符号字符（'+' 或 '-'）
 * 2下述格式之一：
 * 2.1至少一位数字，后面跟着一个点 '.'
 * 2.2至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 2.3一个点 '.' ，后面跟着至少一位数字
 * <p>
 * 整数（按顺序）可以分成以下几个部分：
 * 1（可选）一个符号字符（'+' 或 '-'）
 * 2至少一位数字
 * 部分有效数字列举如下：
 * ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
 * 部分无效数字列举如下：
 * ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
 */
public class _65isNumber {
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        char[] chars = s.toCharArray();
        char lastChar = ' ';
        boolean includeE = false;

        for (char c : chars) {
            if (c == 'e' || c == 'E') {
                includeE = true;
                lastChar = c;
                continue;
            }
            // 一个 'e' 或 'E' ，后面跟着一个 整数
            if (includeE) {
                if (c < 48 || c > 57) {
                    return false;
                }
            } else {
                if (c == '+' || c == '-') {
                    includeE = false;
                } else if ((c < 48 || c > 57) && c != '.') {
                    return false;
                }
            }
            lastChar = c;
        }
        if (lastChar == 'e' || lastChar == 'E' || lastChar == '.') {
            return false;
        }
        return true;
    }

    /**
     * 输入：
     * "e9"
     * 输出：
     * true
     * 预期结果：
     * false
     *
     * @param args
     */
    public static void main(String[] args) {
        // "0"
        // "e"
        // "."
        // ".1"
        new _65isNumber().isNumber("e");
    }
}
