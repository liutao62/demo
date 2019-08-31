package com.data_structure.list;

public interface MyList<T> {
    boolean isEmpty();
    boolean add(T value);
    boolean remove(int index);
    boolean remove(T value);
    T get(int index);
    boolean clear();
    int size();
    boolean change(int index,T value);
}
