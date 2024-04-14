package me.staek.nonblocking.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture
 * - 여러작업이 의존되어있을 경우 동작하는 방식확인
 * - 개별 작업은 비동기 동작하지만, 작업 간 순차적으로(블로킹) 동작한다.
 * - 메인스레드가 get()을 호출하면 블로킹되된다.
 * - 작업실행을 위해 작업만 작성하면 된다.
 */
public class _02_CompletableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int finalResult = CompletableFuture.supplyAsync(() -> {
            System.out.println("Service 1 시작");
            return 1;
        }).thenApplyAsync(result1 -> {
            System.out.println("Service 2 시작");
            return result1 * 2;
        }).thenApplyAsync(result2 -> {
            System.out.println("Service 3 시작");
            return result2 * 2;
        }).thenApplyAsync(result3 -> {
            System.out.println("Service 4 시작");
            return result3 * 2;
        }).thenApplyAsync(result4 -> {
            System.out.println("Service 5 시작");
            return result4 * 2;
        }).get();

        System.out.println("결과: " + finalResult);
    }
}
