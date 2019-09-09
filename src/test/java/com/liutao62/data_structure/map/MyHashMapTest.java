package com.liutao62.data_structure.map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyHashMapTest {

    private MyHashMap<String, Object> map = new MyHashMap();

    @Before
    public void before() {
        map.put("liutao", "123456");
        map.put("liutao", null);
        map.put("sd", 1234);
        map.put("lk", 12345);
        map.put("qwer", 1234678);
        System.out.println(map);
    }

    @Test
    public void put() {
        map.put("test", "lkjfd");
    }

    @Test
    public void get() {
        assert map.get("liutao") == null;
    }

    @After
    public void after() {
        System.out.println(map);
    }
}