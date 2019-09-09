package com.liutao62.demo;

interface A {
    void a_f();

    default void x_f() {
        System.out.println("x_f()");
    }
}

interface B {
    void b_f();
}

interface C extends A, B {
    static void c_f() {
        System.out.println("c_f()");
    }
}

class D {
    void d_f() {
        System.out.println("d_f()");
    }
}

class H implements A {
    @Override
    public void a_f() {
        System.out.println("a_f()");
    }
}

class E extends D implements A, B {

    @Override
    public void a_f() {
        System.out.println("a_f()");
    }

    @Override
    public void b_f() {
        System.out.println("b_f()");
    }

    public void x_f() {
        System.out.println("e_f()");
    }
}

public class App {
    public static void main(String[] args) {
        E e = new E();
        e.a_f();
        e.b_f();
        e.x_f();
        A a = new H();
        a.a_f();
        a.x_f();
        C.c_f();
    }
}
