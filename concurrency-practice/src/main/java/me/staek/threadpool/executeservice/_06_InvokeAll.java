package me.staek.threadpool.executeservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * ExecutorService - invokeAll
 *
 * - Collable 들을 Collection 형태로 담아서 한번에 처리할 수 있다.
 * - invokeAll 내부에서 get() 메서드가 등록한 작업 순서대로 실행하므로 호출자 입장에서는 완료될 때까지 blocking되어 있다.
 * - 예외발생에 대해서도 모든 작업이 완료된 후에 파악이 가능하다.
 */
public class _06_InvokeAll {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Callable<Integer>> taskList = new ArrayList<>();

        taskList.add(() -> { Thread.sleep(3000); return 1; });
        taskList.add(() -> { Thread.sleep(2000); return 1; });
        taskList.add(() -> { throw new RuntimeException("invokeAll exception!!"); });
        taskList.add(() -> { Thread.sleep(1000); return 1; });

        long started = 0;
        try {
            started = System.currentTimeMillis();
            List<Future<Integer>> results = executor.invokeAll(taskList);
            System.out.println("blocking...");
            for (Future<Integer> future : results) {
                try {
                    Integer value = future.get();
                    System.out.println("result: " + value);
                } catch (ExecutionException e) {
                    // 작업 중 예외가 발생한 경우 처리
                    Throwable cause = e.getCause();
                    if (cause instanceof RuntimeException) {
                        System.err.println("exception: " + cause.getMessage());
                    } else {
                        e.printStackTrace();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            executor.shutdown();
        }

        System.out.println("시간:"  + (System.currentTimeMillis() - started ));
    }
}
