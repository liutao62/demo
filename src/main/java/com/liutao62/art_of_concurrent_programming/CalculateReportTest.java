package com.liutao62.art_of_concurrent_programming;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author liutao
 * @date Created in 2021/2/28 23:01
 * @description
 */
public class CalculateReportTest {

    private static ExecutorService EXECUTOR = new ThreadPoolExecutor(3, 3, 0L,
            TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(3),
            new ThreadFactoryBuilder().setNameFormat("日月报计算线程池-%s").build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

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
            Future<?> submit = EXECUTOR.submit(new CalculateRptTask(i));
        }

        if (mod > 1){
            EXECUTOR.submit(new CalculateRptTask(100));
        }

//        System.out.println(submit.get());

    }
}

class CalculateRptTask implements Callable {

    private Object returnObj;

    private int param;

    public CalculateRptTask(int param) {
        this.param = param;
    }

    @Override
    public Object call() throws Exception {
        Object o = calRpt(param);
        return o;
    }

    private Object calRpt(int param) {
        SleepUtils.second(100);
        String message = Thread.currentThread().getName() + " 计算完成 " + (param * 10);
        System.out.println(message);
        return message;
    }

}