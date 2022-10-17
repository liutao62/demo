package com.liutao62.jvm;

import lombok.Data;

@Data
public class MemoryModel {

    int a = 0, b = 0;

    public void method1() {
        int r2 = a;
        b = 1;
        System.out.println(r2);
    }

    public void method2() {
        int r1 = b;
        a = 2;
        System.out.println(r1);
    }

    public static void main(String[] args) {
        MemoryModel memoryModel = new MemoryModel();
        memoryModel.method1();
        memoryModel.method2();
        System.out.println(memoryModel);

    }
}
