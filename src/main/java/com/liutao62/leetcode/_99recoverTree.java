package com.liutao62.leetcode;

public class _99recoverTree {
    public void recoverTree(TreeNode root) {
        if (root == null) return;
        int val = root.val;
    }

    private TreeNode leftNode(int val, TreeNode root) {
        if (root != null) {
            if (root.val < val) {
                leftNode(val, root.left);
                leftNode(val, root.right);
            } else return root;
        }
        return null;
    }
    private TreeNode rightNode(int val, TreeNode root) {
        if (root != null) {
            if (root.val > val) {
                rightNode(val, root.left);
                rightNode(val, root.right);
            } else return root;
        }
        return null;
    }

}
