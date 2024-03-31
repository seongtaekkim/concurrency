package me.staek.threadpool.executeservice;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 주어진 지연 시간 이후에 Runnable 작업을 예약하고 ScheduledFuture를 반환한다. 예약된 작업은 한 번만 실행된다
 * command: 실행할 작업, delay: 실행을 지연할 시간, unit: 지연 매개변수의 시간 단위
 */
public class _08_ScheduleRunnable {
    public static void main(String[] args) {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            System.out.println("작업이 한번 실행 됩니다");
        };

        // 주어진 시간 후에 작업을 실행
        scheduler.schedule(task, 2, TimeUnit.SECONDS);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scheduler.shutdown();
    }
}
