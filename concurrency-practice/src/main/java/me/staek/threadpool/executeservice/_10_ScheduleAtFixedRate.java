package me.staek.threadpool.executeservice;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 초기 지연 시간 이후에 Runnable 작업을 주기적으로 실행하도록 예약하고 ScheduledFuture 를 반환한다. 이후에 주어진 주기로 실행된다
 *
 * command: 실행할 작업, initialDelay: 첫 번째 실행을 지연할 시간, period : 연속적인 실행 사이의 주기, unit: initialDelay와 period 매개변수의 시간 단위
 */
public class _10_ScheduleAtFixedRate {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
        Runnable task = () -> {
            try {
                Thread.sleep(2000);
                System.out.println("thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                // 예외 처리
            }
        };

        // 처음 1 초가 지난 후 실행 되고 지정된 주기 마다 계속 실행 된다
        // 스레드 풀이 3개의 스레드를 가지고 있으므로, 각 스레드가 1초 간격으로 번갈아가면서 작업을 실행한다. 이렇게 하면 스레드 간에 작업이 겹치지 않고 균등하게 분산된다
        ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);


        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        future.cancel(true); // 작업을 취소하면 인터럽트 되어 스케줄링이 중지된다
        scheduler.shutdown();
    }
}
