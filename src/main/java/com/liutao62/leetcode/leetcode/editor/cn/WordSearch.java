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
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        String target = "ABCB";
//        System.out.println(solution.exist(board, target));

        char[][] b = {{'a'}};
        String w = "a";
        solution.exist(b, w);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean exist(char[][] board, String word) {
            boolean[][] visited = new boolean[board.length][board[0].length];
            for (int i = 0; i < board.length; i++) {
                for (int i1 = 0; i1 < board[i].length; i1++) {
                    if (board[i][i1] == word.charAt(0)) {
                        if (backtrack(board, word, i, i1, 0, word.length(), visited)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public boolean backtrack(char[][] board, String word, int i, int j, int index, int len, boolean[][] visited) {
            if (index == len) {
                return true;
            }
            if (i >= board.length || j >= board[0].length || i < 0 || j < 0) {
                return false;
            }
            if (visited[i][j] || word.charAt(index) != board[i][j]) {
                return false;
            }
            visited[i][j] = true;
            boolean exist = backtrack(board, word, i + 1, j, index + 1, len, visited)
                    || backtrack(board, word, i, j + 1, index + 1, len, visited)
                    || backtrack(board, word, i, j - 1, index + 1, len, visited)
                    || backtrack(board, word, i - 1, j, index + 1, len, visited);
            visited[i][j] = false;
            return exist;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
