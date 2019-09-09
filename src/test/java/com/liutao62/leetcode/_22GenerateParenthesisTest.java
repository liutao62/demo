package com.liutao62.leetcode;

import org.junit.Test;

import java.util.List;

public class _22GenerateParenthesisTest {

    private _22GenerateParenthesis test = new _22GenerateParenthesis();

    @Test
    public void generateParenthesis() {
        List<String> list = test.generateParenthesis(3);
        System.out.println(list);
    }
}