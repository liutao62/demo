package com.liutao62.data_structure.tree.impl;

import com.liutao62.data_structure.tree.MyTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MyBinaryTree<T> implements MyTree<T> {

    public MyBinaryTree left;
    public MyBinaryTree right;
    public T val;

    public MyBinaryTree(T val) {
        this.val = val;
    }

    public MyBinaryTree() {
    }

    public static MyBinaryTree<Integer> buildTree(int[] pre, int[] in) {
        if (pre == null || in == null) return null;
        if (pre.length == 0 || in.length == 0) return null;
        if (pre.length != in.length) return null;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        return buildTreeHelper(pre, 0, pre.length - 1, 0, map);
    }

    private static MyBinaryTree<Integer> buildTreeHelper(int[] pre, int preStart, int preEnd, int inStart, Map<Integer, Integer> map) {
        if (preStart > preEnd) return null;
        Integer rootIdx = map.get(pre[preStart]);
        int leftLength = rootIdx - inStart;
        MyBinaryTree<Integer> root = new MyBinaryTree<>(pre[preStart]);
        root.left = buildTreeHelper(pre, preStart + 1, preStart + leftLength, inStart, map);
        root.right = buildTreeHelper(pre, preStart + leftLength + 1, preEnd, rootIdx + 1, map);
        return root;
    }


    @Override
    public void ShowTree() {

    }

    @Override
    public void preOrder() {
        preOrderHelper(this);
        System.out.println();
    }

    public void inOrder(){
        inOrderHelper(this);
    }

    private void inOrderHelper(MyBinaryTree<T> node) {
        if (node != null) {
            preOrderHelper(node.left);
            System.out.print(node.val + " ");
            preOrderHelper(node.right);
        }
    }

    private void preOrderHelper(MyBinaryTree node) {
        if (node != null) {
            System.out.print(node.val + " ");
            preOrderHelper(node.left);
            preOrderHelper(node.right);
        }
    }

    /**
     * 层次遍历
     */
    @Override
    public void levelOrder() {
        LinkedList<MyBinaryTree> list = new LinkedList<>();
        list.add(this);

        while (list.size() > 0) {
            MyBinaryTree peek = list.peek();
            if (peek != null) {
                list.pop();
                System.out.print(peek.val + " ");
                if (peek.left != null)
                    list.add(peek.left);
                if (peek.right != null)
                    list.add(peek.right);
            }
        }
    }
    /**
     * 层次遍历(分层打印)
     */
    public void levelOrder1() {

    }

    /**
     * 求二叉树叶子节点数目
     *
     * @return
     */
    @Override
    public int leafNum() {
        int count = 0;
        if (this != null) {
            if (this.right == null && this.left == null)
                count++;
            if (this.left != null)
                count += this.left.leafNum();
            if (this.right != null)
                count += this.right.leafNum();
        }
        return count;
    }

    /**
     * 二叉树结点总数目
     *
     * @return
     */
    @Override
    public int nodeNum() {
        int count = 0;
        if (this != null) {
            count++;
            if (this.left != null) count += this.left.nodeNum();
            if (this.right != null) count += this.right.nodeNum();
        }
        return count;
    }

    /**
     * 二叉树深度
     *
     * @return
     */
    @Override
    public int treeDepth() {
        int ldep = 0, rdep = 0;
        if (this == null)
            return 0;
        else {
            if (this.left != null)
                ldep = this.left.treeDepth();
            if (this.right != null)
                rdep = this.right.treeDepth();
        }
        return ldep > rdep ? ldep + 1 : rdep + 1;
    }
}
