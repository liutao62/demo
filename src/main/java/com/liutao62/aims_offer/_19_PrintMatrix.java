package com.liutao62.aims_offer;

import java.util.ArrayList;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */
public class _19_PrintMatrix {
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return list;
        int m = matrix.length, n = matrix[0].length;
        if (n == 0) return list;
        int times = (Math.min(m, n) - 1) / 2;
        int i, j, k, l;
        for (int count = 0; count <= times; count++) {
            for (i = count; i < n - count; i++)
                list.add(matrix[count][i]);
            for (j = count + 1; j < m - count; j++)
                list.add(matrix[j][i - 1]);
            for (k = i - 2; k >= count && (j - 1 != count); k--)
                list.add(matrix[j - 1][k]);
            for (l = j - 2; l > count && (i - 1 != count); l--)
                list.add(matrix[l][k + 1]);
        }
        return list;
    }
}
