package com.liutao62.data_structure.tree;

public interface MyTree<T> {

    void ShowTree();

    void preOrder();

    void levelOrder();

    int leafNum();

    int nodeNum();

    int treeDepth();
}
