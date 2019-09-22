package com.liutao62.demo;

class ClassName {

    private static ClassName SINGLETON = new ClassName();   // 2

    // 连接阶段均初始化为 0 （static final除外）
    public static int count1;                               //7 无操作
    public static int count2 = 0;                           //8 重新赋值
    public static int count3 = 1;                           //9 重新赋值

    // 初始化阶段
    private ClassName() {                                   //3
        count1++;                                           //4
        count2++;                                           //5
        count3++;                                           //6
    }

    public static ClassName getInstance() {
        return SINGLETON;
    }
}

public class Test {
    public static void main(String[] args) {
/*
        System.out.println(ClassName.count1);
        System.out.println(ClassName.count2);
        System.out.println(ClassName.count3);
*/


        ClassName instance = ClassName.getInstance();       // 1
        System.out.println(instance.count1);
        System.out.println(instance.count2);
        System.out.println(instance.count3);
    }
}

