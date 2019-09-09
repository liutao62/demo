package com.liutao62.data_structure.tree;

import com.liutao62.data_structure.tree.impl.MyTreeNode;

public class BinarySortTree {
    public MyTreeNode buildSortTree(int[] array) {
        if (array == null || array.length == 0) return null;
        MyTreeNode<Integer> node = new MyTreeNode(array[0]);
        MyTreeNode<Integer> root = node;
        for (int i = 1; i < array.length; i++) {
            buildSuppot(root, array[i]);
        }
        return root;
    }

    public boolean buildSuppot(MyTreeNode<Integer> root, int num) {
        if (num < root.val) {
            if (root.left != null) {
                buildSuppot(root.left, num);
            } else {
                root.left = new MyTreeNode(num);
                return true;
            }
        } else {
            if (root.right != null) {
                buildSuppot(root.right, num);
            } else {
                root.right = new MyTreeNode(num);
                return true;
            }
        }
        return false;
    }
}
