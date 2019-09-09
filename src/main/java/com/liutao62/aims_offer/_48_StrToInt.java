package com.liutao62.aims_offer;

/**
 * 将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)，
 * 要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0。
 * 输入描述:
 * 输入一个字符串,包括数字字母符号,可以为空
 * 输出描述:
 * 如果是合法的数值表达则返回该数字，否则返回0
 */
public class _48_StrToInt {
    public int StrToInt(String str) {
        if (str == null || str.trim().length() == 0) return 0;
        char[] chars = str.toCharArray();
        int sum = 0;
        boolean isPositive = true;
        for (int i = 0; i < chars.length; i++) {
            if (i == 0) {
                if (chars[i] == '+') {
                    isPositive = true;
                    continue;
                } else if (chars[i] == '-') {
                    isPositive = false;
                    continue;
                } else if (chars[i] <= 48 && chars[i] >= 57)
                    return 0;
            }
            if (chars[i] >= 48 && chars[i] <= 57) {
                sum = sum * 10 + (chars[i] - 48);
            } else return 0;
        }
        return isPositive ? sum : -sum;
    }
}
