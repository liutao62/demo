package com.liutao62.art_of_concurrent_programming;

import org.junit.Assert;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author liutao
 * @date Created in 2021/4/25 22:23
 * @description
 */
public class ForkJoinPoolTest {
    // private static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();
    private static final ForkJoinPool FORK_JOIN_POOL = ForkJoinPool.commonPool();

    private static final int PARAM_SIZE = 120000000;

    private static int[] PARAMS = new int[PARAM_SIZE];

    static void initParams() {
        for (int i = 0; i < PARAMS.length; i++) {
            PARAMS[i] = ThreadLocalRandom.current().nextInt(20);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            initParams();
            StopWatch watch = new StopWatch("calculate compare");
            watch.start("forkJoinPool");
            long result = FORK_JOIN_POOL.invoke(new ForkJoinTask(PARAMS, 0, PARAMS.length - 1));
            System.out.println("--------forkJoinPool result = " + result);
            watch.stop();

            watch.start("stream sum");
            result = Arrays.stream(PARAMS).sum();
            System.out.println("--------stream sum result = " + result);
            watch.stop();

            watch.start("stream parallel sum");
            result = Arrays.stream(PARAMS).parallel().sum();
            System.out.println("--------stream parallel sum result = " + result);
            watch.stop();

            watch.start("ExecutorService");
            CalcalateService calculateService = new ExecutorTask();
            result = calculateService.calculate(PARAMS);
            System.out.println("--------ExecutorService result = " + result);
            watch.stop();
            System.out.println();

            System.out.println(watch.prettyPrint());

        }
    }


    static class ForkJoinTask extends RecursiveTask<Long> {

        private static final int EXECUTE_CPU_CORE_NUM = Runtime.getRuntime().availableProcessors() - 1;

        private int[] params;
        private int from;
        private int to;

        public ForkJoinTask(int[] params, int from, int to) {
            Assert.assertNotNull(params);
            this.params = params;
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            if (to - from < 20000000) {
                int result = 0;
                for (int i = from; i <= to; i++) {
                    result += params[i];
                }
                return Long.valueOf(result);
            } else {
                int middle = (from + to) >> 1;
                ForkJoinTask leftTask = new ForkJoinTask(params, from, middle - 1);
                ForkJoinTask rightTask = new ForkJoinTask(params, middle, to);
                leftTask.fork();
                rightTask.fork();
                return leftTask.join() + rightTask.join();
            }
        }
    }

    static class ExecutorTask implements CalcalateService, Callable<Long> {

        private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        private int[] numbers;
        private int from;
        private int to;

        public ExecutorTask() {
        }

        public ExecutorTask(int[] numbers, int from, int to) {
            this.numbers = numbers;
            this.from = from;
            this.to = to;
        }

        @Override
        public Long call() {
            long total = 0;
            for (int i = from; i <= to; i++) {
                total += numbers[i];
            }
            return total;
        }

        @Override
        public long calculate(int[] numbers) {
            List<ExecutorTask> taskList = new ArrayList<>();

            // 把任务分解为 n 份
            int cpuCoreNum = Runtime.getRuntime().availableProcessors();
            int part = numbers.length / cpuCoreNum;
            for (int i = 0; i < cpuCoreNum; i++) {
                int from = i * part; //开始位置
                int to = (i == cpuCoreNum - 1) ? numbers.length - 1 : (i + 1) * part - 1; //结束位置

                taskList.add(new ExecutorTask(numbers, from, to));
            }
            long result = 0;
            try {
                List<Future<Long>> futures = EXECUTOR_SERVICE.invokeAll(taskList);
                for (Future<Long> future : futures) {
                    result += future.get();
                }
            } catch (InterruptedException e) {
                System.out.println("EXECUTOR_SERVICE invoke all fail");
            } catch (ExecutionException e) {
                System.out.println("future get error");
            }

            // 把每个线程的结果相加，得到最终结果 get()方法 是阻塞的
            // 优化方案：可以采用CompletableFuture来优化  JDK1.8的新特性
            return result;
        }

        @Override
        public long calculateByCompletableFuture(int[] numbers) {
            this.numbers = numbers;
            // CompletableFuture.runAsync(null);
            return 0;
        }
    }
}

/**
 * 做个接口暴露出来计算更装逼
 */
interface CalcalateService {
    long calculate(int[] numbers);

    long calculateByCompletableFuture(int[] numbers);
}