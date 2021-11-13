package com.liutao62.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liutao
 * @date Created in 2021/10/25 21:54
 * @description
 */
public class _740deleteAndEarn {
    public int deleteAndEarn(int[] nums) {
        int[] result = {0};

        Arrays.sort(nums);
        Map<Integer, int[]> numCountMap = new HashMap<>();
        for (int num : nums) {
            int[] orDefault = numCountMap.getOrDefault(num, new int[]{0});
            orDefault[0]++;
            numCountMap.put(num, orDefault);
        }

        numCountMap.keySet().stream().sorted((a, b) -> b - a).forEach(num -> {
            int currentCount = numCountMap.getOrDefault(num, new int[]{0})[0] * num;

            int preCount = numCountMap.getOrDefault(num - 1, new int[]{0})[0] * (num - 1);
            int nextCount = numCountMap.getOrDefault(num + 1, new int[]{0})[0] * (num + 1);

            if (currentCount >= preCount + nextCount) {
                result[0] += currentCount;
                numCountMap.remove(num - 1);
                numCountMap.remove(num + 1);
            }
        });
        return result[0];
    }

    public static void main(String[] args) {
        // int i = new _740deleteAndEarn().deleteAndEarn(new int[]{3, 4, 2});
        // int i = new _740deleteAndEarn().deleteAndEarn(new int[]{8, 10, 4, 9, 1, 3, 5, 9, 4, 10});
        int i = new _740deleteAndEarn().deleteAndEarn(new int[]{8, 3, 4, 7, 6, 6, 9, 2, 5, 8, 2, 4, 9, 5, 9, 1, 5, 7, 1, 4});
        System.out.println(i);
    }
}
