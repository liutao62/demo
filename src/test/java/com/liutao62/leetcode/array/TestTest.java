package com.liutao62.leetcode.array;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class TestTest {
    // 源数据
    Integer[] arr = new Integer[20];
    // 随机数量
    int size = 5;
    // 当前未使用题目
    int len = arr.length;
    Random random = new Random();

    com.liutao62.leetcode.array.Test test = new com.liutao62.leetcode.array.Test();

    /**
     * 初始化arr，随机赋值
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
            System.out.print(arr[i] + " ");
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getRandomArray() {
        System.out.println();
        Integer[] randomArray = test.getRandomArray(arr, len, size);

        for (Integer o : randomArray) {
            System.out.print(o + " ");
        }
    }
}