package com.liutao62.data_structure.queue;

import lombok.NonNull;

import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 * @author liutao
 * @date Created in 2021/7/13 22:48
 * @description
 */
public class PriorityQueue<T> extends AbstractQueue<T> {

    private static final int MAX_SIZE = Integer.MAX_VALUE - 8;
    private static final int DEFAULT_CAPACITY = 11;

    private int size;
    public Object[] queue;
    private Comparator<? super T> comparator;


    public PriorityQueue() {
        queue = new Object[4];
    }

    public PriorityQueue(Comparator<? super T> comparator) {
        this();
        this.comparator = comparator;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean offer(@NonNull T o) {
        if (this.size >= queue.length) {
            grow(this.size + 1);
        }
        int i = size++;
        if (i == 0) {
            queue[0] = o;
        } else {
            siftUp(i, o);
        }
        return true;
    }

    private void siftUp(int i, T o) {
        if (this.comparator != null) {
            siftUpUsingComparator(i, o);
        } else {
            siftUpComparable(i, o);
        }
    }

    private void siftUpComparable(int i, T o) {

    }

    private void siftUpUsingComparator(int i, T o) {
        int parent;
        while (i > 0) {
            parent = i >>> 1;
            T t = (T) queue[parent];
            // 优先级低的放在父节点
            if (comparator.compare(o, t) >= 0) {
                break;
            }
            queue[i] = parent;
            i = parent;
        }
        queue[i] = o;
    }

    private void grow(int i) {
        int oldCapacity = queue.length;
        // Double size if small; else grow by 50%
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                (oldCapacity + 2) :
                (oldCapacity >> 1));
        if (newCapacity > MAX_SIZE) {
            newCapacity = Integer.MAX_VALUE;
        }
        queue = Arrays.copyOf(queue, newCapacity);
    }

    @Override
    public T poll() {
        if (size == 0) {
            return null;
        }
        int last = --size;
        T result = (T) queue[0];
        T o = (T) queue[last];
        queue[last] = null;
        if (last != 0) {
            siftDown(0, o);
        }
        return result;
    }

    private void siftDown(int i, T o) {
        if (this.comparator != null) {
            siftDownUsingComparator(i, o);
        } else {
            siftDownComparable(i, o);
        }
    }

    private void siftDownUsingComparator(int i, T o) {
        int half = size >> 1;
        // 完全二叉树性质决定，使用 half 即可。
        while (half > i) {
            int left = (i << 1) + 1;
            T t = (T) queue[left];
            int right = left + 1;
            // 左右孩子比较，把优先级高的放在右孩子
            if (right < size
                    && comparator.compare(t, (T) queue[right]) > 0) {
                t = (T) queue[left = right];
            }
            // 优先级低的孩子与下沉节点比较
            if (comparator.compare(o, t) <= 0) {
                break;
            }
            // 下沉
            queue[i] = t;
            i = left;
        }
        queue[i] = o;
    }

    private void siftDownComparable(int i, T o) {
    }

    @Override
    public T peek() {
        return size == 0 ? null : (T) queue[0];
    }
}
