package com.liutao62.leetcode;

import java.util.Arrays;

/**
 * @author liutao
 * @date Created in 2021/7/15 23:40
 * @description
 */
public class _31nextPermutation {
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        for (int i = len - 1; i > 0; i--) {
            // 如果有小值
            if (nums[i] > nums[i - 1]) {
                // 较小值后面的做升序排序
                Arrays.sort(nums, i, len);
                for (int j = i; j < len; j++) {
                    // 往后找第一个大于
                    if (nums[j] > nums[i - 1]) {
                        int temp = nums[j];
                        nums[j] = nums[i - 1];
                        nums[i - 1] = temp;
                        return;
                    }
                }
            }
        }
        // 如果没有找到较大的排列，按最小排列
        Arrays.sort(nums);
    }

    public static void main(String[] args) {
        int[] ints = {1, 3, 2};
        new _31nextPermutation().nextPermutation(ints);
        Arrays.stream(ints).forEach(System.out::print);
    }
}
