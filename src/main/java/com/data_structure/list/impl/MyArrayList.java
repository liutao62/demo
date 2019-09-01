package com.data_structure.list.impl;

import com.data_structure.list.MyList;

public class MyArrayList<T> implements MyList<T> {
    private Object[] array;
    private int size;

    private static final int DEFAULT_INITIALIZE_SIZE = 10;
    // 涉及到过多的移动，太大反而浪费时间，所以设置
    private static final int MAX_SIZE = 102400;

    public MyArrayList(int size) {
        if (size < 0)
            throw new IllegalArgumentException("by " + size);
        if (size > 20) {
            size = DEFAULT_INITIALIZE_SIZE;
        }
        this.array = new Object[size];
    }

    public MyArrayList() {
        this.array = new Object[DEFAULT_INITIALIZE_SIZE];
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean add(T value) {
        if (value == null) return false;
        grow(size + 1);
        if (size == MAX_SIZE) return false;
        array[size++] = value;
        return true;
    }

    private void grow(int size) {
        if (size > array.length) {
            int newLen = array.length + array.length >> 1;
            newLen = newLen > DEFAULT_INITIALIZE_SIZE ? newLen : DEFAULT_INITIALIZE_SIZE;
            newLen = newLen > MAX_SIZE ? MAX_SIZE : newLen;
            Object[] newArray = new Object[newLen];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    @Override
    public boolean remove(int index) {
        if (index < 0 || index > size - 1)
            throw new ArrayIndexOutOfBoundsException("by " + index);
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return true;
    }

    @Override
    public boolean remove(Object value) {
        int i = 0;
        for (; i < size; i++) {
            Object obj = array[i];
            if (obj == value ||
                    obj.equals(value) && obj.hashCode() == value.hashCode()) {
                System.arraycopy(array, i + 1, array, i, size - i - 1);
                array[--size] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public T get(int index) {
        return (T) array[index];
    }

    @Override
    public boolean clear() {
        array = null;
        size = 0;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean change(int index, T value) {
        if (index < 0 || index > size - 1)
            return false;
        array[index] = value;
        return true;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i] + ",");
        }
        if (sb.length() > 1)
            sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
}
