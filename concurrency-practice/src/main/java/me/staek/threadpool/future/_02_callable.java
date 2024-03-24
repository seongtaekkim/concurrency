package me.staek.threadpool.future;

import java.util.concurrent.*;


/**
 * Callable<T>
 * - ExecutorService 타입에서 submit 인자로 Callable 타입의 작업을 넘겨 수행할 수 있다.
 * - Future 구현체에서 Callable 수행하고 전달한 타입을 리턴한다.
 * - 예외도 던진다.
 *
 * - Callable 작업 후 리턴에 결과가 오는게 아니라, get() 을 호출하여 비동기형태로 리턴값을 조회할 수 있다.
 */
public class _02_callable {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<Integer> callable = () -> {
            System.out.println(Thread.currentThread().getName() + " 작업중");
            return 100;
        };

        Future<Integer> future = executor.submit(callable);

        try {
            Integer i = future.get();
            System.out.println(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        executor.shutdown();
        System.out.println("main 종료");
    }
}
