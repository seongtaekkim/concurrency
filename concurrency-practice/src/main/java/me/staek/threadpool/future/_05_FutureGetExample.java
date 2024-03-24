package me.staek.threadpool.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * future - get()
 * get() 호출 이후 메인스레드는 결과가 리턴될 때까지 blocking 되어 synchronized 형태로 동작하게 된다.
 */
public class _05_FutureGetExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Callable 작업 생성
        Callable<Integer> callableTask = () -> {
            System.out.println("비동기 작업 시작...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("비동기 작업 완료.");
            return 42;
        };

        Future<Integer> future = executorService.submit(callableTask);

        try {
            Integer result = future.get();
            System.out.println("Result: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
