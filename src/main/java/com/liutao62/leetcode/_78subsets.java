package com.liutao62.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _78subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, 0, nums.length, new ArrayList<>(), nums);
        return result;
    }

    private void dfs(List<List<Integer>> result, int left, int right, List<Integer> path, int[] nums) {
        if (left == right) {
            result.add(path);
            return;
        }
        // 不选当前数
        dfs(result, left + 1, right, new ArrayList<>(path), nums);
        // 选择当前数
        path.add(nums[left]);
        dfs(result, left + 1, right, new ArrayList<>(path), nums);
    }

    public static void main(String[] args) {
        List<List<Integer>> subsets = new _78subsets().subsets(new int[]{1, 2, 3});
        System.out.println(subsets);
    }
}
