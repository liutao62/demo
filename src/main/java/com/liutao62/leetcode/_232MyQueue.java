package com.liutao62.leetcode;

import java.util.Stack;

/**
 *
 */
public class _232MyQueue {

    private Stack<Integer> in = new Stack<>();
    private Stack<Integer> out = new Stack<>();

    /**
     * Initialize your data structure here.
     */
    public _232MyQueue() {

    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        in.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        while (out.empty()) {
            while (!in.empty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        while (out.empty()) {
            while (!in.empty()) {
                out.push(in.pop());
            }
        }
        return out.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return in.empty() && out.empty();
    }
}
