package com.liutao62.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liutao
 * @date Created in 2020/6/20 22:06
 * @description
 */
public class _53maxSubArray {
    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        List list = new ArrayList();
        if (nums == null || nums.length == 0) return 0;
        int[] maxValue = new int[nums.length];
        int max;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            max = 0;
            j = 0;
            for (; j <= i; j++) {
                max += nums[i];
            }
            maxValue[j - 1] = max < maxValue[j - 1] ? maxValue[j - 1] : max;
        }
        max = Integer.MIN_VALUE;
        for (int i = 0; i < maxValue.length; i++) {
            max = max < maxValue[i] ? maxValue[i] : max;
        }
        return max;
    }
}
