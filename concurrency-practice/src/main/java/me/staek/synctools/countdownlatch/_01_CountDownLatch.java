package me.staek.synctools.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch
 *
 * await()
 * countDown()
 */
public class _01_CountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(5);

        Thread[] threads = new Thread[5];

        for (int i=0 ;i<threads.length ; i++) {
            new Thread(new Worker(startLatch, endLatch)).start();
        }

        Thread.sleep(100);
        startLatch.countDown(); // 스레드: await에서 깨어남

        try {
            endLatch.await(); // 메인스레드: Latch가 0 될 때까지 대기
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("모든스레드 작업 종료 후 메인스레드 종료");

    }

    static class Worker implements Runnable{

        CountDownLatch start;
        CountDownLatch end;

        public Worker(CountDownLatch start, CountDownLatch end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " 작업 시작전");
                this.start.await();
                System.out.println(Thread.currentThread().getName() + " 작업 시작!");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println(Thread.currentThread().getName() + " 작업 종료");
                this.end.countDown();

            }
        }
    }
}
