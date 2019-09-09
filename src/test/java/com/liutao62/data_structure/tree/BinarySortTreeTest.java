package com.liutao62.data_structure.tree;

import com.liutao62.data_structure.tree.impl.MyTreeNode;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySortTreeTest {

    private BinarySortTree test = new BinarySortTree();

    @Test
    public void buildSortTree() {
        MyTreeNode treeNode = test.buildSortTree(new int[]{62, 15, 12, 68, 65, 79, 46, 35, 57});
        treeNode.preOrder();
        treeNode.inOrder();
        treeNode.levelOrder1();
    }
}