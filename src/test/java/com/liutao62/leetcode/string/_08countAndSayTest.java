package com.liutao62.leetcode.string;

import org.junit.Test;

import static org.junit.Assert.*;

public class _08countAndSayTest {

    @Test
    public void countAndSay() {
        String s = new _08countAndSay().countAndSay(4);
        assert "1211".equals(s);
    }
}