package com.liutao62.demo;

/**
 *  四种单例模式
 */
public enum SingleDemo {
    enumInstance;

    public void hi() {
        System.out.println("hi");
    }
}

class Single1 {
    private Single1() {
    }

    private static Single1 instance = new Single1();

    public static Single1 getInstance() {
        return instance;
    }
}

/**
 * 并发情况下可能会破坏单例
 */
class Single2 {
    private Single2() {
    }

    private static Single2 instance;

    public static Single2 getInstance() {
        if (instance == null) {
            instance = new Single2();
        }
        return instance;
    }
}

/**
 * 双重检索机制
 */
class Single3 {
    private Single3() {
    }

    private static Single3 instance;

    public static Single3 getInstance() {
        if (instance == null) {
            synchronized (Single3.class) {
                if (instance == null)
                    instance = new Single3();
            }
        }
        return instance;
    }
}