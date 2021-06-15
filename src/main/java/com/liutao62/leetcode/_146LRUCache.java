package com.liutao62.leetcode;

import java.util.HashMap;

public class _146LRUCache {

    // 双链表的节点
    private class Node {
        int key, val;
        Node next, pre;

        Node(int key, int val, Node pre, Node next) {
            this.key = key;
            this.val = val;
            this.pre = pre;
            this.next = next;
        }
    }

    private Node head = new Node(-1, -1, null, null);
    private HashMap<Integer, Node> map = new HashMap<>();

    /**
     * @param cur
     * @description 移动成为头指针, 如果当前指针和头指针一样，则让头指针移动到前面
     */
    private void move2Head(Node cur) {
        if (cur == head) {
            return;
        } else if (cur == head.pre) {
            head = cur;
            return;
        }

        // 处理前后节点
        cur.pre.next = cur.next;
        cur.next.pre = cur.pre;

        // 移动到头节点
        cur.next = head;
        cur.pre = head.pre;
        head.pre.next = cur;
        head.pre = cur;

        head = cur;
    }

    /**
     * @param capacity
     * @description 初始化一个空的双向链表环 -1 表示没有值
     */
    _146LRUCache(int capacity) {
        Node cur = head;
        for (int i = 0; i < capacity - 1; i++) {
            cur.next = new Node(-1, -1, cur, null);
            cur = cur.next;
        }
        cur.next = head;
        head.pre = cur;
    }

    int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        move2Head(node);
        return node.val;
    }

    void put(int key, int val) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = val;
            move2Head(node);
        } else {
            Node pre = head.pre;
            if (pre.val != -1) {
                map.remove(pre.key);
            }
            pre.key = key;
            pre.val = val;
            map.put(key, pre);
            head = pre;
        }
    }
}
