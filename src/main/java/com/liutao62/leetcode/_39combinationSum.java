package com.liutao62.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liutao
 * @date Created in 2021/6/19 14:29
 * @description
 */
public class _39combinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(0, candidates.length - 1, target, candidates, new ArrayList<>(), result);
        return result;
    }

    private void dfs(int left, int right, int target, int[] candidates, List<Integer> attribute, List<List<Integer>> result) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<>(attribute));
            return;
        }

        while (left <= right) {
            attribute.add(candidates[left]);

            dfs(left, right, target - candidates[left], candidates, attribute, result);

            attribute.remove(attribute.size() - 1);
            left++;
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new _39combinationSum().combinationSum(new int[]{2, 3, 5}, 8);
        System.out.println(lists);
    }

}
