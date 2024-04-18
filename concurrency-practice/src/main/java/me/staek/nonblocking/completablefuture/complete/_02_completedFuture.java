package me.staek.nonblocking.completablefuture.complete;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture.completedFuture()
 * - 호출스레드에서 결과값을 세팅하는 정적 메서드
 */
public class _02_completedFuture {
    public static void main(String[] args) {

        CompletableFuture<String> cf = CompletableFuture.completedFuture(" ha....");
        CompletableFuture<Void> cf2 = cf.thenAccept(r -> {
            System.out.println("ret: " + r);
        });

        /**
         * 아래 식과 같은 의미라고 봐도 된다
         */
        // CompletableFuture<String> cf2 = new CompletableFuture<>();
        // cf2.complete("Hello World");
    }
}
