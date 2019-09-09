package com.liutao62.aims_offer;

/**
 * {6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。给一个数组，
 * 返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
 */
public class _29_FindGreatestSumOfSubArray {
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array.length == 0) return 0;
        if (array.length == 1) return array[0];
        int[] opt = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            if (i == array.length - 1) {
                opt[i] = array[i];
            } else {
                if (opt[i + 1] > 0) {
                    opt[i] = array[i] + opt[i + 1];
                } else {
                    opt[i] = array[i];
                }
            }
        }
        int max = -2147483648;
        for (int i : opt) {
            if (i > max) max = i;
        }
        return max;
    }
}
