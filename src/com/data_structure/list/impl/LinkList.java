package com.data_structure.list.impl;

import com.data_structure.list.List;

public class LinkList<T> implements List<T> {

    @Override
    public boolean isEmpty() {
        return this.size() == 0 ? true : false;
    }

    @Override
    public boolean add(T instance) {
        return false;
    }

    @Override
    public boolean remove(int index) {
        return false;
    }

    @Override
    public boolean remove(Object instance) {
        return false;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public boolean clear() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean change(int index, Object instance) {
        return false;
    }
}
