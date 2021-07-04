package com.liutao62.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liutao
 * @date Created in 2021/7/4 15:17
 * @description
 */
public class _46permute {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, 0, new boolean[nums.length], new ArrayList<>(), result);
        return result;
    }

    private void dfs(int[] nums, int path, boolean[] used, ArrayList<Integer> attribute, List<List<Integer>> result) {
        if (path == nums.length) {
            result.add(new ArrayList<>(attribute));
            return;
        }
        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                attribute.add(nums[i]);
                used[i] = true;

                dfs(nums, path + 1, used, attribute, result);

                used[i] = false;
                attribute.remove(attribute.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> permute = new _46permute().permute(new int[]{1, 2, 3});
        System.out.println(permute);
    }

}
