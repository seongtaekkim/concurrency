package me.staek.threadpool.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ExecutorService 가 execute로 작업스레드를 시작한다.
 * 이때, 작업결과를 콜백클래스를 통해 전달받도록 작성한다
 * => 메인스레드 입장에서 보면 Nonblock & Async 로 작업을 처리한 셈이다.
 */
public class _03_Callback {

    interface Callback {
        void onComplete(int ret);
    }
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(1);

        service.execute(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " 작업시작");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int ret = 100;
            Callback callback = new MyCallback();
            callback.onComplete(ret);
        });

        System.out.println("비동기작업 시작");
        service.shutdown();
    }

    static class MyCallback implements Callback {

        @Override
        public void onComplete(int ret) {
            System.out.println(Thread.currentThread().getName() + ": 비동기 결과 값 출력: " + ret);
        }
    }
}
