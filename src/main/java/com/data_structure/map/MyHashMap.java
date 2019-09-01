package com.data_structure.map;

public class MyHashMap<T,K> {
    //默认数组初始化大小
    private final static int DEFAULT_INITSIZE = 16;
    //数组当前含量
    private static int COUNT_IN_ARRAY = 0;
    //最大数组大小
    private final static int MAX_SIZE = Integer.MAX_VALUE >> 1;

    private Node[] nodes;

    public MyHashMap() {
        this.nodes = new Node[DEFAULT_INITSIZE];
    }

    public MyHashMap(int size) {
        if (size > 0 && size <= MAX_SIZE) {
            size = getBiggerBinary(size);
        } else if (size > MAX_SIZE) {
            size = MAX_SIZE - 1;
        } else {
            throw new IllegalArgumentException("错误的初始化大小： " + size);
        }
        size = DEFAULT_INITSIZE;
        this.nodes = new Node[size];
    }

    /**
     * @return
     * @description 进行扩容操作，并进行重新的hash确保调用者能正确获取到value
     *              扩容条件：COUNT_IN_ARRAY > nodes.length
     */
    private void expansion() {
        if(nodes.length < MAX_SIZE){
            Node[] oldNodes = this.nodes;
            this.nodes = new Node[this.nodes.length << 1];
            for (int i = 0 ; i < oldNodes.length ; i++){
                if (oldNodes[i] != null){
                    nodes[this.hash(oldNodes[i].key) & this.nodes.length - 1] = oldNodes[i];
                }
            }
        }
    }

    /**
     * @param size
     * @return
     * @description 通过移位或运算，得到当前size当前有效位数（第一个1开始及以后的位数）最大的二进制数
     */
    private int getBiggerBinary(int size) {
        size -= 1;
        size |= size >>> 1;
        size |= size >>> 2;
        size |= size >>> 4;
        size |= size >>> 8;
        return size;
    }

    /**
     * @param key
     * @param value
     * @description 对外暴露的put方法，用于检测key，value是否为空以及扩容判断
     */
    public synchronized void put(T key, K value) {
        if (key != null) {
            if(COUNT_IN_ARRAY > this.nodes.length){
                this.expansion();
            }
            putInArray(hash(key) & this.nodes.length - 1, key, value);
        } else {
            throw new IllegalArgumentException("error key ：null");
        }
    }

    /**
     * @param hash
     * @param key
     * @param value
     * @description 根据key的hash值，将value 保存到数组合适的位置
     */
    private void putInArray(int hash, T key, K value) {
        if (this.nodes[hash] == null) {
            this.nodes[hash] = new Node(key, value);
        } else {
            Node temp = new Node();
            synchronized (nodes[hash]){
                temp.next = this.nodes[hash];
                while (temp.next != null) {
                    if (key.equals(temp.next.key)) {
                        temp.next.value = value;
                        return;
                    }
                    temp = temp.next;
                }
                temp.next = new Node(key, value);
            }

        }
    }

    /**
     * @param key
     * @return key值对应的value
     * @description 根据key值获取value
     */
    public Object get(Object key) {
        Node temp = this.nodes[hash(key) & this.nodes.length - 1];
        while (temp != null) {
            if (temp.key.equals(key)) {
                return temp.value;
            }
            temp = temp.next;
        }
        return null;
    }

    /**
     * @param key
     * @return
     * @description 根据key的hashcode，得到key的hash值
     */
    private int hash(Object key) {
        int hash = key.hashCode();
        return hash >>> 16 & hash;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        Node temp = null;
        for (Node node : this.nodes) {
            temp = node;
            while (temp != null) {
                str.append(temp + ",");
                temp = temp.next;
            }
        }
        return str.toString();
    }

    private class Node<T,K> {
        private T key;
        private K value;
        public Node next;

        public Node() {
        }

        public Node(T key, K value) {
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString() {
            return "Node{" +
                    "key='" + key + '\'' +
                    ", value=" + value +
                    '}';
        }
    }
}