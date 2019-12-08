package com.liutao62.aims_offer;

import java.util.LinkedList;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 */
public class _25Convert {
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return null;
        LinkedList<TreeNode> list = new LinkedList<>();

        inOrder(pRootOfTree,list);
        TreeNode head = list.poll();
        TreeNode cur = head;
        while (list.size() > 0){
            TreeNode temp = list.poll();
            cur.right = temp;
            temp.left = cur;
            cur = temp;
        }
        return head;
    }

    private void inOrder(TreeNode node, LinkedList<TreeNode> list) {
        if (node != null) {
            inOrder(node.left, list);
            list.add(node);
            inOrder(node.right, list);
        }
    }

    private TreeNode min(TreeNode node) {
        if (node.left != null) min(node.left);
        return node;
    }

    private TreeNode max(TreeNode node) {
        if (node.right != null) min(node.right);
        return node;
    }
}
