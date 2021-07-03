package com.liutao62.data_structure.advance;

import java.util.*;

/**
 * @author liutao
 * @date Created in 2021/6/28 0:24
 * @description 在InnoDB实现上，按照5:3的比例把整个LRU链表分成了young区域和old区域。图中LRU_old指
 * 向的就是old区域的第一个位置，是整个链表的5/8处。也就是说，靠近链表头部的5/8是young区
 * 域，靠近链表尾部的3/8是old区域。
 * 改进后的LRU算法执行流程变成了下面这样。
 * 1. 图7中状态1，要访问数据页P3，由于P3在young区域，因此和优化前的LRU算法一样，将
 * 其移到链表头部，变成状态2。
 * 2. 之后要访问一个新的不存在于当前链表的数据页，这时候依然是淘汰掉数据页Pm，但是新
 * 插入的数据页Px，是放在LRU_old处。
 * 3. 处于old区域的数据页，每次被访问的时候都要做下面这个判断：
 * 若这个数据页在LRU链表中存在的时间超过了1秒，就把它移动到链表头部；
 * 如果这个数据页在LRU链表中存在的时间短于1秒，位置保持不变。1秒这个时间，是由
 * 参数innodb_old_blocks_time控制的。其默认值是1000，单位毫秒。
 */
// 避免缓存污染
public class LRUOfInnoDB<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final int MAX_CAPACITY = 128;
    private static final int INNODB_OLD_BLOCKS_TIME = 1000;

    private int capacity;
    private int partition;

    private Map<K, Node> map;
    private Map<K, Node> old;

    private Node<K, V> head;
    private Node<K, V> tail;
    private Node<K, V> oldHead;

    public LRUOfInnoDB() {
        this(DEFAULT_CAPACITY);
    }

    public LRUOfInnoDB(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("by capacity < 0");
        } else if (capacity > MAX_CAPACITY) {
            capacity = MAX_CAPACITY;
        }
        this.capacity = capacity;
        // 总数据
        map = new HashMap<>(capacity);
        // old 区
        old = new HashMap<>(capacity >> 1);

        // 双向链表初始化
        head = new Node<>();
        Node cursor = head;
        partition = capacity / 8 * 5;
        for (int i = 1; i < capacity; i++) {
            Node<Object, Object> node = new Node<>();
            cursor.next = node;
            node.pre = cursor;
            cursor = node;
        }
        cursor.next = head;
        head.pre = cursor;
        tail = head;
    }

    public V get(K key) {
        Node node = map.get(key);
        //-----------------------
        if (node == null) {
            return null;
        } else if (old.containsKey(key)) {
            if (System.currentTimeMillis() - node.time < INNODB_OLD_BLOCKS_TIME) {
                return (V) node;
            }
            move2OldHead(node);
        } else {
            move2YoungHead(node);
        }
        //-----------------------
        // System.out.println(old.values());
        // System.out.println(map.values());
        return (V) node.value;
    }

    public void put(K key, V value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            if (old.containsKey(key)) {
                if (System.currentTimeMillis() - node.time < INNODB_OLD_BLOCKS_TIME) {
                    return;
                }
                //----------------old 区移动到 old 头部
                move2OldHead(node);
            } else {
                //----------------young 区移动到 young 头部
                move2YoungHead(node);
            }
        } else {
            // young 区不满，加入 young 区
            if (map.size() < partition) {
                tail.key = key;
                tail.value = value;
                map.put(key, tail);
                tail = tail.next;
                oldHead = tail;
            } else {
                tail = head.pre;
                map.remove(tail.key, tail);
                old.remove(tail.key, tail);

                tail.key = key;
                tail.value = value;
                tail.time = System.currentTimeMillis();
                move2OldHead(tail);
                map.put(key, tail);
                old.put(key, tail);
            }
        }
        // System.out.println("-----------------------");
        // System.out.println(head.print());
        // System.out.println("old = " + old.values());
        // System.out.println("map = " + map.values());
        // System.out.println("-----------------------");
    }

    private void move2OldHead(Node<K, V> node) {
        // 如果是 oldHead 晋升到 young 尾
        if (node == oldHead) {
            Node<K, V> youngTail = node.pre;
            youngTail.next = node.next;
            node.next = youngTail;

            node.pre = youngTail.pre;
            youngTail.pre = node;
            node.pre.next = node;
            youngTail.next.pre = youngTail;
            old.remove(node.key, node);
            old.put(youngTail.key, youngTail);
        } else {
            node.pre.next = node.next;
            node.next.pre = node.pre;

            node.next = oldHead;
            node.pre = oldHead.pre;
            oldHead.pre.next = node;
            oldHead.pre = node;
            oldHead = node;
        }
    }

    private void move2YoungHead(Node node) {
        if (head == node) {
            return;
        }
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.next = head;
        node.pre = head.pre;

        head.pre.next = node;
        head.pre = node;
        head = node;
    }


    class Node<K, V> {
        Node next, pre;
        K key;
        V value;
        long time;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

        public String print() {
            StringBuilder sb = new StringBuilder();
            Set<Object> set = new HashSet<>();
            Node cursor = this;
            sb.append("next : ");
            while (cursor != null && !set.contains(cursor.value)) {
                sb.append(cursor.value).append(" -> ");
                set.add(cursor.value);
                cursor = cursor.next;
            }
            if (cursor == this) {
                sb.append(" self ");
            }
            sb.append("\npre : ");
            cursor = this;
            set.clear();
            while (cursor != null && !set.contains(cursor.value)) {
                sb.append(cursor.value).append(" <- ");
                set.add(cursor.value);
                cursor = cursor.pre;
            }
            if (cursor == this) {
                sb.append(" self \n");
            }
            return sb.toString();
        }


        @Override
        public String toString() {
            return value.toString();
        }
    }

}
