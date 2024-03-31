package me.staek.threadpool.executeservice;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 초기 지연 이후에 Runnable 작업을 주기적으로 실행하도록 예약하고 ScheduledFuture를 반환한다. 작업이 완료되고 나서 지연 시간 후 실행된다
 *
 * command: 실행할 작업, initialDelay: 첫 번째 실행을 지연할 시간, delay: 연속적인 실행 사이의 지연 시간, unit: initialDelay 및 delay 파라미터의 시간 단위
 */
public class _11_ScheduleWithFixedDelay {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

        Runnable task = () -> {
            try {
                Thread.sleep(1000);
                System.out.println("thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                // 예외 처리
            }
        };

        // 처음 1 초가 지난 후 실행 되고 작업이 완료 되고 나서 지정된 주기 마다 계속 실행 된다
        ScheduledFuture<?> future = scheduler.scheduleWithFixedDelay(task, 1, 1, TimeUnit.SECONDS);

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        future.cancel(true); // 작업을 취소하면 인터럽트 되어 스케줄링이 중지된다
        scheduler.shutdown();
    }
}
