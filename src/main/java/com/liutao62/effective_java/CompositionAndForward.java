package com.liutao62.effective_java;

import java.util.*;

/**
 * @author liutao
 * @date Created in 2020/8/21 22:11
 * @description 复合和转发机制（包装）
 */
public class CompositionAndForward {
    public static void main(String[] args) {
        InstrumentesSet<Integer> forward = new InstrumentesSet<>(new HashSet<>());
        MySet<Integer> mySet = new MySet<>();

        forward.add(1);
        mySet.add(1);
        System.out.println(forward.getAddCount());          //  1
        System.out.println(mySet.getAddCount());            //  1

        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);

        forward.addAll(list);
        mySet.addAll(list);

        System.out.println(forward.getAddCount());          //  3
        System.out.println(mySet.getAddCount());            //  5
    }
}

/**
 * @param <E>
 * @description 假设是为了统计添加过的次数（使用过添加多少次）
 */
class InstrumentesSet<E> extends ForwardingSet<E> {
    private int addCount = 0;

    public InstrumentesSet(Set<E> set) {
        super(set);
    }

    public int getAddCount() {
        return this.addCount;
    }

    @Override
    public boolean add(E e) {
        addCount += 1;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }
}

/**
 * @param <E>
 * @description addAll() 是在 add() 上实现的，
 * 这种 自用性 是实现细节，不是承诺，不能保证在 Java 的所有实现中都保持不变（参照开发中使用其他部件情况）
 * 1、删除 addAll 方法或者移除增加 count 的代码并不能从根本上解决问题
 * 2、超类在后续的发行版本中可能实现细节出改变
 */
class MySet<E> extends HashSet<E> {
    private int addCount = 0;

    public int getAddCount() {
        return this.addCount;
    }

    @Override
    public boolean add(E e) {
        addCount += 1;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }
}

class ForwardingSet<E> implements Set<E> {
    private final Set<E> set;

    public ForwardingSet(Set<E> set) {
        this.set = set;
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }

    @Override
    public Object[] toArray() {
        return set.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return set.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return set.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return set.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return set.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return set.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return set.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return set.retainAll(c);
    }

    @Override
    public void clear() {
        set.clear();
    }
}
