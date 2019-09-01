package com.javasedemo;

import org.junit.Test;

public class JdTest {

    @Test
    public void testTest2() {
        System.out.println(Test2.a);
    }

    @Test
    public void testTest3() {
        System.out.println(Test3.a);
    }

    @Test
    public void testTest4() {
        System.out.println(Test4.a);
    }

    @Test
    public void testB1() {
        System.out.print(B1.c);
    }

    @Test
    public void testB2() {
        // 类加载的问题，类加载的初始化阶段会执行静态块的代码，通过子类去调用父类的静态变量子类不会被初始化。
        System.out.print(B2.c);
    }

    @Test
    public void testA3() throws ClassNotFoundException {
        // 用ClassLoader加载类，是不会导致类的初始化（也就是说不会执行<clinit>方法）.
        // Class.forName(...)加载类，不但会将类加载，还会执行会执行类的初始化方法.
        ClassLoader classLoader=ClassLoader.getSystemClassLoader();
        Class clazz=classLoader.loadClass("com.javasedemo.A3");
        System.out.print("Test");
        clazz.forName("com.javasedemo.A3");
    }

}

class Test2 {
    // 编译时期常量 无需进行 链接 (为这个域分配存储空间) 和初始化 (初始化该存储空间)
    public static final String a = "JD";

    static {
        System.out.print("OK");
    }

}

class Test3 {
    public static final String a = new String("JD");

    static {
        System.out.print("OK");
    }

}

class Test4 {
    static {
        System.out.print("OK");
    }

    public static final String a = new String("JD");

}

class A1 {
    static {
        System.out.print("A");
    }
}

class B1 extends A1 {
    static {
        System.out.print("B");
    }

    public final static String c = "C";
}

class A2 {
    // 类加载的问题，类加载的初始化阶段会执行静态块的代码，通过子类去调用父类的静态变量子类不会被初始化。
    // JVM中类加载的初始化工作可以延迟执行，直到其中的static成员被调用或者有对象实例化时才触发
    public static String c = "C";

    static {
        System.out.print("A");
    }
}

class B2 extends A2 {
    static {
        System.out.print("B");
    }
}

class A3 {
    static {
        System.out.print("A");
    }
}