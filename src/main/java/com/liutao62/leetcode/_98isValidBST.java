package com.liutao62.leetcode;

import com.liutao62.data_structure.tree.TreeNode;

public class _98isValidBST {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (root.left != null){
            if (root.left.val < root.val){
                isValidBST(root.left);
            }else return false;
        }
        if (root.right != null){
            if (root.right.val > root.val){
                isValidBST(root.right);
            }else return false;
        }
        return true;
    }
}
