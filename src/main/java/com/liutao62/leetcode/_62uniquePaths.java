package com.liutao62.leetcode;

/**
 * @author liutao
 * @date Created in 2021/10/26 22:01
 * @description
 */
public class _62uniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (j > 0 && i > 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else {
                    dp[i][j] = 1;
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }


    public static void main(String[] args) {
        int i = new _62uniquePaths().uniquePaths(3, 7);
        System.out.println(i);
    }
}
