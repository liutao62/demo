package com.liutao62.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    public static void main(String[] args) {
        print(6);
        Solution solution = new NQueens().new Solution();
        List<List<String>> lists = solution.solveNQueens(4);
        for (List<String> list : lists) {
            for (String s : list) {
                System.out.println(s);
            }
            System.out.println();
        }
    }

    private static void print(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%d%d ", i, j);
            }
            System.out.println();
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> solveNQueens(int n) {
            boolean[][] board = new boolean[n][n];
            List<List<String>> res = new ArrayList<>();
            backTrack(board, res, 0);
            return res;
        }

        private void backTrack(boolean[][] board, List<List<String>> res, int row) {
            if (row == board.length){
                List<String> way = new ArrayList<>();
                for (int i1 = 0; i1 < board.length; i1++) {
                    StringBuilder sb = new StringBuilder();
                    for (int i2 = 0; i2 < board[i1].length; i2++) {
                        sb.append(board[i1][i2] ? "Q" : ".");
                    }
                    way.add(sb.toString());
                }
                res.add(way);
                return;
            }

            for (int i = 0; i < board.length; i++) {
                if (!check(board, row, i)) {
                    continue;
                }
                board[row][i] = true;
                backTrack(board, res, row + 1);
                board[row][i] = false;
            }
        }

        private boolean check(boolean[][] board, int i, int j) {
            int x = i - j;
            boolean flag = x >= 0;
            for (int i1 = 0; i1 < board.length; i1++) {
                if (board[i1][j]) return false;
                if (flag && i1 + x < board.length && board[i1 + x][i1]) return false;
                else if (!flag && i1 - x >= 0 && i1 - x < board.length && board[i1][i1 - x]) return false;
            }
            int y = i + j;
            for (int j1 = 0; j1 < board[0].length; j1++) {
                if (board[i][j1]) return false;
                if (y - j1 >= 0 && y - j1 < board.length && board[j1][y - j1]) return false;
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}