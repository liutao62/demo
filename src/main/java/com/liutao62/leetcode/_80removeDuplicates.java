package com.liutao62.leetcode;

public class _80removeDuplicates {
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        int currentNum = nums[0];
        int count = 1;
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            if (currentNum == nums[i]) {
                count++;
            } else {
                currentNum = nums[i];
                count = 1;
            }
            if (count > 2) {
                System.arraycopy(nums, i + 1, nums, i--, --len - i - 1);
            }
        }
        return len;
    }
}
