package me.staek.lock.reentrantlock.api;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock - tryLock
 *
 * 이 락이 호출 시점에 다른 스레드에 의해 보유되지 않을 때만 락을 획득하고 락의 보유 횟수를 1로 설정하고  true 를 반환한다
 * 현재 스레드가 이미 이 락을 보유하고 있다면, 보유 횟수가 1 증가하고 true 를 반환한다
 * 락이 다른 스레드에 의해 소유되어 있다면 이 메서드는 즉시 false 값을 반환한다. 그리고 이 메서드는 락을 획득하지 못하더라도 스레드가 대기하거나 차단되지 않는다
 */
public class _06_TryLock {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            boolean acquired = false;
            while (!acquired) {
                acquired = lock.tryLock();
                if (acquired) {
                    try {
                        System.out.println("스레드1 - 락 획득");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                        System.out.println("스레드1 - 락 해제");
                    }
                } else {
                    System.out.println("스레드1 - 획득실패 - 대기.");
                    try {
                        Thread.sleep(1000); // 1초 대기 후 다시 시도
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            boolean acquired = false;
            while (!acquired) {
                acquired = lock.tryLock();
                if (acquired) {
                    try {
                        System.out.println("스레드2 - 락 획득");
                    } finally {
                        lock.unlock();
                        System.out.println("스레드2 - 락 해제");
                    }
                } else {
                    System.out.println("스레드2 - 획득실패 - 대기.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
