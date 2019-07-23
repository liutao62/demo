package com.aims_offer;

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}

/**
 * 输入两棵二叉树A，B，判断B是不是A的子结构。
 * （ps：我们约定空树不是任意一个树的子结构）
 */
public class HasSubtree {
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root2 == null || root1 == null) return false;
        return isSubtree(root1,root2) || isSubtree(root1.left,root2)
                || isSubtree(root2.right,root2);
    }

    public boolean isSubtree(TreeNode root1, TreeNode root2) {
        if (root2 == null) return true;
        if (root1 == null) return false;
        if (root1.val == root2.val) {
            return isSubtree(root1.left,root2.left) && isSubtree(root1.right,root2.right);
        }
        return isSubtree(root1.left,root2) || isSubtree(root1.right,root2);
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(7);
        root1.left.left.left = new TreeNode(8);
        root1.left.left.right = new TreeNode(9);

        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(6);

        System.out.println(new HasSubtree().HasSubtree(root1,root2));
    }
}
