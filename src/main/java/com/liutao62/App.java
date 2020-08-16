package com.liutao62;

public class App {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Long sum = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("计算 Long sum = " + sum + " 总用时： " + (end - start));

        start = System.currentTimeMillis();
        long sum1 = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum1 += i;
        }
        end = System.currentTimeMillis();
        System.out.println("计算 long sum = " + sum + " 总用时： " + (end - start));
    }
}
