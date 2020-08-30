package com.liutao62.data_structure.string;

import org.junit.Test;

import java.util.Arrays;

public class KMPTest {

    private String src = "aabababcabcd";

    private String target = "aabcd";

    @Test
    public void kmp() {
    }

    @Test
    public void getNext() {
        int[] next = new KMP().getNext(target);
        Arrays.stream(next).forEach(System.out::println);
    }
}