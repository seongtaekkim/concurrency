package me.staek.threadpool.executeservice;

import java.util.concurrent.*;

/**
 * 주어진 지연 시간 이후에 Callable 작업을 예약하고 ScheduledFuture를 반환한다. 예약된 작업은 한 번만 실행된다
 *
 * callable : 실행할 함수, delay: 실행을 지연할 시간, unit: 지연 매개변수의 시간 단위
 */
public class _09_ScheduleCallable {
    public static void main(String[] args) {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Callable<String> task = () -> {
            return "작업한번실행, 결과반환";
        };

        ScheduledFuture<String> future = scheduler.schedule(task, 3, TimeUnit.SECONDS);
        try {
            String result = future.get();
            System.out.println("결과: " + result);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        scheduler.shutdown();
    }
}
