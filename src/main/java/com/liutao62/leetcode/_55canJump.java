package com.liutao62.leetcode;

/**
 * @author liutao
 * @date Created in 2021/9/30 15:44
 * @description 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 */
public class _55canJump {
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        boolean[] canJump = new boolean[nums.length - 1];
        int lastIndex = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] >= lastIndex - i) {
                canJump[i] = true;
                lastIndex = i;
            }
        }
        return canJump[0];
    }

    public static void main(String[] args) {
        System.out.println(new _55canJump().canJump(new int[]{3, 2, 1, 0, 4}));
    }
}
