package com.data_structure.tree.impl;

import org.junit.Before;
import org.junit.Test;

public class MyTreeNodeTest {
    private MyTreeNode<Integer> root = new MyTreeNode<>(1);


    @Before
    public void before() {
        root.left = new MyTreeNode(2);
        root.right = new MyTreeNode(3);
        MyTreeNode temp = root.left;
        temp.right = new MyTreeNode(4);
        temp = root.right;
        temp.left = new MyTreeNode(5);
        temp.right = new MyTreeNode(6);
        temp.left.left = new MyTreeNode(7);
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
        MyTreeNode<Integer> tree = MyTreeNode.buildTree(pre, in);
        tree.preOrder();
        tree.levelOrder();
    }
}