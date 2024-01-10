package me.staek.threadpool.future;

import java.util.concurrent.*;

/**
 * ExecutorService - future + callback
 *
 * - Future는 get() 때문에 sync 처럼 동작한다.
 * - 새로운 스레드에서 Future get()을 기다리고, 결과를 인자로 보낸 콜백클래스에서 받아수행한다면
 *   async형태로 동작하게 할 수 있을 것이다.
 */
public class _04_CallbackWithFuture {

    interface Callback {
        void onComplete(int ret);
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(1);

        Callable<Integer> callable = () -> {
            System.out.println(Thread.currentThread().getName() + " : callable 작업 시작");
            Thread.sleep(1000);
            return 100;
        };

        Future<Integer> future = service.submit(callable);
        System.out.println("비동기작업 시작");
        registCallback(future, new Callback() {
            @Override
            public void onComplete(int ret) {
                System.out.println(Thread.currentThread().getName() + ": 비동기 결과 값 출력: " + ret);
            }
        });

        service.shutdown();

    }
    private static void registCallback(Future<Integer> future, Callback callback) {
        new Thread(() -> {
            try {
                Integer i = future.get();
                callback.onComplete(i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }
}
