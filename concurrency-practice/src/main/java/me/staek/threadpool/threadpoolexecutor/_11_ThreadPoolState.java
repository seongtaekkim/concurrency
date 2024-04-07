package me.staek.threadpool.threadpoolexecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolExecutor state 테스트
 * - SHUTDOWN,TIDYING, STOP, RUNNING, TERMINATED
 */
public class _11_ThreadPoolState {
    public static void main(String[] args) throws InterruptedException {
        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 2;
        int workQueueCapacity = 2;

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(workQueueCapacity));

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

        // 스레드 풀 종료
        executor.shutdown();
        System.out.println("===============================================");

        printThreadPoolState(executor);

        System.out.println("===============================================");

        // 스레드 풀이 종료될 때까지 대기
        boolean terminated = executor.awaitTermination(1, TimeUnit.SECONDS);

        if (!terminated) {
            // 스레드 풀 종료
            executor.shutdownNow();
        }

        System.out.println("===============================================");

        // 최종 스레드 풀 상태 출력
        printThreadPoolState(executor);

        System.out.println("===============================================");
        Thread.sleep(3000);
    }

    private static void printThreadPoolState(ThreadPoolExecutor executor) {
        if (executor.isShutdown())
            System.out.println("ThreadPoolExecutor is SHUTDOWN");
        if (executor.isTerminating())
            System.out.println("ThreadPoolExecutor is TIDYING");
        if (executor.isTerminated())
            System.out.println("ThreadPoolExecutor is TERMINATED");
        if (executor.isShutdown() && executor.isTerminated())
            System.out.println("ThreadPoolExecutor is STOP");
        if (executor.getActiveCount() > 0)
            System.out.println("ThreadPoolExecutor is RUNNING");
    }
}
