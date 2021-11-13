package com.liutao62.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liutao
 * @date Created in 2021/9/30 16:20
 * @description
 */
public class _56merge {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 1) return intervals;

        Arrays.sort(intervals, (arr1, arr2) -> arr1[0] - arr2[0]);

        List<int[]> result = new ArrayList<>(intervals.length);

        for (int i = 0; i < intervals.length; i++) {
            if (result.size() == 0) {
                result.add(intervals[i]);
            } else if (result.get(result.size() - 1)[1] >= intervals[i][1]) {

            } else if (result.get(result.size() - 1)[1] >= intervals[i][0]) {
                result.get(result.size() - 1)[1] = intervals[i][1];
            } else {
                result.add(intervals[i]);
            }
        }
        return result.toArray(new int[0][]);
    }

    public static void main(String[] args) throws Exception {
        // int[][] merge = new _56merge().merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        int[][] merge = new _56merge().merge(new int[][]{{1, 4}, {0, 2}, {3, 6}});
        // int[][] merge = new _56merge().merge(new int[][]{{1, 4}, {4, 5}});
        for (int[] ints : merge) {
            for (int anInt : ints) {
                System.out.print(anInt);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
