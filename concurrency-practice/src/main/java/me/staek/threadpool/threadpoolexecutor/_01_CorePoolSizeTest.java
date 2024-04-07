package me.staek.threadpool.threadpoolexecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * corePoolSize  & maxPoolSize 테스트
 * - task가 등록되기 전까지 스레드는 0개이다.
 * - 스레드는 corePoolSize 개수까지 늘어난다.
 * - task가 다 찼는데 스레드가 corePoolSize와 같다면 maxPoolSize까지 스레드가 늘어난다.
 * <p>
 * 테스트1) 작업을 LinkedBlockingQueue에 등록
 * 테스트2) 작업을 ArrayBlockingQueue에 크기 4로 등록, task 4, 6, 8 개 등록 -> 스레드이름 확인
 * 테스트3) 작업을 ArrayBlockingQueue에 크기 4로 등록, task 9 등록 -> 예외 확인
 */
public class _01_CorePoolSizeTest {
    public static void main(String[] args) {

        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 0L;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
//        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(4);
        int taskNum = 9;

        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

        for (int i = 0; i < taskNum; i++) {
            final int taskId = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " 가 태스크" + taskId + " 를 실행하고 있습니다.");
            });
        }

        executor.shutdown();

    }
}
