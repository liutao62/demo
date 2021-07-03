package com.liutao62.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author liutao
 * @date Created in 2021/7/3 16:05
 * @description
 */
public class _104maxDepth {
    public int maxDepth(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, 0, result);
        return result.stream().max(Comparator.comparingInt(v -> v)).get();
    }

    private void dfs(TreeNode root, int i, List<Integer> result) {
        if (root == null) {
            result.add(i);
            return;
        }
        dfs(root.left, i + 1, result);
        dfs(root.right, i + 1, result);
    }

    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

}
