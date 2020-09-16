package com.liutao62.effective_java;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author liutao
 * @date Created in 2020/9/16 22:39
 * @description
 */
public class SignalDemo {

    // 由于 boolean 域的读写操作都是原子的，访问这个域不再使用同步
    private static /* method1: 使用 volatile 之后即可一秒钟后停止。可见性，禁止重排序*/ boolean signal;

    // method2: 对同步信号量的访问通过同步方法（读写方法都要同步，否则不起作用）

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            int i = 0;
            // 轮询
            while (!signal) {
                i++;
            }
            /*
            VM 将上面的代码转变成下面这样，这种优化称作提升
            if (!done) {
                while (!signal) {
                    i++;
                }
            }
            */
        }).start();

        Thread.sleep(1000);
        signal = true;


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + generateSerialNumber());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        }).start();
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " - " + generateSerialNumber());
            Thread.sleep(100);
        }
        /*
            version1:
            main - 18
            Thread-1 - 18
            因为增量符 ++ 不是原子的，执行了两项操作：读值再写值
            Thread-1 在 main 线程读值和写值的期间读取了这个值，也就是是两个线程看到了同一个值，并返回相同的序列号
        */
    }

    // 使用 volatile 的时候务必要小心，假设要用它产生序列号
    /* version1
    private static volatile int serialNumber = 0;

    public static int generateSerialNumber() {
        return serialNumber++;
    }
    */
    /* version2: 可以解决问题，并且使用 long 使方法更加可靠？？？？溢出嘛？
    private static long serialNumber = 0;

    public static synchronized long generateSerialNumber() {
        return serialNumber++;
    }
    */
    // version3
    private static final AtomicLong serialNumber = new AtomicLong();

    public static long generateSerialNumber() {
        // compareAndSwap , 单纯递增的话也不会有 ABA 问题
        return serialNumber.getAndIncrement();
    }
}
