package com.liutao62.leetcode;

public class _200numIslands {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null
                || grid[0].length == 0) return 0;
        int n = grid.length, m = grid[0].length;
        boolean[][] visit = new boolean[n][m];
        int num = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int i1 = 0; i1 < grid[i].length; i1++) {
                if (grid[i][i1] == '0' || visit[i][i1]) continue;
                num++;
                dfs(grid, visit, i, i1);
            }
        }
        return num;
    }

    private void dfs(char[][] grid, boolean[][] visit, int i, int j) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0' || visit[i][j])
            return;
        visit[i][j] = true;
        dfs(grid, visit, i - 1, j);
        dfs(grid, visit, i + 1, j);
        dfs(grid, visit, i, j - 1);
        dfs(grid, visit, i, j + 1);
    }
}
