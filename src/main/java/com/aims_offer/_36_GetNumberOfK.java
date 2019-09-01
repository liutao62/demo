package com.aims_offer;

/**
 * 统计一个数字在排序数组中出现的次数。
 */
public class _36_GetNumberOfK {
    public int GetNumberOfK(int[] array, int k) {
        if (array == null || array.length == 0) return 0;
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (k == array[i]) count++;
        }
        return count;
    }
}
