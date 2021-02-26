package com.liutao62.art_of_concurrent_programming;

/**
 * @author liutao
 * @date Created in 2021/2/26 22:10
 * @description
 */
public class InterruptedTest {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                SleepUtils.second(1);
            }
            System.out.println("--------------- will finish");
        }, "thread - 1");
        System.out.println("-- thread1 isInterrupted :" + thread1.isInterrupted());
        thread1.start();
        SleepUtils.second(1);
        thread1.interrupt();
        System.out.println(" thread1 isInterrupted :" + thread1.isInterrupted());
        SleepUtils.second(1);
        System.out.println(" thread1 isInterrupted after :" + thread1.isInterrupted());
        SleepUtils.second(2000);
        System.out.println("finished");
        /**
         * -- thread1 isInterrupted :false
         * 0
         * 1
         *  thread1 isInterrupted :true             可能为 true，也可能为 false
         * thread is interrupted
         * 2
         * 3
         *  thread1 isInterrupted after :false
         * 4
         * 5
         * 6
         * 7
         * 8
         * 9
         * --------------- will finish
         * finished
         */
    }
}
