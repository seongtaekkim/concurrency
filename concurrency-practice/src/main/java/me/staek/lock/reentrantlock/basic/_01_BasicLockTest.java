package me.staek.lock.reentrantlock.basic;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock
 * - shared-data에 대한 critical-section 동기화를 확인해본다.
 */
public class _01_BasicLockTest {

    Lock lock = new ReentrantLock();

    int count=0;
    public void increment() {
        lock.lock();
        try {
            this.count++;
        } finally {
            lock.unlock();
        }
    }

    public int count() {
        lock.lock();
        try {
            return this.count;
        } finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {

        _01_BasicLockTest ex = new _01_BasicLockTest();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++)
                ex.increment();
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++)
                ex.increment();
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++)
                ex.increment();
        });
        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            System.out.println(ex.count());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
