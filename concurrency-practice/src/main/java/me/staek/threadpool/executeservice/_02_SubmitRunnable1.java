package me.staek.threadpool.executeservice;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ExecutorService - submit(Runnable)
 *
 * - 예외를 던질 수 없고, 리턴값이 없다.
 */
public class _02_SubmitRunnable1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("작업시작");
            }
        });

        /**
         * Runnable도 리턴타입은 있으나 리턴값은 없다.
         */
        Future<?> future2 = executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Object result2 = future2.get();
        System.out.println("비동기 작업 결과: " + result2);


        executorService.shutdown();

    }
}
