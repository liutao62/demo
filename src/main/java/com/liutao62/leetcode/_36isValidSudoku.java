package com.liutao62.leetcode;

public class _36isValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (exsits(board, i, j)) return false;
            }
        }
        return true;
    }

    private boolean exsits(char[][] board, int i, int j) {
        char c = board[i][j];
        for (int x = 0; x < board[i].length; x++) {
            if (x == j) continue;


        }
        return false;
    }
}
