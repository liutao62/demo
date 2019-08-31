package com.javasedemo;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    private static Timer timer = new Timer(true);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("begin");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("计时器");
            }
        },0);
        System.out.println("after");
        Thread.sleep(1000);

    }
}
