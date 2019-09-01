package com.data_structure.list.impl;

import com.data_structure.list.MyList;

public class MyLinkedList<T> implements MyList<T> {

    private Node head;
    private Node last;
    private int size;

    @Override
    public boolean isEmpty() {
        return this.size() == 0 ? true : false;
    }

    @Override
    public boolean add(T val) {
        return addLast(val);
    }

    public boolean addFirst(T value) {
        Node node = new Node(value);
        node.next = head;
        if (head != null) {
            head.pre = node;
        }

        if (last == null) {
            last = node;
        }
        head = node;
        size++;
        return true;
    }

    public boolean addLast(T value) {
        Node node = new Node(value);
        node.pre = last;
        if (last != null) {
            last.next = node;
        }
        if (head == null) {
            head = node;
        }
        last = node;
        size++;
        return true;
    }

    @Override
    public boolean remove(int index) {
        if (index < 0)
            throw new RuntimeException("remove failed by " + index);
        Node temp = head;
        while (temp != null && index > 0) {
            temp = temp.next;
            index--;
        }
        if (index == 0 && temp != null) {
            if (isHead(temp) && isLast(temp)) {
                head = null;
                last = null;
                size--;
            } else if (isHead(temp)) {
                removeFirst();
            } else if (isLast(temp)) {
                removeLast();
            } else {
                temp.pre.next = temp.next;
                temp.next.pre = temp.pre;
                size--;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object value) {
        Node temp = head;
        while (temp != null) {
            T val = temp.val;
            if (val == value ||
                    (val.equals(value) && val.hashCode() == value.hashCode())) {
                if (isHead(temp) && isLast(temp)) {
                    head = null;
                    last = null;
                    size--;
                } else if (isHead(temp)) {
                    removeFirst();
                } else if (isLast(temp)) {
                    removeLast();
                } else {
                    temp.pre.next = temp.next;
                    temp.next.pre = temp.pre;
                    size--;
                }
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    private boolean isHead(Node node) {
        return node == head;
    }

    private boolean isLast(Node node) {
        return node == last;
    }

    public boolean removeFirst() {
        if (head == null) return false;
        Node temp = head;
        head = head.next;
        temp.next = null;
        if (head != null)
            head.pre = null;

        size--;
        return true;
    }

    public boolean removeLast() {
        if (last == null) return false;
        Node temp = last;
        last = last.pre;
        temp.pre = null;
        if (last != null)
            last.next = null;

        size--;
        return true;
    }

    @Override
    public T get(int index) {
        if (index < 0)
            throw new RuntimeException("get failed by " + index);
        Node temp = head;
        while (index > 0 && temp != null) {
            temp = temp.next;
            index--;
        }
        if (index > 0)
            throw new RuntimeException("the index value more than size ");

        return temp == null ? null : temp.val;
    }

    @Override
    public boolean clear() {
        head = null;
        last = null;
        size = 0;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean change(int index, T value) {
        if (index < 0) return false;
        Node temp = head;
        while (temp != null && index > 0) {
            temp = temp.next;
            index--;
        }
        if (index > 0 || temp == null) return false;

        temp.val = value;

        return true;
    }

    @Override
    public String toString() {
        Node temp = head;
        StringBuffer sb = new StringBuffer("[");
        while (temp != null) {
            sb.append(temp.val + ",");
            temp = temp.next;
        }
        if (sb.length() > 1)
            sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }


    private class Node {
        private T val;
        private Node next;
        private Node pre;

        public Node(T val) {
            this.val = val;
        }
    }
}
