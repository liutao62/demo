package com.liutao62.jvm;

public class SafepointTest {
    static double sum = 0;

    public static void foo() {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 0x77777777; i++) {
            sum += Math.sqrt(i);
        }
        System.out.println(System.currentTimeMillis() - begin);
    }

    public static void bar() {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 50_000_000; i++) {
            new Object().hashCode();
        }
        System.out.println(System.currentTimeMillis() - begin);
    }

    public static void main(String[] args) {
        new Thread(SafepointTest::foo).start();
        new Thread(SafepointTest::bar).start();
    }
}