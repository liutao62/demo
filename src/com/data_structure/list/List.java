package com.data_structure.list;

public interface List<T> {
    boolean isEmpty();
    boolean add(T instance);
    boolean remove(int index);
    boolean remove(T instance);
    T get(int index);
    boolean clear();
    int size();
    boolean change(int index,T instance);
}
