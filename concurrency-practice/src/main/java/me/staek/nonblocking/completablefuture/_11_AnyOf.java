package me.staek.nonblocking.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * 여러 개의 CompletableFuture 중에서 가장 먼저 완료되는 하나의 CompletableFuture를 반환하는 메서드이다
 * 병렬 작업을 수행하고 가장 먼저 결과를 얻고자 할 때사용
 * <p>
 * - ExecutorService 의 invokeAny() 과 유사한 개념이다
 */
public class _11_AnyOf {
    public static void main(String[] args) {

        ServiceA sa = new ServiceA();
        ServiceB sb = new ServiceB();
        ServiceC sc = new ServiceC();

        CompletableFuture<Integer> cf1 = sa.getData1();
        CompletableFuture<Integer> cf2 = sb.getData2();
        CompletableFuture<Integer> cf3 = sc.getData3();

        long started = System.currentTimeMillis();
        CompletableFuture<Object> finalCf = CompletableFuture.anyOf(cf1, cf2, cf3);
        finalCf.thenApply(result -> {
            return (int) result * 10;

        });
//        Thread.sleep(2000);
        finalCf.join();
        System.out.println("최종 소요 시간: " + (System.currentTimeMillis() - started));

        System.out.println("최종결과: " + finalCf.join());
        System.out.println("메인 스레드 종료");
    }

    static class ServiceA {

        public CompletableFuture<Integer> getData1() {
            // 비동기 작업 시뮬레이션
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(500);
                    System.out.println("비동기 작업 시작 1");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return 10;
            });
        }
    }

    static class ServiceB {

        public CompletableFuture<Integer> getData2() {
            // 비동기 작업 시뮬레이션
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(2000);
                    System.out.println("비동기 작업 시작 2");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return 20;
            });
        }
    }

    static class ServiceC {

        public CompletableFuture<Integer> getData3() {
            // 비동기 작업 시뮬레이션
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("비동기 작업 시작 3");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return 30;
            });
        }
    }
}
