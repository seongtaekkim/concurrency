package me.staek.nonblocking.completablefuture.execption;

import java.util.concurrent.CompletableFuture;

/**
 * exceptionally method
 * - 리턴값이 존재한다. (예외 처리 후 리턴됨.)
 * - 앞의 작업들에 대한 예외를 처리
 * - 예외가 발생할 대만 해당 메서드가 실행된다.
 */
public class _01_Exceptionally {
    public static void main(String[] args) {

        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(500);
                        throw new IllegalArgumentException("error");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return 10;
                })
                .thenApply(r -> r + 20)
                .exceptionally(e -> {
                    System.out.println("Exception: " + e.getMessage());
                    return -1;
                });

        System.out.println("result: " + cf.join());
    }
}
