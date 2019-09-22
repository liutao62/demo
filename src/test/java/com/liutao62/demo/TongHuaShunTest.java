package com.liutao62.demo;

import org.junit.Test;

import static org.junit.Assert.*;

public class TongHuaShunTest {
    private TongHuaShun test = new TongHuaShun();

    @Test
    public void method() {
        System.out.println(test.method());
    }

    @Test
    public void print() {
        test.print(20);
        test.print(19);
    }
}