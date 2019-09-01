package com.javasedemo;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    private static Timer timer = new Timer(true);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("begin");
        System.out.println(task());
        System.out.println("after");
        Thread.sleep(2200);

    }

    public static int task(){
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
        return 1;
    }
}
