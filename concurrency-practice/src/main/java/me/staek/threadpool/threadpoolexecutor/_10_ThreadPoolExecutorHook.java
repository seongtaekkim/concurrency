package me.staek.threadpool.threadpoolexecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolExecutorHook
 * - beforeExecute, afterExecute, terminated 테스트
 */
public class _10_ThreadPoolExecutorHook extends ThreadPoolExecutor {

    public _10_ThreadPoolExecutorHook(int corePoolSize, int maxPoolSize, long keepAliveTime, TimeUnit timeUnit, LinkedBlockingQueue<Runnable> queue) {
        super(corePoolSize, maxPoolSize, keepAliveTime, timeUnit, queue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println(t.getName() + " 가 작업 실행 전");
        super.beforeExecute(t, r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        if (t != null) {
            System.out.println("task: " + t.getMessage() + " Except");
        } else {
            System.out.println("task succeed");
        }
        super.afterExecute(r, t);
    }

    @Override
    protected void terminated() {
        System.out.println("thread pool terminated");
        super.terminated();
    }

    public static void main(String[] args) {

        int corePoolSize = 2;
        int maxPoolSize = 2;
        long keepAliveTime = 0L;
        int capacity = 2;

        _10_ThreadPoolExecutorHook executor = new _10_ThreadPoolExecutorHook(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(capacity));

        for (int i = 1; i <= 4; i++) {
            final int taskId = i;
            executor.execute(() -> {
                System.out.println("Task " + taskId + " is running on thread " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
    }
}
