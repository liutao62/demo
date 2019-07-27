package com.aims_offer;

/**
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 */
public class _28_GetLeastNumbers {
    public java.util.ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        java.util.Arrays.sort(input);
        java.util.ArrayList<Integer> list = new java.util.ArrayList(k);
        if (input.length < k) return list;
        for (int i = 0; i < k; i++) {
            list.add(input[i]);
        }
        return list;
    }
}
