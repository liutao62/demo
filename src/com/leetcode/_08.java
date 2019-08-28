package com.leetcode;

/**
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * <p>
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换
 */
public class _08 {
    /**
     * @param str
     * @return
     * @description long值溢出
     */
    public int myAtoi(String str) {
        if (str == null || str.trim().length() == 0) return 0;
        char[] chars = str.trim().toCharArray();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            // - + 符号处理
            if (i == 0 && c == 45) {
                sb.append(c);
            } else if (i == 0 &&  c == 43){
            }else if (c > 47 && c < 58) { // 数字处理
                sb.append(c);
            } else {
                break;
            }
        }

        String substring = sb.toString();
        if (sb.length() > 12) {
            substring = sb.charAt(0) + sb.substring(sb.length() - 12, sb.length());
        }

        Long target = 0L;
        if (substring.length() > 0) {
            if (substring.length() == 1 && substring.charAt(0) == 45) {
            } else
                target = Long.parseLong(substring);
        }
        if (target > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (target < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        else return target.intValue();
    }



/*    public int myAtoi(String str) {
        if (str == null || str.trim().length() == 0) return 0;
        str = str.trim();
        char[] chars = new char[str.length()];
        str.getChars(0, str.length(), chars, 0);

        int lastState = 0, nowState = 0; //0:未开始检测，或者为数字，-1：符号，1：字母
        int index = 0, length = str.length();
        long targetNumber = 0, temp = 0;
        boolean isPositive = true;
        while (index < length) {
            nowState = stateCharAt(str, index);
            if (nowState == 1) return getTarget(targetNumber);
            else if (nowState == 0) {
                if (!isPositive)
                    targetNumber = targetNumber * 10 - str.charAt(index) + 48;
                else
                    targetNumber = targetNumber * 10 + str.charAt(index) - 48;
            } else {
                if (lastState == -1) {
                    return getTarget(targetNumber);
                }
                if (str.charAt(index) == '-') isPositive = false;
                temp = targetNumber + temp;
                targetNumber = 0;
                lastState = nowState;
            }
            index++;
        }
        if (temp != 0) targetNumber = targetNumber + temp;
        return getTarget(targetNumber);

    }

    private int stateCharAt(String str, int index) {
        char c = str.charAt(index);
        if (47 < c && c < 58) return 0;
        else if (c == '+' || c == '-') return -1;
        else return 1;
    }

    private int getTarget(long target){
        if (target > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        else if (target < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return (int) target;
    }*/
}
