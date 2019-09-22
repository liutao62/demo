package com.liutao62.data_structure.tree;

/**
 * @description 仅用于操作 Integer 类型 Key 的无 Value 的二叉排序树
 */
public class BinarySortTree extends BST<Integer, Object> {

    private static final Object DEFAULT_VALUE = new Object();

    @Override
    public int size() {
        return super.size();
    }

    public void put(Integer key) {
        super.put(key, DEFAULT_VALUE);
    }

    @Override
    public void delete(Integer key) {
        super.delete(key);
    }

    @Override
    public void deleteMin() {
        super.deleteMin();
    }

    @Override
    public Integer min() {
        return super.min();
    }
}
