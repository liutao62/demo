package com.liutao62.art_of_concurrent_programming;

/**
 * @author liutao
 * @date Created in 2021/4/28 20:49
 * @description
 */
public class CacheLinePadding {
    private static class Padding {
        // 填充 56 byte
        public volatile long p1, p2, p3, p4, p5, p6, p7;
    }

    private static class T extends Padding {
        // 8 byte
        public volatile long x = 0L;
    }

    // 缓存行64个字节是CPU同步的基本单位
    // arr 的两个对象能够确保在不同的缓存行里，避免缓存一致性导致缓存行无效
    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            for (long i = 0; i < 1000_0000L; i++) {
                arr[0].x = i;
            }
        });

        Thread t2 = new Thread(() -> {
            for (long i = 0; i < 1000_0000L; i++) {
                arr[1].x = i;
            }
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - start) / 100_0000);
    }
}
