package com.liutao62.Instrumentation;

import com.liutao62.TestBranch;

/**
 * @author liutao
 * @date 2023/10/31
 */
public class Test {

    @org.junit.Test
    public void test() throws InterruptedException {
        TestBranch testBranch = new TestBranch();
        new Thread(() -> {
            while (true) {
                try {
                    testBranch.test1();
                    Thread.sleep(200);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        }).start();
        Thread.sleep(1000);
    }
}
