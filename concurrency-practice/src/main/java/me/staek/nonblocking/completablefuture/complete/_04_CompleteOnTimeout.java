package me.staek.nonblocking.completablefuture.complete;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * complete - completeOnTimeout
 */
public class _04_CompleteOnTimeout {
    public static void main(String[] args) {

        getData().completeOnTimeout("Hello Java", 2, TimeUnit.SECONDS)
                .thenAccept(r -> {
                    System.out.println("r = " + r);
                }).join();
    }

    private static CompletableFuture<String> getData() {

        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                return "Hello World";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
