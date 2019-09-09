package com.liutao62.demo;

public class FinallyTest {

    public static void main(String[] args) {
        System.out.print(fun1());

        try {
        }finally {
            int j = 1 / 0;
            System.out.println("111");
        }
    }

    public static String fun1() {
        try {
            System.out.print("A");
            return fun2();
        } finally {
            System.out.print("B");
        }
    }

    public static String fun2() {
        System.out.print("C");
        return "D";
    }
}