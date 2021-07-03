package com.liutao62.leetcode;

/**
 * @author liutao
 * @date Created in 2021/7/3 16:22
 * @description
 */
public class _101isSymmetric {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return check(root.right, root.left);
    }

    private boolean check(TreeNode right, TreeNode left) {
        if ((right == null && left != null) || (right != null && left == null)) {
            return false;
        } else if (right == null && left == null) {
            return true;
        } else if (right.val != left.val) {
            return false;
        }
        return check(right.right, left.left) && check(right.left, left.right);
    }
}
