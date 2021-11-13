package com.liutao62.leetcode;

import java.util.Arrays;

/**
 * @author liutao
 * @date Created in 2021/10/25 21:13
 * @description
 */
public class _45jump {
    public int jump(int[] nums) {
        int lastIndex = nums.length - 1;
        if (nums.length == 1) return 0;
        int[] op = new int[lastIndex];

        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] >= lastIndex - i) {
                op[i] = 1;
            } else if (nums[i] > 0) {
                int step = Arrays.stream(Arrays.copyOfRange(op, i + 1, nums[i] + i + 1)).filter(s -> s != 0).min().orElse(-1);
                op[i] = step + 1;
            }
        }
        return op[0];
    }

    public static void main(String[] args) {
        // int jump = new _45jump().jump(new int[]{2, 3, 1, 1, 4});
        int jump = new _45jump().jump(new int[]{2, 3, 0, 1, 4});
        System.out.println(jump);
    }
}
