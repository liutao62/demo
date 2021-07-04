package com.liutao62.leetcode;

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
        int ans = nums[0];
        int sum = 0;
        for (int num : nums) {
            // 如果大于 0 可以继续尝试
            if (sum > 0) {
                sum += num;
            } else {
                // 如果小于 0 说明上一个 区间的计算可以舍弃了，重新开始
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        int i = new _53maxSubArray().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println(i);
    }
}
