package com.liutao62.leetcode;

public class _70climbStairs {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        int[] opt = new int[n];
        opt[0] = 1;
        opt[1] = 2;
        for (int i = 2; i < n; i++) {
            opt[i] = opt[i - 1] + opt[i - 2];
        }
        return opt[n];
    }
}
