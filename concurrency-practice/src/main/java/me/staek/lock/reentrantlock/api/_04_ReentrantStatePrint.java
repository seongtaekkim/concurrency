package me.staek.lock.reentrantlock.api;

import java.util.concurrent.locks.ReentrantLock;

/**
 * thread가 락을 획득하거나 획득하기 위해 대기중인 상태를 확인할 수 있다.
 */
public class _04_ReentrantStatePrint {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("thread1 waiting");
                Thread.sleep(1000); // 스레드 1이 Lock을 보유한 상태에서 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread thread2 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("thread2 waiting");
                Thread.sleep(1000); // 스레드 2가 Lock을 보유한 상태에서 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread thread3 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("thread3 waiting");
                Thread.sleep(1000); // 스레드 3이 Lock을 보유한 상태에서 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        /**
         * Thread 3개 실행 전 lock 상태 출력
         */
        System.out.println("hold count: " + lock.getHoldCount()); // 현재 스레드가 점유하는 락 개수 0
        System.out.println("is held by current thread: " + lock.isHeldByCurrentThread());
        System.out.println("has queued Threads: " + lock.hasQueuedThreads()); // num of threads are waiting to acquire this lock
        System.out.println("has queued Thread1: " + lock.hasQueuedThread(thread1));
        System.out.println("has queued Thread2: " + lock.hasQueuedThread(thread2));
        System.out.println("has queued Thread3: " + lock.hasQueuedThread(thread3));
        System.out.println("queue length: " + lock.getQueueLength());
        System.out.println("is fair: " + lock.isFair());

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            Thread.sleep(500); // Main Thread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * thread1 이 락을 획득한 상태이다.
         * 나머지 2개 thread는 락 획득을 위한 대기큐에 있다.
         */
        System.out.println("hold count: " + lock.getHoldCount()); // 현재 스레드가 점유하는 락 개수
        System.out.println("is held by current thread: " + lock.isHeldByCurrentThread());
        System.out.println("has queued Threads: " + lock.hasQueuedThreads());
        System.out.println("has queued Thread1: " + lock.hasQueuedThread(thread1));
        System.out.println("has queued Thread2: " + lock.hasQueuedThread(thread2));
        System.out.println("has queued Thread3: " + lock.hasQueuedThread(thread3));
        System.out.println("queue length: " + lock.getQueueLength());
        System.out.println("is fair: " + lock.isFair());


        thread1.join();
        thread2.join();
        thread3.join();

        /**
         * 모든 스레드 작업이 종료 된 후 lock 상태값
         * - 대기큐에 스레드는 없다.
         */
        System.out.println("hold count: " + lock.getHoldCount()); // 현재 스레드가 점유하는 락 개수
        System.out.println("is held by current thread: " + lock.isHeldByCurrentThread());
        System.out.println("has queued Threads: " + lock.hasQueuedThreads());
        System.out.println("has queued Thread1: " + lock.hasQueuedThread(thread1));
        System.out.println("has queued Thread2: " + lock.hasQueuedThread(thread2));
        System.out.println("has queued Thread3: " + lock.hasQueuedThread(thread3));
        System.out.println("queue length: " + lock.getQueueLength());
        System.out.println("is fair: " + lock.isFair());

    }
}
