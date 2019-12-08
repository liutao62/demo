package com.liutao62.leetcode.array;

public class _10isValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            int[] row = new int[9];
            int[] col = new int[9];
            int[] grid = new int[9];
            for (int i1 = 0; i1 < 9; i1++) {
                char r = board[i][i1];
                if (r != '.') {
                    if (row[r - '1'] == 1) {
                        return false;
                    } else {
                        row[r - '1'] = 1;
                    }
                }
                char c = board[i1][i];
                if (c != '.') {
                    if (col[c - '1'] == 1) {
                        return false;
                    } else {
                        col[c - '1'] = 1;
                    }
                }
                int x = 3 * (i / 3) + i1 / 3;
                int y = 3 * (i % 3) + i1 % 3;
                char g = board[x][y];
                if (g != '.') {
                    if (grid[g - '1'] == 1) {
                        return false;
                    } else {
                        grid[g - '1'] = 1;
                    }
                }
            }
        }
        return true;
    }
}
