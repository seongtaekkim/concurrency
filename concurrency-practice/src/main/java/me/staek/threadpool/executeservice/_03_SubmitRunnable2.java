package me.staek.threadpool.executeservice;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ExecutorService - submit(Runnable, T)
 *
 * 예외를 던질수 없지만 리턴값은 있다. (그런데 그 값을 인자로 먼저 던져야 한다.)
 * - 작업결과가 성공해야 리턴이 완료된다.
 */
public class _03_SubmitRunnable2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        String str = new String("apple");
        Future<String> future1 = executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("interrupted!!");
                throw new RuntimeException(e);
            }
        }, str);
//        future1.cancel(false);
        System.out.println("작업결과: " + future1.get());

        // 스레드 풀 종료
        executorService.shutdown();

    }
}
