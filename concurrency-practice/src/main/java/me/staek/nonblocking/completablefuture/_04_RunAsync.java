package me.staek.nonblocking.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * runAsync
 * 인자를 받지도 리턴하지도 않음
 */
public class _04_RunAsync {
    public static void main(String[] args) {

        MyService2 service = new MyService2();

        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 가 비동기 작업을 시작합니다");
            service.getData().stream().forEach(System.out::println);
        });

        cf.join();
        System.out.println("메인 스레드 종료");
    }
}

class MyService2 {

    public List<Integer> getData() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Arrays.asList(1, 2, 3);
    }
}
