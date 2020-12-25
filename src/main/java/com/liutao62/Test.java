package com.liutao62;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @author liutao
 * @date Created in 2020/11/17 23:26
 * @description
 */
public class Test {
    public static void main(String[] args) {
        new Thread(new TimeWaiting(), "TimeWaiting").start();
        new Thread(new Waiting(), "Waiting").start();
        new Thread(new Blocked(), "Blocked-1").start();
        new Thread(new Blocked(), "Blocked-2").start();
    }

    private static class TimeWaiting implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                TimeUnit.SECONDS.sleep(100);
            }
        }
    }

    private static class Waiting implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    Waiting.class.wait();
                }
            }
        }
    }

    private static class Blocked implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    TimeUnit.SECONDS.sleep(100);
                }
            }
        }
    }
}
