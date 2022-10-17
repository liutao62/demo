package com.liutao62.leetcode;

public class _79exist {
    public boolean exist(char[][] board, String word) {
        return dfs(board, word, "", 0, 0);
    }

    private boolean dfs(char[][] board, String target, String path, int i, int j) {
        if (target.equals(path)) {
            return true;
        }
        if (i < 0 || j < 0 || i > board.length - 1 || j > board[i].length - 1) {
            return false;
        }
        if (target.startsWith(path)) {

            return dfs(board, target, path + board[i][j], i + 1, j)
                    || dfs(board, target, path + board[i][j], i, j + 1)
                    || dfs(board, target, path + board[i][j], i - 1, j)
                    || dfs(board, target, path + board[i][j], i, j - 1);
        }
        return false;
    }
}
