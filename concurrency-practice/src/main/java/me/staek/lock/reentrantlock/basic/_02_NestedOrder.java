package me.staek.lock.reentrantlock.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock
 * - 여러 종류의 Lock을 중첩해서 획득할 경우
 * - 해제 순서는 상관 없다.
 *   - synchronized는 바깥의 Lock에 포함되어 있어야 한다.
 */
public class _02_NestedOrder {

    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();

    public void nestedLockCheck() {
        lock1.lock();
        try {
            System.out.println("in lock1");
            lock2.lock();
            try {
                System.out.println("in lock2");
            } finally {
                lock1.unlock();
                System.out.println("release lock1");
            }
        } finally {
            lock2.unlock();
            System.out.println("release lock2");
        }
    }
    public static void main(String[] args) {
        _02_NestedOrder order = new _02_NestedOrder();
        order.nestedLockCheck();
    }
}
