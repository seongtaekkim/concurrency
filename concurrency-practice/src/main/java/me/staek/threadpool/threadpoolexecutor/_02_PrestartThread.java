package me.staek.threadpool.threadpoolexecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * prestartCoreThread
 * <p>
 * 테스트1) 작업생성 없을 때 생성된 스레드 0개 확인
 * 테스트2) prestartCoreThread : 1개 미리 생성 확인, 호출만큼 생성됨 (max : corePoolSize)
 * 테스트3) prestartAllCoreThreads : corePoolSize 만큼 미리 생성  확인
 */
public class _02_PrestartThread {
    public static void main(String[] args) {

        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 0L;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        int taskNum = 9;

        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

        executor.prestartCoreThread();
        executor.prestartCoreThread();

//        executor.prestartAllCoreThreads();

        for (int i = 0; i < taskNum; i++) {
            final int taskId = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " 가 태스크" + taskId + " 를 실행하고 있습니다.");
            });
        }

        executor.shutdown();

    }
}
