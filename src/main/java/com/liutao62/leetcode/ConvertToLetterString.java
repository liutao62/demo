package com.liutao62.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liutao
 * @date Created in 2021/4/8 21:48
 * @description 从左往右的尝试模型：假设 1 对应 A ，2 对应 B ，3 对应 C 那么给定一个只有数字的字符数组共有多少种结果
 */
public class ConvertToLetterString {

    public static void main(String[] args) {
        String numbers = "1111";
        char[] chars = numbers.toCharArray();

        List<String> list = new ArrayList<>();
        int process = process(0, chars);
        System.out.println(process);
    }

    private static int process(int begin, char[] chars) {
        if (begin == chars.length) {
            return 1;
        }
        // 1 对应 A ，如果开始为 0 ，则无法转换。 但是可以转换 10 --> J
        if (chars[begin] == '0') {
            return 0;
        }
        int result = 0;
        char c = chars[begin];
        if (c == '1') {
            if (begin < chars.length - 1) {
                result += process(begin + 2, chars);
            }
        } else if (c == '2') {
            if (begin < chars.length - 1 && chars[begin + 1] < '7') {
                result += process(begin + 2, chars);
            }
        }
        result += process(begin + 1, chars);
        return result;
    }
}
