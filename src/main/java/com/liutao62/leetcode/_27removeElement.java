package com.liutao62.leetcode;

/**
 * https://leetcode-cn.com/problems/remove-element/
 */
public class _27removeElement {
    public int removeElement(int[] nums, int val) {
        int count = 0, begin = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                count++;
                nums[begin++] = nums[i];
            }
        }
        return count;
    }
}
