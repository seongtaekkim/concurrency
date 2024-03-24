package me.staek.threadpool.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Future - cancel
 *
 * future.cancel() -> future.get()
 * - 작업 시작 전에 취소했다면 : CancellationException
 * - 작업 시작 후에 취소했다면 : InterruptedException + CancellationException
 *
 */
public class _06_FutureCancel {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Callable 작업 생성
        Callable<Integer> callableTask = () -> {
            System.out.println("비동기 작업 시작...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("비동기 작업 완료.");
            return 42;
        };

        // 작업을 제출 하고 Future 객체를 받음
        Future<Integer> future = executorService.submit(callableTask);

//        while (!future.isDone()) {
//            System.out.println("작업 중 ...");
//            Thread.sleep(500);
//        }

        // 작업 취소 시도, 결과가 완료된 경우는 효과가 없다
        boolean cancel = future.cancel(true);
//        boolean cancel = future.cancel(false);
//        if (!future.isCancelled()) {
            try {
                Integer result = future.get();
                System.out.println("ret: " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
//        } else {
//            System.out.println("작업 취소");
//        }
        executorService.shutdown();
    }
}
