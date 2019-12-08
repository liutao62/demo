package com.liutao62.leetcode.array;

public class _11rotate {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length < 2) {
            return;
        }
        int len = matrix.length;
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int i1 = i; i1 < len - i - 1; i1++) {
                swap(i, i1, matrix);
            }
        }
    }

    private void swap(int i, int j, int[][] matrix) {
        int maxIndex = matrix.length - 1;
        int val = matrix[j][i];
        matrix[j][i] = matrix[maxIndex - i][j];
        matrix[maxIndex - i][j] = matrix[maxIndex - j][maxIndex - i];
        matrix[maxIndex - j][maxIndex - i] = matrix[i][maxIndex - j];
        matrix[i][maxIndex - j] = val;
    }
}
