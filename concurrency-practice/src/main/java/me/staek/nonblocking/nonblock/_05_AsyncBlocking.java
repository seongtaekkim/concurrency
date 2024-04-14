package me.staek.nonblocking.nonblock;

import java.util.concurrent.*;

/**
 * asynchronous & blocking
 * <p>
 * asynchronous - 작업을 비동기로 실행
 * blocking - 작업요청 후 대기한다.
 */
public class _05_AsyncBlocking {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> task = () -> {
            Thread.sleep(2000);
            return "my name is staek";
        };

        // asynchronous & nonblocking
        Future<String> future = executor.submit(task);

        // blocking
        try {
            String result = future.get();
            System.out.println("작업결과 " + result);
        } catch (InterruptedException | ExecutionException e) {
        }

        System.out.println("메인스레드 작업 ...");
        executor.shutdown();
    }
}
