package me.staek.nonblocking.completablefuture.execption;

import java.util.concurrent.CompletableFuture;


/**
 * handle method
 * - 리턴값이 존재한다. (예외 처리 후 리턴됨.)
 * - 앞의 작업들에 대해서 예외발생 유무로 분기처리함.
 */
public class _02_Handle {
    public static void main(String[] args) {

        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return 10;
        }).handle((r, e) -> {
            if (e != null) {
                System.out.println(Thread.currentThread().getName() + " : " + e.getMessage());
                return -1;
            }
            return r;
        });

        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
                throw new RuntimeException("runtime error");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return 20;
        }).handle((r, e) -> {
            if (e != null) {
                System.out.println(Thread.currentThread().getName() + " : " + e.getMessage());
                return -1;
            }
            return r;
        });


        CompletableFuture<Integer> cf3 = cf1.thenCombine(cf2, (r1, r2) -> {
            if (r1 == -1 || r2 == -1) {
                return -1;
            }
            return r1 + r2;
        });

        System.out.println("result: " + cf3.join());
    }
}
