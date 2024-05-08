package me.staek.nonblocking.completablefuture;

import java.util.concurrent.*;

/**
 * Future
 * - 여러작업이 의존되어있을 경우 동작하는 방식확인
 * - 개별 작업은 비동기 동작하지만, 작업 간 순차적으로(블로킹) 동작한다.
 * - 메인스레드가 get()을 호출하면 블로킹되된다.
 * - 작업실행을 위해 스레드풀, 예외처리, 작업을 작성해야한다.
 */
public class _01_Future {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Future<Integer> future1 = executorService.submit(new Service1());
        Future<Integer> future2 = executorService.submit(new Service2(future1));
        Future<Integer> future3 = executorService.submit(new Service3(future2));
        Future<Integer> future4 = executorService.submit(new Service4(future3));
        Future<Integer> future5 = executorService.submit(new Service5(future4));

        int finalResult = future5.get();

        executorService.shutdown();

        System.out.println("결과: " + finalResult);
    }

    static class Service1 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("Service 1 시작");
            return 1;
        }
    }

    static class Service2 implements Callable<Integer> {
        private Future<Integer> future;

        Service2(Future<Integer> future) {
            this.future = future;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("Service 2 시작");
            return future.get() * 2;
        }
    }

    static class Service3 implements Callable<Integer> {
        private Future<Integer> future;

        Service3(Future<Integer> future) {
            this.future = future;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("Service 3 시작");
            return future.get() * 2;
        }
    }

    static class Service4 implements Callable<Integer> {
        private Future<Integer> future;

        Service4(Future<Integer> future) {
            this.future = future;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("Service 4 시작");
            return future.get() * 2;
        }
    }

    static class Service5 implements Callable<Integer> {
        private Future<Integer> future;

        Service5(Future<Integer> future) {
            this.future = future;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("Service 5 시작");
            return future.get() * 2;
        }
    }
}
