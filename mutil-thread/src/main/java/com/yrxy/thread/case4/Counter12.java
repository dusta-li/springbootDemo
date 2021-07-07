package com.yrxy.thread.case4;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author huanglaoxie(微信:yfct-8888)
 * @className Counter01
 * @description：
 * @date 2017/12/19 10:44
 */
public class Counter12 {

    // 请求总数
    public static int clientTotal = 100000000;

    // 同时并发执行的线程数
    public static int threadTotal = 1000;
    public static AtomicLong count = new AtomicLong(0);

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            count.set(0);
            count();
        }
    }

    private static void count() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);

        long t1 = System.currentTimeMillis();
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {

                }

            });
        }

        executorService.shutdown();
        long t2 = System.currentTimeMillis();
        System.out.println("Counter12 , "+String.format("结果：%s,耗时(ms)：%s", count, (t2 - t1)));
    }

    private static void add() {
        count.getAndIncrement();
    }


}
