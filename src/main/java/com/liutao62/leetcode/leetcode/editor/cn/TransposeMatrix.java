package com.liutao62.leetcode.leetcode.editor.cn;

//给定一个矩阵 A， 返回 A 的转置矩阵。 
//
// 矩阵的转置是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。 
//
// 
//
// 示例 1： 
//
// 输入：[[1,2,3],[4,5,6],[7,8,9]]
//输出：[[1,4,7],[2,5,8],[3,6,9]]
// 
//
// 示例 2： 
//
// 输入：[[1,2,3],[4,5,6]]
//输出：[[1,4],[2,5],[3,6]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 1000 
// 1 <= A[0].length <= 1000 
// 
// Related Topics 数组 
// 👍 112 👎 0
public class TransposeMatrix {
    public static void main(String[] args) {
        Solution solution = new TransposeMatrix().new Solution();
        int[][] transpose = solution.transpose(new int[][]{{1, 2, 3}, {4, 5, 6}});
        for (int i = 0; i < transpose.length; i++) {
            for (int j = 0; j < transpose[i].length; j++) {
                System.out.print(transpose[i][j]);
            }
            System.out.println();
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] transpose(int[][] A) {
            int[][] transpose = new int[A[0].length][A.length];
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[i].length; j++) {
                    System.out.print(A[i][j]);
                    transpose[j][i] = A[i][j];
                }
                System.out.println();
            }
            return transpose;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
