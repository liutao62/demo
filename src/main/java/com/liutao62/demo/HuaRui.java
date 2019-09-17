package com.liutao62.demo;

/**
 * 华锐笔试：多线程轮番执行
 */
public class HuaRui {
    public static volatile int i = 1;

    public static void main(String[] args) {
        Thread A = new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    synchronized (HuaRui.class) {
                        if (HuaRui.i == 1){
                            System.out.println("A");
                            HuaRui.i = 0;
                        }else i--;
                    }
                }
            }
        };
        Thread B = new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    synchronized (HuaRui.class) {
                        if (HuaRui.i == 0){
                            System.out.println("B");
                            HuaRui.i = -1;
                        }else i--;
                    }
                }
            }
        };
        Thread C = new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    synchronized (HuaRui.class) {
                        if (HuaRui.i == -1){
                            System.out.println("C");
                            HuaRui.i = 1;
                        }else i--;
                    }
                }
            }
        };
        A.start();
        B.start();
        C.start();
    }
}
