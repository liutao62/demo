package com.liutao62.demo;

import java.util.ArrayList;

public class YieldAndJoinDemo{

    public static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        Thread t1 = new YieldAndJoinDemo().new MyThread();
        Thread t2 = new Thread(new YieldAndJoinDemo().new MyRunnable());
        t1.setPriority(3);
        t2.setPriority(8);
        t1.start();
        t2.start();
        // 等待该线程终止
        t2.join();

        for (int i = 0; i < 100000; i++) {
            i++;
        }
        list.add("main");

        t1.join();

        for (String s : list) {
            System.out.println(s);
        }
    }

    class MyThread extends Thread{
        @Override
        public void run(){
            for (int i = 0; i < 100000; i++) {
                i++;
            }
            list.add("Thread 1");
        }
    }

    class MyRunnable implements Runnable{
        @Override
        public void run(){
            for (int i = 0; i < 100000; i++) {
                // 暂停当前正在执行的线程对象，并执行其他线程。
                Thread.yield();
                i++;
            }
            list.add("Thread 2");
        }
    }
}