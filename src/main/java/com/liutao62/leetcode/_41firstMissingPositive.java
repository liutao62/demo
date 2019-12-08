package com.liutao62.leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/first-missing-positive/
 */
public class _41firstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        int last = 0;
        for (int num : nums) {
            if (num > last){
                if (num - last == 1) {
                    last = num;
                }else return last + 1;
            }
        }
        return last + 1;
    }
}
