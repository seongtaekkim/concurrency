package me.staek.lock.reentrantlock.api;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock 재진입 - Read -> Write
 * - Read Lock 획득 후 Write Lock은 획득 불가능하다.
 * - 애초에 Write Lock은 상호배제를 위한 락이기 때문.
 */
public class _14_ReentrantReadWriteLock_UpLock {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void failedUpgradeAttempt() {
        System.out.println("읽기 잠금 획득 시도");
        lock.readLock().lock();
        System.out.println("읽기 잠금 획득.");

        try {
            System.out.println("쓰기 잠금 획득 시도");
            lock.writeLock().lock(); // 데드락 발생
            System.out.println("?");
        } finally {
            lock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        new _14_ReentrantReadWriteLock_UpLock().failedUpgradeAttempt();
    }
}
