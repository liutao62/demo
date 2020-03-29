package com.liutao62.leetcode;

public class _81search {
    public static boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int low = 0, high = nums.length - 1, mid;
        while (low <= high) {
            mid = (high + low) >> 1;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[low] == nums[mid]) {
                low++;
                continue;
            }
            // 前半部分有序
            if (nums[mid] > nums[low]) {
                if (target < nums[mid] && target >= nums[low]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (target <= nums[high] && target > nums[mid]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return false;
    }
}
