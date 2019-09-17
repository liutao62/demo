package com.liutao62.leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * 你的算法时间复杂度必须是 O(log n) 级别。
 */
public class _34searchRange {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int index = Arrays.binarySearch(nums, target);
        if (index < 0) return new int[]{-1, -1};
        int i = index, j = index;

        while (i >= 0 && nums[i] == target)
            i--;
        while (j <= nums.length - 1 && nums[j] == target)
            j++;
        if (i != index && j != index) return new int[]{i + 1, j - 1};
        if (i != index) return new int[]{i + 1, j};
        if (j != index) return new int[]{i, j - 1};
        return new int[]{i, j};

    }
}
