package me.staek.threadpool.executeservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * Test1) 60초 이후 생성한 스레드가 해제되는 지 확인
 * Test2) 매개변수 ThreadFactory 를 통해 스레드 생성과 관련된 로직을 정할 수 있다 (스레드 생성 방식, 이름, 우선 순위)
 *
 */
public class _15_CachedThreadPool_60 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

//        ExecutorService executor= Executors.newCachedThreadPool(new ThreadFactory() {
//            @Override
//            public Thread newThread(Runnable r) {
//                String threadName = "ccusteom";
//                Thread newThread = new Thread(r, threadName);
//                newThread.setDaemon(true);
//                return newThread;
//            }
//        });

        for (int i = 0; i < 10; i++) {
            int taskId = i;
            executor.execute(() -> {
                System.out.println("Task " + taskId + " is executing on " + Thread.currentThread().getName());
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
            });
        }

        // 60초 동안 아무 작업도 수행 하지 않음
        try {
            Thread.sleep(70000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 60초 이상 동안 사용 되지 않은 스레드는 자동 종료됨
        executor.shutdown();
    }
}
