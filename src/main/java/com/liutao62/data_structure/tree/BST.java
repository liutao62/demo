package com.liutao62.data_structure.tree;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int N;              // 以该节点为根的子树结点总数

        public Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    /**
     * @return
     * @description 获得总结点数
     */
    public int size() {
        return size(root);
    }

    /**
     * @param root
     * @return
     * @description 获取以 root 结点为根的子树总结点数
     */
    private int size(Node root) {
        if (root == null) return 0;
        return root.N;
    }

    /**
     * @param key
     * @return
     * @description 对外暴露的通过 key 获取 value 的方法
     */
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp > 0) return get(root.right, key);
        else if (cmp < 0) return get(root.left, key);
        else return root.value;
    }

    /**
     * @param key
     * @param value
     * @description 对外暴露的 put 方法
     */
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node root, Key key, Value value) {
        if (root == null) return new Node(key, value, 1);
        int cmp = key.compareTo(root.key);
        if (cmp < 0) root.left = put(root.left, key, value);
        else if (cmp > 0) root.right = put(root.right, key, value);
        else root.value = value;
        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;
            Node temp = node;
            node = min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * @description 删除最小的结点
     */
    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * @return
     * @description 获得最小的 key
     */
    public Key min() {
        return min(root).key;
    }

    /**
     * @param node
     * @return
     * @description 返回最小的结点
     */
    private Node min(Node node) {
        if (node.left == null) return node;
        return min(node.left);
    }
}
