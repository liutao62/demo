package com.liutao62.effective_java;

import java.io.Serializable;

/**
 * @author liutao
 * @date Created in 2020/8/16 16:12
 * @description
 */
public class SingletonDemo {
    public static void main(String[] args) {
//        AccessibleObject object = new AccessibleObject();
//        object.setAccessible(true);
    }
}

/**
 * 可以借助 object.setAccessible(true) 方法通过反射机制调用私有构造器
 * 可以修改构造器，让它在被要求创建第二个实例的时候抛出异常
 */
class Singleton1 {
    public static final Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {
    }
}

/**
 * 为了使利用这其中一种方法实现的Singleton类编程时可序列化的，仅 implements Serializable 是不够的
 * 需要声明所有的的实例域都是瞬时（transient）的，并提供一个 readReasolve 方法
 */
class Singleton2 implements Serializable {
    private static final long serialVersionUID = 8367495898228178902L;
    private static final Singleton2 INSTANCE = new Singleton2();

    private transient int a;

    private Singleton2() {
        this.a = 1;
    }

    public static Singleton2 getInstance() {
        return INSTANCE;
    }

    /**
     * @return 返回一个真正的单例，否则每次反序列化一个实例的时候都会创建一个新的实例
     * @description 保留单例属性。
     */
    private Singleton2 readReasolve() {
        return INSTANCE;
    }
}

/**
 * 更加简洁，无偿提供了序列化机制，绝对防止多次实例化，即使在面对复杂的序列化或者反射攻击的时候
 */
// 首选方法
enum Singleton3 {
    INSTANCE
}
