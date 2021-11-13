package com.liutao62.leetcode;


public class _75sortColors {
    public void sortColors(int[] nums) {
        int temp;
        int i1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                temp = nums[i];
                for (i1 = i; i1 > 0 && temp < nums[i1 - 1]; i1--) {
                    nums[i1] = nums[i1 - 1];
                }
                nums[i1] = temp;
            }
        }
      
    }
}
