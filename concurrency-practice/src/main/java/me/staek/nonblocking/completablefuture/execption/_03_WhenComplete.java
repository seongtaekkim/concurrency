package me.staek.nonblocking.completablefuture.execption;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * whenComplete method
 * - 리턴값이 없다. (호출스레드가 예외를 처리해야 함)
 * - 앞의 작업들에 대해서 예외발생 유무로 분기처리함.
 */
public class _03_WhenComplete {
    public static void main(String[] args) throws InterruptedException {

        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
                throw new RuntimeException("runtime error");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return 10;
        }).whenComplete((r, e) -> {
            if (e != null) {
                System.out.println(Thread.currentThread().getName() + ": " + e.getMessage());
            } else {
                System.out.println("result: " + r);
            }
        });

        try {
            cf.join();
        } catch (CompletionException e) {
            System.out.println(Thread.currentThread().getName() + ": 호출자가 예외처리를 한다.");
        }
    }
}
