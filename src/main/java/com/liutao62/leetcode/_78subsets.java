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
    public boolean exist(char[][] board, String word) {
        return dfs(board, word, "", 0, 0);
    }

    private boolean dfs(char[][] board, String target, String path, int i, int j) {
        if (target.equals(path)) {
            return true;
        }
        if (i < 0 || j < 0 || i > board.length - 1 || j > board[i].length - 1) {
            return false;
        }
        return dfs(board, target, path + board[i][j], i + 1, j)
                || dfs(board, target, path + board[i][j], i, j + 1)
                || dfs(board, target, path + board[i][j], i - 1, j)
                || dfs(board, target, path + board[i][j], i, j - 1);

    }
    public static void main(String[] args) {
        List<List<Integer>> subsets = new _78subsets().subsets(new int[]{1, 2, 3});
        System.out.println(subsets);
    }
}
