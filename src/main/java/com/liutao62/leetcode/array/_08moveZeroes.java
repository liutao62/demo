package com.liutao62.leetcode.array;

public class _08moveZeroes {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length < 1) {
            return;
        }
        int len = nums.length;
        for (int i = 0; i < len; ) {
            if (nums[i] == 0) {
                System.arraycopy(nums, i + 1, nums, i, len - i - 1);
                nums[--len] = 0;
            } else i++;
        }
    }
}
