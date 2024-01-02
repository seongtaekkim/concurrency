package me.staek.lock.reentrantlock.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock
 * - 같은 스레드에서 lock()을 이미 획득 했다면, 여러번 재진입이 가능하다.
 * - 다른 스레드에서 lock을 해제사지 않았다면, 획득하지못하고 대기하게 된다.
 */
public class _03_ReentrantState {
    Lock lock = new ReentrantLock();

    void reentrantMethod() {
        lock.lock();
        System.out.println("in first lock");
        try {
            lock.lock();
            System.out.println("in second lock");
            try {
                lock.lock();
                try {
                    System.out.println("in third lock");
                } finally {
                    lock.unlock();
                    System.out.println("release third lock");
                }
            } finally {
                lock.unlock();
                System.out.println("release second lock");

            }
        } finally {
            lock.unlock();
            System.out.println("release first lock");
        }
    }
    void secondEntrantMethod() {
        lock.lock();
        try {
            System.out.println("대기열에서 락 획득");
        } finally {
            lock.unlock();
            System.out.println("release waitlist lock");
        }
    }

    public static void main(String[] args) {
        _03_ReentrantState state = new _03_ReentrantState();
        Thread thread1 = new Thread(() -> {
            state.reentrantMethod();
        });

        Thread thread2 = new Thread(() -> {
            state.secondEntrantMethod();
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
