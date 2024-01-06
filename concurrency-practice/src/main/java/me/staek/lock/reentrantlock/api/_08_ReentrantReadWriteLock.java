package me.staek.lock.reentrantlock.api;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * ReadWriteLock 타입의 ReentrantReadWriteLock 예제
 * - readLock() 은  상호배제하지 않아서 동시성이 높다. readLock() 락이 걸린 채로 writeLock() 은 상호배제 된다.
 * - writeLock() 은 다른 writeLock() 이나 readLock()과 상호배제 관계이다.
 */
public class _08_ReentrantReadWriteLock {
    static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static final SharedData sharedData = new SharedData();

    public static void main(String[] args) {

        Thread reader1 = new Thread(() -> {
            readWriteLock.readLock().lock();
            try {
                System.out.println("Thread1: Reading Data:" + sharedData.getData());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } finally {
                readWriteLock.readLock().unlock();
            }
        });

        Thread reader2 = new Thread(() -> {
            readWriteLock.readLock().lock();
            try {
                System.out.println("Thread2: Reading Data:" + sharedData.getData());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } finally {
                readWriteLock.readLock().unlock();
            }
        });

        Thread writer = new Thread(() -> {
            readWriteLock.writeLock().lock();
            try {
                System.out.println("Thread3: Acquire WriteLock");
                sharedData.setData(42);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Thread3: Write Data: " + sharedData.getData());
            } finally {
                readWriteLock.writeLock().unlock();
            }
        });

        Thread writer2 = new Thread(() -> {
            readWriteLock.writeLock().lock();
            try {
                System.out.println("Thread4: Acquire WriteLock");
                sharedData.setData(4242);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Thread4: Write Data: " + sharedData.getData());
            } finally {
                readWriteLock.writeLock().unlock();
            }
        });

        writer.start();
        reader1.start();
        reader2.start();
        writer2.start();

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
