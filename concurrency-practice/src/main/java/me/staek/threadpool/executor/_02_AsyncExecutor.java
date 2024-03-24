package me.staek.threadpool.executor;

import java.util.concurrent.Executor;

/**
 * 작업을 인자로 넘겼을 때, Executor가 스레드를 생성한다.
 *
 * - 비동기로 실행하게 된다.
 */
public class _02_AsyncExecutor {
    public static void main(String[] args) {
        Executor executor = new Executor() {
            @Override
            public void execute(Runnable command) {
                new Thread(command).start();
            }
        };

        executor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + ": 1번작업");
        });
        executor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + ": 2번작업");
        });
    }
}
