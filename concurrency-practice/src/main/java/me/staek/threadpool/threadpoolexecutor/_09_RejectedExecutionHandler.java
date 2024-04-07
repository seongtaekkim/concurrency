package me.staek.threadpool.threadpoolexecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * RejectedExecutionHandler - Custom
 * 테스트1) 큐에 작업이 가득 차고, 스레드가 모두 작업을 처리하고 있는데 추가 작업이 있다면
 * => Custom handler 가 실행된다.
 */
public class _09_RejectedExecutionHandler {
    static int NUM = 5;

    public static void main(String[] args) {
        int corePoolSize = 2;
        int maxPoolSize = 2;
        long keepAliveTime = 0L;
        int workQueueCapacity = 2;

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(workQueueCapacity),
                new CustomRejectedExecutionHandler());


        // 작업을 제출
        for (int i = 1; i <= NUM; i++) {
            final int taskId = i;
            executor.execute(() -> {
                System.out.println("Task " + taskId + " is running on thread " + Thread.currentThread().getName());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // 스레드 풀 종료
        executor.shutdown();
    }
}

class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("task rejected");
        if (!executor.isShutdown()) {
            executor.getQueue().poll();
            executor.getQueue().offer(r);
        }
    }
}
