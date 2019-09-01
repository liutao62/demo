package com.javasedemo;

public class OverrideDemo2 {
    public static void main(String[] args) {
        Super1 instance = new Sub1();
        instance.method1();     //SUB根据实例本身调用方法

        instance.superMethod();
        //  instance.subMethod();     报错，instance编译类型是Super类型检查Super类中没有该方法]
        System.out.println("---------");
        instance.x = 1;
        System.out.println(instance.x);
        System.out.println("---------");
        Sub1 sub = (Sub1) instance;
        sub.subMethod();
        System.out.println("---------");
        System.out.println(sub.getX());
    }

}

class Super1 {
    public String string;
    public Integer x;

    public Super1() {                                       //4           8
        System.out.println("super 构造器");
    }

    {                                                     //3           7
        string = new String("super 代码块");
        System.out.println(string);
    }

    static {                                              //1
        System.out.println("super 静态代码块");
    }

    protected void method1() throws RuntimeException {
        System.out.println("SUPER");
    }

    public void superMethod() {
        System.out.println("superMethod");
    }

}

class Sub1 extends Super1 {
    protected Integer x;
    public String string;

    {                                                      //5          9
        string = new String("sub 代码块");
        System.out.println(string);
    }

    public Sub1() {                                          //6          10
        System.out.println("sub 构造器");
    }

    static {                                               //2
        System.out.println("sub 静态代码块");
    }

    public void method1() throws ArithmeticException {
        System.out.println("SUB");
    }

    public void subMethod() {
        System.out.println("subMethod");
    }

    public Integer getX() {
        return this.x;
    }
}
