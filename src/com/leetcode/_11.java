package com.leetcode;

/**
 * @description 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _11 {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int[] opt = new int[height.length - 1];
        int i, i1;
        for (i = 0; i < height.length; i++) {
            for (i1 = i + 1; i1 < height.length; i1++) {
                opt[i] = Math.max(opt[i], Math.min(height[i1], height[i]) * (i1 - i));
            }
        }
        for (i = 0; i < opt.length; i++) {
            maxArea = maxArea > opt[i] ? maxArea : opt[i];
        }
        return maxArea;
    }
}
