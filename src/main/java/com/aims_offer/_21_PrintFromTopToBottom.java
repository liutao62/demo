package com.aims_offer;

import java.util.ArrayList;

/**
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印
 */
public class _21_PrintFromTopToBottom {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) return list;
        ArrayList<TreeNode> nodes = printFromTopToBottom(root);
        for (TreeNode node : nodes) {
            list.add(node.val);
        }
        return list;
    }

    public ArrayList<TreeNode> printFromTopToBottom(TreeNode root) {
        ArrayList<TreeNode> list = new ArrayList();
        if (root != null) list.add(root);
        boolean flag = true;
        int index = 0;
        while (flag) {
            flag = false;
            TreeNode temp;
            for (int i = index; i < list.size(); i++) {
                temp = list.get(i);
                if (temp.left != null) {
                    list.add(temp.left);
                    flag = true;
                }
                if (temp.right != null) {
                    list.add(temp.right);
                    temp.right = null;
                    flag = true;
                }
            }
            index = list.size() - 1;
        }
        return list;
    }
}
