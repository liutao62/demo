package com.liutao62.art_of_concurrent_programming;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author liutao
 * @date Created in 2021/3/1 19:00
 * @description
 */
public class CalRptTestNew implements Runnable {

    /**
     * 创建4个屏障，处理完之后执行当前类的run方法
     */
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(4, this);

    private static Executor EXECUTOR = new ThreadPoolExecutor(3, 3, 0L,
            TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(3),
            new ThreadFactoryBuilder().setNameFormat("日月报计算线程池-%s").build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    // 保存每个线程计算的结果
    private static ConcurrentHashMap<Integer, Object> calRptResult = new
            ConcurrentHashMap<>();

    public static void main(String[] args) {
        // 6核cpu
        System.out.println(Runtime.getRuntime().availableProcessors());
        try {
            calculateReport();
            System.out.println("计算完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void calculateReport() throws ExecutionException, InterruptedException {

        int total = 207;

        int count = total / 10;
        int mod = total % 10;

        CyclicBarrier cyclicBarrier = new CyclicBarrier(count + (mod > 0 ? 1 : 0));

        for (int i = 0; i < count; i++) {
            int finalI = i;
            EXECUTOR.execute(() -> {
                calRptResult.put(finalI, calRpt(finalI));
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        if (mod > 1) {
            EXECUTOR.execute(() -> {
                calRptResult.put(-1, calRpt(-1));
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static Object calRpt(int param) {
        SleepUtils.second(100);
        String message = Thread.currentThread().getName() + " 计算完成 " + (param * 10);
        System.out.println(message);
        return message;
    }

    @Override
    public void run() {
        System.out.println(calRptResult);
    }
}
