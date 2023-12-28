package me.staek.thread.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadPool을 세팅하고 쓰레드를 수행할 때, ThreadLocal 데이터를 clear 해야 한다.
 * 그렇지 않으면, 이전 작업 데이터가 남아있다.
 */
public class _03_ThreadPoolThreadLocal {
    private static final ThreadLocal<String> LOCAL = new ThreadLocal<>();
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);

        service.submit(() -> {
            LOCAL.set("data: " + Thread.currentThread().getName());
//            LOCAL.remove(); // 사용완료한 정보는 스레드 종료 전에 삭제해야 한다.
        });

        Thread.sleep(100);

        for (int i=0 ; i<10 ; i++) {
            service.submit(() -> {
                System.out.println(LOCAL.get());
            });
        }
    }
}
