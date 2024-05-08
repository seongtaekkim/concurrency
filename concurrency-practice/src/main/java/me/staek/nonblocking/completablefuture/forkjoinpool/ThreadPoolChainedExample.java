package me.staek.nonblocking.completablefuture.forkjoinpool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

/**
 * 계산 집약적인 작업에 대해 ForkJoinPool을 사용하면, 여러 프로세서 코어를 활용하여 작업 부하를 나눌 수 있어 더 효율적입니다.
 * I/O 바인드 작업의 경우 고정 스레드 풀이 있는 ExecutorService와 같은 다른 접근 방식을 사용하는 것이 일반적으로 더 좋습니다.
 */
public class ThreadPoolChainedExample {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors() - 1);

        CompletableFuture.supplyAsync(() -> {
                    System.out.println("IO Bound 작업: " + Thread.currentThread().getName());
                    return 1;
                }, executor)

                .thenApplyAsync(res -> {
                    System.out.println("CPU Bound 작업: " + Thread.currentThread().getName());
                    return res + 1;
                })

                .thenApplyAsync(res -> {
                    System.out.println("IO Bound 작업: " + Thread.currentThread().getName());
                    return res + 2;

                }, executor)

                .thenApplyAsync(res -> {
                    System.out.println("CPU Bound 작업: " + Thread.currentThread().getName());
                    return res + 3;
                })

                .thenComposeAsync(res -> {
                    System.out.println("병렬 처리: " + Thread.currentThread().getName());
                    return CompletableFuture.supplyAsync(() -> res + 4);

                })

                .thenAcceptAsync(res -> {
                    System.out.println("병렬 처리: " + Thread.currentThread().getName());
                    System.out.println("최종 결과 : " + res);
                }).join();

        executor.shutdown();
        forkJoinPool.shutdown();
    }
}
