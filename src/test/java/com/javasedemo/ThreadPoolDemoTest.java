package com.javasedemo;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemoTest {


    /**
     * 创建一个单线程的线程池。这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。
     * 如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。此线程池保证所有任务的执行顺序按照任务的提交顺序执行。
     * 此线程池支持定时以及周期性执行任务的需求。
     */
    @Test
    public void testSingleThreadExecutor() {
        /*
        public ScheduledThreadPoolExecutor(int corePoolSize) {
          super(corePoolSize,
            Integer.MAX_VALUE,
            0,
            NANOSECONDS,
            new DelayedWorkQueue());

         static class DelayedWorkQueue extends AbstractQueue<Runnable>
                implements BlockingQueue<Runnable>
         */
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
            service.execute(() -> {
                Thread thread = Thread.currentThread();
                System.out.println(thread.getName() + " ");
            });
        }
        service.shutdown();
    }

    /**
     * 创建固定大小的线程池。每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。
     * 线程池的大小一旦达到最大值就会保持不变，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程。
     */
    @Test
    public void testFixedThreadPool() {
        /*
                return new ThreadPoolExecutor(nThreads,
                                            nThreads,
                                            0L,
                                            TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>())
         */
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }

            service.execute(() -> {
                Thread thread = Thread.currentThread();
                System.out.println(thread.getName() + " ");

            });
        }
        service.shutdown();
    }

    /**
     * 创建一个可缓存的线程池。如果线程池的大小超过了处理任务所需要的线程，那么就会回收部分空闲（60秒不执行任务）的线程，
     * 当任务数增加时，此线程池又可以智能的添加新线程来处理任务。此线程池不会对线程池大小做限制，
     * 线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小
     */
    @Test
    public void testCachedThreadPool() {
        /*
            public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0,
                                    Integer.MAX_VALUE,
                                      60L,
                                      TimeUnit.SECONDS,
                                      new SynchronousQueue<Runnable>());
         */
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            service.execute(() -> {
                Thread thread = Thread.currentThread();
                System.out.println(thread.getName() + " ");

            });
        }
    }

    /**
     * 创建一个大小无限的线程池。此线程池支持定时以及周期性执行任务的需求。
     * super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
     * new DelayedWorkQueue());
     */
    @Test
    public void testScheduledThreadPool() {
        /*
            public ScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize,
                Integer.MAX_VALUE,
                0,
                NANOSECONDS,
              new DelayedWorkQueue());
    }
         */
        /*
            begin :1567165034566
            schedule :142
            pool-1-thread-1
            延迟一秒执行
            sleep :2142
         */
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("begin :" + currentTimeMillis);
        service.schedule(() -> {
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName() + " ");
            System.out.println("延迟一秒执行");
        }, 1, TimeUnit.SECONDS);
        System.out.println("schedule :" + (System.currentTimeMillis() - currentTimeMillis));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sleep :" + (System.currentTimeMillis() - currentTimeMillis));
    }
}