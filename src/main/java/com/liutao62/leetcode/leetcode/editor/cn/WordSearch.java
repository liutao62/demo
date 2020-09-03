package com.liutao62.leetcode.leetcode.editor.cn;

//给定一个二维网格和一个单词，找出该单词是否存在于网格中。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 示例: 
//
// board =
//[
//  ['A','B','C','E'],
//  ['S','F','C','S'],
//  ['A','D','E','E']
//]
//
//给定 word = 'ABCCED', 返回 true
//给定 word = 'SEE', 返回 true
//给定 word = 'ABCB', 返回 false 
//
// 
//
// 提示： 
//
// 
// board 和 word 中只包含大写和小写英文字母。 
// 1 <= board.length <= 200 
// 1 <= board[i].length <= 200 
// 1 <= word.length <= 10^3 
// 
// Related Topics 数组 回溯算法 
// 👍 543 👎 0


public class WordSearch {
    public static void main(String[] args) {
        Solution solution = new WordSearch().new Solution();
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'D'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String target = "ABCCED";
        System.out.println(solution.exist(board, target));

        char[][] b = {{'c', 'a', 'a'}, {'a', 'a', 'a'}, {'b', 'c', 'd'}};
        String w = "aab";
        solution.exist(b, w);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean exist(char[][] board, String word) {
            if (word == null || word.length() == 0) {
                return true;
            }
            if (board == null || board.length == 0 || board[0].length == 0) {
                return false;
            }
            char[] chars = word.toCharArray();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (existHelp(board, chars, i, j, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean existHelp(char[][] board, char[] word, int i, int j, int len) {
            if (len == word.length) {
                return true;
            }
            if (i < 0 || i == board.length || j < 0 || j == board[i].length || board[i][j] != word[len]) {
                return false;
            }
            // 因为只包含字母，用 . 过滤重复使用
            board[i][j] = '.';
            return existHelp(board, word, i + 1, j, len + 1) || existHelp(board, word, i - 1, j, len + 1)
                    || existHelp(board, word, i, j - 1, len + 1) || existHelp(board, word, i, j + 1, len + 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
