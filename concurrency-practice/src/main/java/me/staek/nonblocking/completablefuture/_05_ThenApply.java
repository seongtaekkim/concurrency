package me.staek.nonblocking.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * thenApply
 * - 이전 작업이 완료되었다면 호출 스레드가 작업을 진행한다.
 * - 완료되지 않았다면 비동기로 작업을 수행한다.
 * thenApplyAsync
 * - 비동기로 작업을 수행한다.
 */
public class _05_ThenApply {
    public static void main(String[] args) {

        MyService myService = new MyService();
        long started = System.currentTimeMillis();
//        ExecutorService service = Executors.newFixedThreadPool(3);
        CompletableFuture<Integer> asyncFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("thread1:" + Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 42;
        }).thenApply(result -> {
            System.out.println("thread2:" + Thread.currentThread().getName());
            int r = myService.getData1();
            return r + result;
        }).thenApplyAsync(result -> {
            System.out.println("thread3:" + Thread.currentThread().getName());
            int r = myService.getData2();
            return r + result;
        });

        int asyncResult = asyncFuture.join();
        System.out.println("final result: " + asyncResult);
        System.out.println("시간: " + (System.currentTimeMillis() - started));
    }

    static class MyService {
        public int getData1() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 50;
        }

        public int getData2() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 60;
        }
    }
}
