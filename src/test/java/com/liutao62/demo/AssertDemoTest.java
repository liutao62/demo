package com.liutao62.demo;

import org.junit.Test;

public class AssertDemoTest {
    @Test
    public void test(){
        int x = 2;
        assert x > 3 : x + " > 3";


    }

}