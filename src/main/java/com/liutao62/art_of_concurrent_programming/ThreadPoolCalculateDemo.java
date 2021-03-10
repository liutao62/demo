package com.liutao62.art_of_concurrent_programming;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liutao
 * @date Created in 2021/3/1 18:46
 * @description
 */
public class ThreadPoolCalculateDemo {

    private CyclicBarrier cyclicBarrier;

    private Executor executor;

    final int poolCoreNum = Runtime.getRuntime().availableProcessors() >> 1;

    public ThreadPoolCalculateDemo() {
        executor = new ThreadPoolExecutor(poolCoreNum, poolCoreNum, 0L,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(poolCoreNum),
                new ThreadFactoryBuilder().setNameFormat("日月报计算线程池-%s").build(),
                new ThreadPoolExecutor.CallerRunsPolicy());

    }

    private ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();

    private int calRpt(List<Integer> nums) {
        Integer reduce = nums.stream().reduce(0, (a, b) -> a + b);
        return reduce;
    }

    private void count() {

        List<Integer> nums = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24
                , 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44)
                .collect(Collectors.toList());

        Integer reduce = nums.stream().reduce(0, (a, b) -> a + b);
        System.out.println("total num = " + reduce);

        int size = nums.size();
        int wishCount = size / 10;
        int realCount = wishCount > poolCoreNum ? poolCoreNum : wishCount;
        cyclicBarrier = new CyclicBarrier(realCount + 1);

        int onceSize = size / realCount;

        for (int i = 0; i < realCount; i++) {

            List<Integer> integers = nums.subList(i * onceSize, (i + 1) * onceSize);

            if (onceSize != 10 && i + 1 == realCount) {
                integers = nums.stream().skip(i * onceSize).collect(Collectors.toList());
            }

            System.out.println(integers.toString());
            List<Integer> finalIntegers = integers;
            executor.execute(() -> {
                        SleepUtils.second(1000);

                        int onceResult = calRpt(finalIntegers);
                        queue.add(onceResult);

                        try {
                            cyclicBarrier.await();
                        } catch (InterruptedException |
                                BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        int result = 0;
        for (Object o : queue) {
            result += (Integer) o;
        }
        System.out.println("thread pool calculate result = " + result);

    }

    public static void main(String[] args) {
        ThreadPoolCalculateDemo bankWaterCount = new ThreadPoolCalculateDemo();
        bankWaterCount.count();
    }
}
