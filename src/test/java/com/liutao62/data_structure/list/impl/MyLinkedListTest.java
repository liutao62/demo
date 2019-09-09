package com.liutao62.data_structure.list.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyLinkedListTest {

    private MyLinkedList<String> list = new MyLinkedList<>();

    @Before
    public void before() {
        list.add("1");
        list.add("2");
        System.out.println(list);
    }

    @Test
    public void isEmpty() {
        Assert.assertTrue(!list.isEmpty());
        System.out.println(list.size());
        System.out.println(list.isEmpty());
    }

    @Test
    public void add() {
        list.add("3");
    }

    @Test
    public void addFirst() {
        list.addFirst("0");
    }

    @Test
    public void addLast() {
        list.addLast("9");
    }

    @Test
    public void remove() {
        list.remove(1);
    }

    @Test
    public void remove1() {
        list.remove("1");
    }

    @Test
    public void removeFirst() {
        list.removeFirst();
    }

    @Test
    public void removeLast() {
        list.removeLast();
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
    }

    @Test
    public void change() {
        list.change(1,"9");
    }

    @After
    public void after(){
        System.out.println(list);
    }
}