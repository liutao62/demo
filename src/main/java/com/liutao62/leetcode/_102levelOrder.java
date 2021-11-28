package com.liutao62.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class _102levelOrder {

    // 超出内存限制
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        List<TreeNode> tmp = new ArrayList<>();
        tmp.add(root);
        int begin = 0;
        int size = tmp.size();
        boolean hasChild = false;
        for (int i = 0; i <= begin; i++) {
            for (int j = begin; j < size; j++) {
                TreeNode node = tmp.get(j);
                if (node == null) {
                    tmp.add(null);
                    tmp.add(null);
                } else {
                    TreeNode left = node.left;
                    TreeNode right = node.right;
                    tmp.add(left);
                    tmp.add(right);
                    hasChild = left != null || right != null || hasChild;
                }
            }
            if (hasChild) {
                hasChild = false;
                begin = (begin << 1) + 1;
                size = (size << 1) + 1;
            }
        }

        int level = (tmp.size() + 1) >> 1;

        for (int i = 0; i < level; ) {
            List<Integer> levelResult = tmp.subList(i, (i << 1) + 1).stream().filter(treeNode -> treeNode != null)
                    .map(treeNode -> treeNode.val).collect(Collectors.toList());
            if (levelResult != null && levelResult.size() != 0) {

                result.add(levelResult);
            }
            i = (i << 1) + 1;
        }
        return result;
    }
}
