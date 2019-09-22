package com.liutao62.leetcode;

public class _35searchInsert {
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int index = 0;
        while (index < nums.length && target > nums[index]) {
            index++;
        }
        return index;
    }
}
