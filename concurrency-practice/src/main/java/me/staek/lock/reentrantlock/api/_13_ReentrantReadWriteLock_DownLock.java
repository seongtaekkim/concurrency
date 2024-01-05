package me.staek.lock.reentrantlock.api;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock 재진입 - Write -> Read
 * - Write Lock 획득한 스레드가 Read Lock을 획득 할 수 있다. (DownGrade Lock)
 * - Read Lock 획득 후 Write Lock 을 반환하면, 상호배제가 해제되어 다른 스레드가 Read Lock을 획득할 수 있게된다.
 */
public class _13_ReentrantReadWriteLock_DownLock {
    public static void main(String[] args) {

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        Lock readLock = lock.readLock();
        Lock writeLock = lock.writeLock();
        SharedData sharedData = new SharedData();

        for (int i=0; i<1 ; i++) {
            new Thread(() -> {
                writeLock.lock();

                try {
                    sharedData.setData(1000 + sharedData.getData());
                    System.out.println(Thread.currentThread().getName() + " 쓰기: " + sharedData.getData());
                    readLock.lock();

                    try {
                        System.out.println(Thread.currentThread().getName() + " 읽기: " + sharedData.getData());
                    } finally {
                        writeLock.unlock();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } finally {
                    readLock.unlock();
                }
            }).start();

        }

        for (int i=0; i<10 ; i++) {
            new Thread(() -> {
                readLock.lock();

                try {
                    System.out.println(Thread.currentThread().getName() + " 읽기: " + sharedData.getData());
                } finally {
                    readLock.unlock();
                }
            }).start();
        }
    }

    static class SharedData {
        private int data = 0;
        public int getData() {
            return data;
        }
        public void setData(int data) {
            this.data = data;
        }
    }
}
