package com.liutao62.demo;

import com.liutao62.leetcode._22GenerateParenthesis;

public class TongHuaShun {
    public byte method() {
        byte b = 127;
        return ++b;
    }

    /**
     * @param n
     * @description 打印倒金字塔
     */
    public void print(int n) {
        int count = n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            for (int k = count; k > 0; k--) {
                System.out.print("#");
            }
            count -= 2;
            System.out.println();
        }
    }

    /**
     * @param n
     * @description 2n个小孩，有一半有五毛，有一半有一块钱，冰棍五毛一根，商店没有零钱，请问有多少种排序。
     * @see _22GenerateParenthesis
     */
    public void getList(int n) {
    }
}
