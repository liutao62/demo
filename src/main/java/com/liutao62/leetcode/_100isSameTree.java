package com.liutao62.leetcode;

public class _100isSameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q != null) return false;
        if (q == null && p != null) return false;
        if (p != null && q != null) {
            if (p.val == q.val){
                return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
            }
            return false;
        }
        return true;
    }

}
