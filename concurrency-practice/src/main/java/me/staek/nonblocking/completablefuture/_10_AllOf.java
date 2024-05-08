package me.staek.nonblocking.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * 여러 개의 CompletableFuture를 동시에 실행하고 모든 CompletableFuture가 완료될 때까지 대기하는 데 사용된다
 * CompletableFuture 중에서 가장 오래 걸리는 작업이 완료 되기 전 까지 allOf() 는 대기한다.
 * ExecutorService 의 invokeAll() 과 유사한 개념이다
 */
public class _10_AllOf {
    public static void main(String[] args) throws InterruptedException {

        ServiceA sa = new ServiceA();
        ServiceB sb = new ServiceB();
        ServiceC sc = new ServiceC();

        CompletableFuture<Integer> cf1 = sa.getData1();
        CompletableFuture<Integer> cf2 = sb.getData2();
        CompletableFuture<Integer> cf3 = sc.getData3();

        long started = System.currentTimeMillis();
        CompletableFuture<Void> voidCf = CompletableFuture.allOf(cf1, cf2, cf3);
        CompletableFuture<Integer> finalCf = voidCf.thenApply(v -> {

            int result1 = cf1.join();
            int result2 = cf2.join();
            int result3 = cf3.join();

            System.out.println("result1 = " + result1);
            System.out.println("result2 = " + result2);
            System.out.println("result3 = " + result3);

            return result1 + result2 + result3;

        });
//        Thread.sleep(2000);
        finalCf.join();
        System.out.println("최종 소요 시간: " + (System.currentTimeMillis() - started));

//        System.out.println("최종결과: " + voidCf);
        System.out.println("최종결과: " + finalCf.join());
        System.out.println("메인 스레드 종료");
    }

    static class ServiceA {

        public CompletableFuture<Integer> getData1() {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(1000);
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
