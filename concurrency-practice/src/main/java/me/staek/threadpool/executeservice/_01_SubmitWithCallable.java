package me.staek.threadpool.executeservice;

import java.util.concurrent.*;


/**
 * ExecutorService - submit(Callable)
 *
 * 예외를 던질 수 있고, 리턴값이 존재하는 Future를 이용한 비동기작업이다.
 */
public class _01_SubmitWithCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("작업시작");
                return 100;
            }
        });

        int result = future.get();
        System.out.println("작업 결과: " + result);

        executorService.shutdown();
    }
}
