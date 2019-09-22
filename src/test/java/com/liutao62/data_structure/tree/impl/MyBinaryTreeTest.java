package com.liutao62.data_structure.tree.impl;

import org.junit.Before;
import org.junit.Test;

public class MyBinaryTreeTest {
    private MyBinaryTree<Integer> root = new MyBinaryTree<>(1);


    @Before
    public void before() {
        root.left = new MyBinaryTree(2);
        root.right = new MyBinaryTree(3);
        MyBinaryTree temp = root.left;
        temp.right = new MyBinaryTree(4);
        temp = root.right;
        temp.left = new MyBinaryTree(5);
        temp.right = new MyBinaryTree(6);
        temp.left.left = new MyBinaryTree(7);
    }

    @Test
    public void showTree() {

    }

    @Test
    public void preOrder() {
        root.preOrder();
    }

    @Test
    public void levelOrder() {
        root.levelOrder();
    }

    @Test
    public void leafNum() {
        assert root.leafNum() == 3;
    }

    @Test
    public void nodeNum() {
        assert root.nodeNum() == 7;
    }

    @Test
    public void treeDepth() {
        assert root.treeDepth() == 4;
    }

    @Test
    public void buildTree() {
        int[] pre = new int[]{1,2,4,8,5,3,6,9,10,7};
        int[] in = new int[]{4,8,2,5,1,9,6,10,3,7};
        MyBinaryTree<Integer> tree = MyBinaryTree.buildTree(pre, in);
        tree.preOrder();
        tree.levelOrder();
    }
}