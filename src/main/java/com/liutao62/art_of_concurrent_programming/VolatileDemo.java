package com.liutao62.art_of_concurrent_programming;

import java.io.IOException;

/**
 * @author liutao
 * @date Created in 2021/4/28 20:29
 * @description
 */
public class VolatileDemo {

    private static boolean FLAG = true;

    public static void main(String[] args) throws IOException {
        new Thread(() -> {
            System.out.println("begin");
            while (FLAG) {

            }
            System.out.println("end");
        }).start();
        SleepUtils.second(1);

        FLAG = false;
        System.out.println("flag = false");

        System.in.read();
    }
}
