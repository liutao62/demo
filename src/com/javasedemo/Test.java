package com.javasedemo;

public class Test {
    public static void main(String[] args) {
        try {
            test(new int[]{1, 2, 3});
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void test(int[] array) {
        for (int i = 0; i < array.length; i++) {
            try {
                if (array[i] % 2 == 0) throw new NullPointerException();
                else System.out.println(i);
            }finally {
                System.out.println("E");
            }
        }
    }
}
