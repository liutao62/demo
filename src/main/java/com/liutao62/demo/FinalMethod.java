package com.liutao62.demo;

public class FinalMethod {
    public static void main(String[] args) {
        Son s = new Son();
        s.method1();
        s.method3();
        System.out.println("--------------");
        Parent p = s;
//        p.method1();  访问权限为私有
        p.method2();
        p.method3();
    }
}

class Parent{
    private void method1(){
        System.out.println("Parent.method1");
    }
    final void method2(){
        System.out.println("Parent.method2");
    }
    public void method3(){
        System.out.println("Parent.method3");
    }
}

class Son extends Parent{
    public final void method1(){
        System.out.println("Son.method1");
    }
//    final void method2(){ }
    public void method3(){
        System.out.println("Son.method3");
    }
}
