package com.liutao62.leetcode;

import java.util.HashMap;

public class _146LRUCache {

    // 双链表的节点
    private class Node {
        Integer key, val;
        Node next, pre;

        Node(Integer key, Integer val, Node pre, Node next) {
            this.key = key;
            this.val = val;
            this.pre = pre;
            this.next = next;
        }
    }

    private Node head = new Node(null, null, null, null);
    private HashMap<Integer, Node> map = new HashMap<>();

    /**
     * @author razertory
     * @date 2019/1/2
     * @description 移动成为头指针, 如果当前指针和头指针一样，则让头指针移动到前面
     */
    private void move2Head(Node cur) {
        if (cur == head) {
            head = head.pre;
            return;
        }
        cur.pre.next = cur.next;
        cur.next.pre = cur.pre;

        cur.next = head.next;
        cur.next.pre = cur;
        head.next = cur;
        cur.pre = head;
    }

    /**
     * @author razertory
     * @date 2019/1/2
     * @description 初始化一个空的双向链表环 -1 表示没有值
     */
    _146LRUCache(int capacity) {
        Node cur = head;
        for (int i = 0; i < capacity - 1; i++) {
            cur.next = new Node(null, null, cur, null);
            cur = cur.next;
        }
        cur.next = head;
        head.pre = cur;
    }

    int get(int key) {
        if (!map.containsKey(key)) return -1;
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
            if (head.val != null) map.remove(head.key);
            head.key = key;
            head.val = val;
            map.put(key, head);
            head = head.pre;
        }
    }
}
