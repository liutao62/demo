package com.liutao62.leetcode;

/**
 * https://leetcode-cn.com/problems/fibonacci-number/
 */
public class _509fib {
    public int fib(int N) {
        if (N <= 0) return 0;
        if (N == 1) return 1;
        int first = 0, second = 1;
        for (int i = 2; i <= N; ++i) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }
}
