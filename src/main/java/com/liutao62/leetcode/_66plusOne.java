package com.liutao62.leetcode;

import java.util.Arrays;

/**
 * @author liutao
 * @date Created in 2021/6/17 23:04
 * @description
 */
public class _66plusOne {
    public int[] plusOne(int[] digits) {
        boolean plusOne = true;
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] = digits[i] + (plusOne ? 1 : 0);
            if (digits[i] > 9) {
                plusOne = true;
                digits[i] = 0;
            } else {
                plusOne = false;
            }
        }
        if (plusOne) {
            int[] result = new int[digits.length + 1];
            System.arraycopy(digits, 0, result, 1, result.length - 1);
            result[0] = 1;
            return result;
        }
        return digits;
    }

    public static void main(String[] args) {
        int[] ints = new _66plusOne().plusOne(new int[]{9, 9});

        Arrays.stream(ints).forEach(System.out::println);
    }
}
