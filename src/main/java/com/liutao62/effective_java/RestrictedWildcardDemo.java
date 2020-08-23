package com.liutao62.effective_java;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liutao
 * @date Created in 2020/8/23 15:36
 * @description
 */
public class RestrictedWildcardDemo<E> {
    public static void main(String[] args) {
        Stack<Number> stack = new Stack<>();
        Iterable<Integer> integers = Arrays.asList(new Integer[]{1, 2, 3, 4});
        // 版本一：无限制泛型编译报错，此处应该改为 E 的某种子类的集合
        stack.pushAll(integers);
        System.out.println(stack);

        // 版本一：泛型限制只能为 E 的集合，此处应该改为 E 的某种超类的集合
        stack.popAll(new ArrayList<Object>());
        System.out.println(stack);

        Set<Integer> s1 = Arrays.stream(new Integer[]{1, 2, 3, 4}).collect(Collectors.toSet());
        Set<Double> s2 = Arrays.stream(new Double[]{1.0d, 2.2d, 3.3d}).collect(Collectors.toSet());
        // 写法1
//        Set<? extends Number> union = union(s1, s2);
        // 写法2 其实书上这种写法报错了，但是应该是基于 JDK6 写的原因，这种能写就没必要用书上的了，并不好看
        // max 方法说明如果是泛型则不能直接这么写，得用写法1
        Set<Number> union = union(s1, s2);
        System.out.println(union);

        Integer max = max(Arrays.stream(new Integer[]{1, 2, 3, 4, 5}).collect(Collectors.toList()));
        System.out.println(max);

    }

    public static <T extends Comparable<? super T>> T max(List<? extends T> list) {
        // 编译报错
//        Iterator<T> iterator = list.iterator();
        Iterator<? extends T> iterator = list.iterator();
        T result = iterator.next();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (next.compareTo(result) > 0) {
                result = next;
            }
        }
        return result;
    }

    private static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2) {
        HashSet<E> union = new HashSet<>();
        union.addAll(s1);
        union.addAll(s2);
        return union;
    }

    private static class Stack<E> {
        private int size = 0;
        private Object[] stack;

        public Stack() {
            this.stack = new Object[16];
        }

        public void push(E e) {
            stack[size++] = e;
        }

        public E pop() {
            if (!isEmpty()) {
                E result = (E) stack[--size];
                stack[size] = null;
                return result;
            }
            return null;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        // pushAll 版本一
//        public void pushAll(Iterable<E> src) {
        public void pushAll(Iterable<? extends E> src) {
            for (E e : src) {
                push(e);
            }
        }

        // popAll 版本一
//        public void popAll(Collection<E> target) {
        public void popAll(Collection<? super E> target) {
            while (!isEmpty()) {
                target.add(pop());
            }
        }

        @Override
        public String toString() {
            return "Stack{" +
                    "size=" + size +
                    ", stack=" + Arrays.toString(stack) +
                    '}';
        }
    }
}
