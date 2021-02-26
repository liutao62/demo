package com.liutao62.art_of_concurrent_programming;

/**
 * @author liutao
 * @date Created in 2021/2/23 23:33
 * @description
 */
public class SleepUtils {

    public static void second(long i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            System.out.println("thread is interrupted");
        }
    }
}
