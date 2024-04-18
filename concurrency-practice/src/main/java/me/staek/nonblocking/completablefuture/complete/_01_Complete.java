package me.staek.nonblocking.completablefuture.complete;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CompletableFuture 인스턴스를 생성했을때, 작업이 종료될까?
 */
public class _01_Complete {
    public static void main(String[] args) {

        MakeCF service = new MakeCF();
        CompletableFuture<Integer> cf = service.doTask();
        CompletableFuture<Integer> cf2 = cf.thenApply(r -> r + 20);

        System.out.println("result: " + cf.join());
        System.out.println(cf2.join());
        System.out.println("메인 스레드 종료");
    }

    static class MakeCF {
        public CompletableFuture<Integer> doTask() {
            CompletableFuture<Integer> cf = new CompletableFuture<>();

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                /**
                 * complete() 를 호출하지 않으면 CompletableFuture의 result가 null이어서
                 * 무한대기 하게된다.
                 */
                cf.complete(42);
                /**
                 * complete()를 두번 호출한다고해서 적용되지 않는다. (상태가 CAS로 관리되어 갱신되지 않음)
                 */
                cf.complete(84);
            });
            return cf;
        }
    }
}
