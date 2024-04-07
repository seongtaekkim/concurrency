package me.staek.threadpool.threadpoolexecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * RejectedExecutionHandler - DiscardOldestPolicy
 * 테스트1) 큐에 작업이 가득 차고, 스레드가 모두 작업을 처리하고 있는데 추가 작업이 있다면
 * =>  Executor가 종료되지 않은 경우 대기큐 맨 앞에 있는 작업을 삭제하고 재시도 됨.
 */
public class _07_DiscardOldestPolicy {
    static int NUM = 5;

    public static void main(String[] args) {
        int corePoolSize = 2;
        int maxPoolSize = 2;
        long keepAliveTime = 0;
        int capacity = 2;

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(capacity),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        submitTasks(executor);
    }

    private static void submitTasks(ThreadPoolExecutor executor) {
        for (int i = 1; i <= NUM; i++) {
            final int taskId = i;
            executor.submit(() -> {
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
