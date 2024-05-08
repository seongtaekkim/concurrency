package me.staek.nonblocking.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * CompletableFuture객체가 완료상태를 관리한다 (다음 작업에 전파를 할 수 있도록)
 * - CompletableFuture는 데몬스레드로 동작함
 */
public class _03_SupplyAsync {
    public static void main(String[] args) {

        MyService service = new MyService();

        CompletableFuture<List<Integer>> cf = CompletableFuture.supplyAsync(new Supplier<List<Integer>>() {
            @Override
            public List<Integer> get() {
                System.out.println(Thread.currentThread().getName() + " : 비동기작업 시작");
                return service.getData();
            }
        });

        List<Integer> result = cf.join();
        result.stream().forEach(r -> System.out.println(r));

        System.out.println("===============================================================================");

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<List<Integer>> future = executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " 가 비동기 작업을 시작합니다");
            return service.getData();
        });

        try {
            List<Integer> result2 = future.get();
            result2.stream().forEach(r -> System.out.println(r));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("메인 스레드 종료");
        executorService.shutdown();
    }
}

class MyService {

    public List<Integer> getData() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Arrays.asList(1, 2, 3);
    }
}
