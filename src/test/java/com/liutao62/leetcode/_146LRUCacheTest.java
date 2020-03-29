package com.liutao62.leetcode;

import org.junit.Test;

public class _146LRUCacheTest {

    _146LRUCache lruCache;

    /**
     * ["LRUCache","put","put","get","put","get","put","get","get","get"]
     * [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
     *
     * @throws Exception
     */
    @Test
    public void get() {
        lruCache = new _146LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }

    /**
     * ["LRUCache","put","put","get","get","put","get","get","get"]
     * [[2],[2,1],[3,2],[3],[2],[4,3],[2],[3],[4]]
     */
    @Test
    public void test() {
        lruCache = new _146LRUCache(2);
        lruCache.put(2, 1);
        lruCache.put(3, 2);
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(2));
        lruCache.put(4, 3);
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }

    @Test
    public void put() {
    }
}