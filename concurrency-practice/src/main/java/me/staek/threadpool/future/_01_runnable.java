package me.staek.threadpool.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Runnable
 *
 * - ExecutorService 타입에서 execute 인자로 Runnable 타입의 작업을 넘겨 수행할 수 있다.
 */
public class _01_runnable {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + " 진행중");
        };

        executor.execute(runnable);
        executor.shutdown();
    }
}
