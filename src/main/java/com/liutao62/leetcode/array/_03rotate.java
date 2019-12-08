package com.liutao62.leetcode.array;

public class _03rotate {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return;
        }
        int tem;
        for (int i = 0; i < k; i++) {
            tem = nums[nums.length - 1];
            System.arraycopy(nums, 0, nums, 1, nums.length - 1);
            nums[0] = tem;
        }
    }
}
