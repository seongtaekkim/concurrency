package me.staek.threadpool.threadpoolexecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * keepAliveTime
 * <p>
 * 테스트1) 기본적으로 keepAliveTime 이후 corePoolSize 만큼 스레드가 남는다.
 * 테스트2) allowCoreThreadTimeOut -> keepAliveTime 이후 모든 스레드가 clear
 * 테스트3) keepAliveTime 이후 newFixedThreadPool 스레드가 줄어들지 않는다.
 */
public class _03_KeepAliveTime {
    public static void main(String[] args) throws InterruptedException {

        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 1L;
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(2);
        int taskNum = 6;


        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, queue);

        for (int i = 0; i < taskNum; i++) {
            final int taskId = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " 가 task" + taskId + " 를 실행하고 있습니다.");
            });
        }

        /**
         * 고정스레드는 이미생성된 스레드 clear가 안됨.
         */
//        Executors.newFixedThreadPool(20);

        /**
         * 시간 지나면 스래드 corePoolSize 까지 모두 clear
         */
//        executor.allowCoreThreadTimeOut(true);

        Thread.sleep(4000);
        executor.shutdown();
    }
}
