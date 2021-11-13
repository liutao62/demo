package com.liutao62.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liutao
 * @date Created in 2021/7/28 22:41
 * @description
 */
public class _114flatten {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        List<TreeNode> list = new ArrayList<>();
        preTraverse(root, list);
        TreeNode tmp = root;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) != null) {
                TreeNode treeNode = list.get(i);
                treeNode.right = null;
                treeNode.left = null;
                tmp.right = treeNode;
                tmp.left = null;
                tmp = tmp.right;

            }
        }
    }

    private void preTraverse(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            preTraverse(root.left, list);
            preTraverse(root.right, list);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1)
                .setLeft(new TreeNode(2)
                        .setLeft(new TreeNode(3))
                        .setRight(new TreeNode(4)))
                .setRight(new TreeNode(5)
                        .setRight(new TreeNode(6)));
        new _114flatten().flatten(root);

        System.out.println(root);
    }
}
