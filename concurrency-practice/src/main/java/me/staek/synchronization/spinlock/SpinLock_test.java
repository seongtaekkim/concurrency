package me.staek.synchronization.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * AtomicBoolean 의 compareAndSet를 이용해서 busy waiting을 발생시켜
 * 작업
 */
public class SpinLock_test {
    private AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        while (!lock.compareAndSet(false, true)) ;
    }

    public void unlock() {
        lock.set(false);
    }

    public static void main(String[] args) {
        SpinLock_test spinLock = new SpinLock_test();

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " 락 획득시도");
            spinLock.lock();
            System.out.println(Thread.currentThread().getName() + " 락 획득");
            try {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                System.out.println(Thread.currentThread().getName() + " 락 해제");
                spinLock.unlock();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();
    }
}
