package me.staek.threadpool.executeservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * ExecutorService - daemon thread issue
 *
 * - Thread를 daemon type으로 만들어 작업을 처리하는 경우 특징
 * 데몬스레드가 작업중이라고 했을 때
 * case 1) MainThread가 종료되면 작업중에 종료된다. => 유저스레드: 종료되지 않고 계속 작업한다.
 * case 2) awaitTermination() 는 TImeout 만큼 무한히 대기한다.
 * case 3) shutdownNow() : 인터럽트되어 종료된다.
 */
public class _05_SubmitWithDaemon {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
//                thread.setDaemon(true);
                return thread;
            }
        });

        executorService.submit(()->{
            while(true){
                System.out.println(Thread.currentThread().getName() + " : 데몬 스레드 실행 중...");
                Thread.sleep(1000);
            }
        });

        executorService.shutdown();

//        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        Thread.sleep(3000);
        System.out.println("메인 스레드 종료");

    }
}
