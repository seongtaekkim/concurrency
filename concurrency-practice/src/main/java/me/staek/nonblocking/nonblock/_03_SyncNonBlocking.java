package me.staek.nonblocking.nonblock;

import java.util.concurrent.*;

/**
 * synchronous & nonblocking
 * <p>
 * synchronous - 작업을 비동기로 실행했으나, 완료되었는지 계속확인하기에 결국 동기적으로 동작한다.
 * nonblocking - 작업요청 후 대기하지 않는다.
 */
public class _03_SyncNonBlocking {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> task = () -> {
            Thread.sleep(2000);
            return "my name is seongtaek";
        };

        // 비동기 & 논 블록킹
        Future<String> future = executor.submit(task);

        // 동기행동 - 다른 스레드 작업완료 여부를 계속검사한다
        while (!future.isDone()) {
            try {
                Thread.sleep(500);
                System.out.println("호출 스레드를 확인 ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            String result = future.get();
            System.out.println("작업 결과: " + result);
        } catch (InterruptedException | ExecutionException e) {
        }

        executor.shutdown();
    }
}
