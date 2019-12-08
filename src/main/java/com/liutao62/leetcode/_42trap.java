package com.liutao62.leetcode;

/**
 * https://leetcode-cn.com/problems/trapping-rain-water/
 */
public class _42trap {
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int sum = 0;
        int lastHeight = height[0];
        int lastIndex = 0;
        for (int i = 1; i < height.length; i++) {
            if (height[i] != 0)




            if (height[i] != 0) {
                sum += area((i - lastIndex - 1) * (lastHeight - height[i] - 1));
                lastHeight = height[i];
                lastIndex = i;
            }
        }
        return sum;
    }

    private int area(int a) {
        return a > 0 ? a : -a;
    }
}
