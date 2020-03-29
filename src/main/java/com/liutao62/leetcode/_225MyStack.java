package com.liutao62.leetcode;

import java.util.LinkedList;

public class _225MyStack {

    private LinkedList<Integer> queue;

    /**
     * Initialize your data structure here.
     */
    public _225MyStack() {
        queue = new LinkedList();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        queue.addFirst(x);
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return queue.removeFirst();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return queue.getFirst();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return queue.isEmpty();
    }
}
