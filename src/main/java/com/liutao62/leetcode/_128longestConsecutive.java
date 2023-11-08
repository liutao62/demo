package com.liutao62.leetcode;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author liutao
 * @date 2023/11/8
 */
public class _128longestConsecutive {

    public static void main(String[] args) {
        int[] array = Lists.newArrayList(1, 2, 0, 1).stream().mapToInt(Integer::intValue).toArray();
        int i = new _128longestConsecutive().longestConsecutive(array);
    }

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        nums = Arrays.stream(nums).distinct().sorted().toArray();

        Set<Integer> set = new HashSet<>(100);
        int count = 1;
        int preValue = nums[0];
        for (int i = count; i < nums.length; i++) {
            if (preValue + 1 == (preValue = nums[i])) {
                count++;
            } else {
                set.add(count);
                count = 1;
            }
        }
        set.add(count);
        return set.stream().mapToInt(Integer::intValue).max().orElse(1);
    }
}
