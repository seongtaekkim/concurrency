package me.staek.threadpool.executeservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 매개변수 ThreadFactory 를 통해 스레드 생성과 관련된 로직을 정할 수 있다 (스레드 생성 방식, 이름, 우선 순위)
 *
 * return new ThreadPoolExecutor(nThreads, nThreads,0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory)
 *                              (기본 스레드개수, 최대 스레드개수, 대기시간, 대기 시간단위, 무한크기 대기열, 스레드 팩토리)
 */
public class _13_FixedCustomThreadFactory {
    public static void main(String[] args) {

        ThreadFactory threadFactory = new CustomThreadFactory("CustomThread");
        ExecutorService executor = Executors.newFixedThreadPool(3, threadFactory);

        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int taskNumber = i;
            Callable<Integer> task = () -> {
                System.out.println("Thread : " + Thread.currentThread().getName() + ", Result: " + taskNumber + 1);
                return taskNumber + 1;
            };

            Future<Integer> future = executor.submit(task);
            futures.add(future);
        }

        for (Future<Integer> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }

    static class CustomThreadFactory implements ThreadFactory {
        private final String name;
        private int threadCount = 0;

        public CustomThreadFactory(String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            threadCount++;
            String threadName = name + "-" + threadCount;
            Thread newThread = new Thread(r, threadName);
            System.out.println("스레드 이름: " + threadName);
            return newThread;
        }
    }
}
