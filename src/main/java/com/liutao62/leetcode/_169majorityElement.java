package com.liutao62.leetcode;

/**
 * https://leetcode-cn.com/problems/majority-element/
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 */
public class _169majorityElement {
    public int majorityElement(int[] nums) {
        int num = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0){
                num = nums[i];
                count = 1;
                continue;
            }
            if (num == nums[i]) count++;
            else count--;
        }
        return num;
    }
}
