package com.liutao62.leetcode;

public class _152maxProduct {
    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] arr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 && nums[i] != 0){
                arr[i] = nums[i];
            }
            if (i > 0){
                if (arr[i - 1] != 0){

                }
            }
        }
        return 0;
    }
}
