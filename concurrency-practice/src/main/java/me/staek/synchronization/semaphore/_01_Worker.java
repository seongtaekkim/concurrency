package me.staek.synchronization.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Semaphore API test Thread 구현
 *
 */
public class _01_Worker implements Runnable {
    private int id;
    private Semaphore semaphore;
    public _01_Worker(int id, Semaphore semaphore) {
        this.id = id;
        this.semaphore = semaphore;
    }
    @Override
    public void run() {
        try {
            System.out.println("Thread " + id + " permit 획득 시도");
            semaphore.acquire();  // P 연산
            System.out.println("Thread " + id + " permit 획득.");

            Thread.sleep(1000);

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally{
            System.out.println("Thread " + id + " 해제");
            semaphore.release();  // V 연산
        }
    }
}
