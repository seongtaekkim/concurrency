package me.staek.threadpool.executeservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  ExecutorService - invokeAny
 *
 *  - 등록한 작업 중 가장먼저 완료한 결과를 리턴한다
 *  - state가 NORMAL인 상태만 가능하다.
 *  - 이때 main의 blocking은 풀린다.
 */
public class _07_InvokeAny {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Callable<String>> tasks = new ArrayList<>();

        tasks.add(() -> { Thread.sleep(2000); return "Task 1"; });
        tasks.add(() -> { Thread.sleep(1000); throw new RuntimeException("error"); });
        tasks.add(() -> { Thread.sleep(3000); return "Task 3"; });
        long started = 0;
        try {
            started = System.currentTimeMillis();
            String result = executor.invokeAny(tasks);
            System.out.println("result: " + result);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

        System.out.println("시간:"  + (System.currentTimeMillis() - started ));
    }
}
