package com.liutao62.leetcode;

/**
 * @author liutao
 * @date Created in 2021/10/26 21:45
 * @description
 */
public class _64minPathSum {
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (j > 0 && i > 0) {
                    dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
                } else if (j > 0) {
                    dp[i][j] = grid[i][j] + dp[i][j - 1];
                } else if (i > 0) {
                    dp[i][j] = grid[i][j] + dp[i - 1][j];
                } else {
                    dp[i][j] = grid[i][j];
                }
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }

    public static void main(String[] args) {
        System.out.println(new _64minPathSum().minPathSum(new int[][]{
                {1, 3, 1}, {1, 5, 1}, {4, 2, 1}
        }));
        System.out.println(new _64minPathSum().minPathSum(new int[][]{
                {1, 2, 3}, {4, 5, 6}
        }));
    }
}
