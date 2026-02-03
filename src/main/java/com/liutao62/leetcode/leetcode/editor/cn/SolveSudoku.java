package com.liutao62.leetcode.leetcode.editor.cn;

public class SolveSudoku {

    char nums[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public void solveSudoku(char[][] board) {
        solve(board, 0, 0);
    }

    private boolean solve(char[][] board, int i, int j) {
        if (i >= 9) return true;
        if (j >= 9) {
            return solve(board, i + 1, 0);
        }
        if (board[i][j] != '.') {
            return solve(board, i, j + 1);
        }

        for (int i1 = 0; i1 < nums.length; i1++) {
            if (!isValid(board, i, j, nums[i1])) continue;
            board[i][j] = nums[i1];
            boolean solve = solve(board, i, j + 1);
            if (solve) {
                return true;
            }
            board[i][j] = '.';
        }
        return false;
    }

    private boolean isValid(char[][] board, int i, int j, char c) {
        for (int k = 0; k < 9; k++) {
            if (board[i][k] == c) return false;
            if (board[k][j] == c) return false;
            if (board[(i / 3) * 3 + k / 3][(j / 3) * 3 + k % 3] == c) return false;
        }
        return true;
    }
}
