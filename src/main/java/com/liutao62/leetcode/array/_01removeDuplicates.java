package com.liutao62.leetcode.array;

public class _01removeDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int lastNum = nums[0];
        int len = nums.length;
        for (int i = 1; i < len; ) {
            if (nums[i] == lastNum) {
                System.arraycopy(nums, i + 1, nums, i, len - i - 1);
                len--;
            } else {
                lastNum = nums[i];
                i++;
            }
        }
        return len;
    }
}
