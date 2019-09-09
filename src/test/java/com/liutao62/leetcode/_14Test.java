package com.liutao62.leetcode;

import org.junit.Test;

public class _14Test {

    private _14 test = new _14();
    private String[] strings = {"a"};

    @Test
    public void test(){
        String s = test.longestCommonPrefix(strings);
        System.out.println(s);
    }
}