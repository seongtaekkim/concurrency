package me.staek.threadpool.executor;

import java.util.concurrent.Executor;

/**
 * 작업을 인자로 넘겼을 때, Executor가 직접 작업을 수행한다.
 *
 * - 스레드를 생성하는게 아닌, Executor가 run() 하는 방식이다.
 */
public class _01_SyncExecutor {
    public static void main(String[] args) {

        Executor executor = new Executor() {
            @Override
            public void execute(Runnable command) {
                command.run();
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
