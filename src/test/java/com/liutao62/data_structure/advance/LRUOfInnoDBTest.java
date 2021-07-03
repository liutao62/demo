package com.liutao62.data_structure.advance;

import org.junit.Test;

public class LRUOfInnoDBTest {

    LRUOfInnoDB<Integer, Integer> lruOfInnoDB = new LRUOfInnoDB<>(8);

    {
        for (int i = 0; i < 8; i++) {
            lruOfInnoDB.put(i, i);
        }
    }

    @Test
    public void get() {
        Integer integer = lruOfInnoDB.get(1);
        System.out.println(integer);

        lruOfInnoDB.put(9, 9);
        // System.out.println(lruOfInnoDB.get(9));
        lruOfInnoDB.put(10, 10);
        // System.out.println(lruOfInnoDB.get(10));

    }

    @Test
    public void put() {
    }
}