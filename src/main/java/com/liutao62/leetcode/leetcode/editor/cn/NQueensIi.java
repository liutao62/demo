package com.liutao62.leetcode.leetcode.editor.cn;

public class NQueensIi {
    public static void main(String[] args) {
        Solution solution = new NQueensIi().new Solution();
        System.out.println(solution.totalNQueens(4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        int count = 0;

        public int totalNQueens(int n) {
            boolean[][] flag = new boolean[n][n];
            backtrack(n, flag, 0);
            return count;
        }

        private void backtrack(int n, boolean[][] flag, int row) {
            if (row == n) {
                count++;
                return;
            }
            for (int i = 0; i < n; i++) {
                if (isValid(flag, row, i)) {
                    flag[row][i] = true;
                    backtrack(n, flag, row + 1);
                    flag[row][i] = false;
                }
            }
        }

        private boolean isValid(boolean[][] board, int i, int j) {
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