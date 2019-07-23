package com.javasedemo;

import org.junit.Test;

import static org.junit.Assert.*;

public class AssertDemoTest {
    @Test
    public void test(){
        int x = 2;
        assert x > 3 : x + " > 3";


    }

}