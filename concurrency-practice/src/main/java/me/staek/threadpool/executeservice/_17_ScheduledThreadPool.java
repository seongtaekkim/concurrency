package me.staek.threadpool.executeservice;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 주어진 지연시간 후에 명령을 실행하거나 주기적으로 실행할 수 있는 스레드 풀을 생성한다.
 *
 * 테스트) 1차작업 실행 -> 작업 cancel -> 작업 재실행 -> 작업 cancel
 */
public class _17_ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            System.out.println("작업중 ...");
        };

        int initialDelay = 0;
        int initialPeriod = 1;
        int updatedPeriod = 3;

        ScheduledFuture<?> future = executor.scheduleAtFixedRate(task, initialDelay, initialPeriod, TimeUnit.SECONDS);

        try {
            Thread.sleep(5000);
            future.cancel(true);

            // 변경된 주기로 다시 스케줄링
            future = executor.scheduleAtFixedRate(task, 0, updatedPeriod, TimeUnit.SECONDS);

            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        future.cancel(false);
        executor.shutdown();
    }
}
