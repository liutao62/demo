package com.liutao62.leetcode;

import java.util.*;

/**
 * @author liutao
 * @date Created in 2021/3/17 23:08
 * @description
 */
public class _155MinStack {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(512);
        minStack.push(-1024);
        minStack.push(-1024);
        minStack.push(512);
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
    }

}

class MinStack {

    private LinkedList<Integer> stack = new LinkedList<>();

    private Integer min;

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    public void push(int x) {
        stack.push(x);
        if (min == null || min > x) {
            min = x;
        }
    }

    public void pop() {
        Integer pop = stack.pop();
        if (pop.equals(min)) {
            min = stack.peek();
            Iterator<Integer> iterator = stack.iterator();
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                if (min > next) {
                    min = next;
                }
            }
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}

/*
错误的优化。花里胡哨~
class MinStack {

    private java.util.LinkedList<Integer> stack = new java.util.LinkedList<>();
    private java.util.LinkedList<Integer> minValueQueue = new java.util.LinkedList<>();

    private Integer minValue;

    */
/**
 * initialize your data structure here.
 *//*

    public MinStack() {

    }

    public void push(int x) {
        stack.push(x);
        if (minValue == null || minValue > x) {
            minValue = x;
        }
        Integer last = minValueQueue.peekLast();
        if (last == null || last >= x) {
            minValueQueue.add(x);
            Collections.sort(minValueQueue);
        }
    }

    public void pop() {
        Integer pop = stack.pop();
        if (pop.equals(min)) {
            minValueQueue.removeFirst();
            getMinValue();
        }
    }

    private void getMinValue() {
        if (minValueQueue.isEmpty()) {
            minValue = stack.getFirst();
            Iterator<Integer> iterator = stack.iterator();
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                if (minValue > next) {
                    minValue = next;
                }
            }
            return;
        }
        minValue = minValueQueue.getFirst();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return this.minValue;
    }
}*/
