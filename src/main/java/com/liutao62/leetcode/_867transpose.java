package com.liutao62.leetcode;

/**
 * @author liutao
 * @date Created in 2021/2/8 12:26
 * @description
 */
public class _867transpose {
    public int[][] transpose(int[][] A) {
        int[][] array = new int[A[0].length][A.length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                array[j][i] = A[i][j];
            }
        }
        return array;
    }
}
