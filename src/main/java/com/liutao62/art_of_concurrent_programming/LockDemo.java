package com.liutao62.art_of_concurrent_programming;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liutao
 * @date Created in 2021/4/30 10:41
 * @description
 */
public class LockDemo {

    static int NUM = 100;

    public static void main(String[] args) throws IOException {
        Lock lock = new ReentrantLock();

        new Thread(() -> {
            lock.lock();
            int i = 0;
            while (NUM > 0) {
                SleepUtils.millisecond(20);
                System.out.println(Thread.currentThread().getName() + "  " + NUM--);
            }
            lock.unlock();
        }, "thread a").start();

        new Thread(() -> {
            lock.lock();
            int i = 0;
            while (NUM > 0) {
                SleepUtils.millisecond(20);
                System.out.println(Thread.currentThread().getName() + "  " + NUM--);
            }
            lock.unlock();
        }, "thread b").start();

        System.in.read();
    }
}
