package me.staek.lock.reentrantlock.api;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock - Fairness vs UnFair 성능비교
 * - Fair: 락해제 후 대기중인 스레드 순서대로 작업을 할당해야 한다.
 * - Unfair: 락해제 후 순서상관없이 락을 차지한 스레드가 작업을 수행한다.
 *
 * => Fair, Unfair 성능차이가 비교적 안난다.
 */
public class _12_FairPerform3 {
    private static final int THREAD_COUNT = 4;
    private static final int NUM_OF_TESTS = 1000_000;
    private static final ReadWriteLock unfairWriteLock = new ReentrantReadWriteLock(false);
    private static final ReadWriteLock fairWriteLock = new ReentrantReadWriteLock(true);

    public static void main(String[] args) {
        test("Unfair", unfairWriteLock);
        test("Fair", fairWriteLock);
    }

    private static void test(String lockType, ReadWriteLock lock) {
        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < NUM_OF_TESTS ; j++) {
                    lock.readLock().lock();
                    try {
                        // critical section
                    } finally {
                        lock.readLock().unlock();
                    }
                }
            });
        }

        for (Thread thread : threads)
            thread.start();

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(lockType + ": " + (endTime - startTime) + " ms");
    }
}
