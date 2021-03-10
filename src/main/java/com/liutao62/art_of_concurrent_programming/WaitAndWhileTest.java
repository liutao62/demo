package com.liutao62.art_of_concurrent_programming;

/**
 * @author liutao
 * @date Created in 2021/2/26 22:44
 * @description
 */
public class WaitAndWhileTest {
    public static void main(String[] args) {
        Object o = new Object();
//        whileMethod(o);
//        waitMethod(o);
        System.out.println(16 >>> 16);
    }

    static void whileMethod(Object obj) {
        boolean flag = true;
        try {
            synchronized (obj) {
                while (true) {

                }
            }
        } catch (Exception e) {

        }

    }

    static void waitMethod(Object obj) {
        try {
            synchronized (obj) {
                obj.wait();
            }
        } catch (Exception e) {
            System.out.println("------");
        }
    }

}
