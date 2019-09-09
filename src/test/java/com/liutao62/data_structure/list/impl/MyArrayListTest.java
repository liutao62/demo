package com.liutao62.data_structure.list.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyArrayListTest {
    private MyArrayList<String> list = new MyArrayList();

    @Before
    public void before() {
        list.add("0");
        list.add("1");
        System.out.println(list);
    }

    @Test
    public void isEmpty() {
        Assert.assertTrue(!list.isEmpty());
    }

    @Test
    public void add() {
        list.add("3");
    }

    @Test
    public void remove() {
        list.remove(0);
    }

    @Test
    public void remove1() {
        list.remove("1");
    }

    @Test
    public void get() {
        System.out.println(list.get(1));
    }

    @Test
    public void clear() {
        list.clear();
    }

    @Test
    public void size() {
        System.out.println(list.size());
    }

    @Test
    public void change() {
        list.change(1,"9");
    }

    @After
    public void after() {
        System.out.println(list);
    }
}