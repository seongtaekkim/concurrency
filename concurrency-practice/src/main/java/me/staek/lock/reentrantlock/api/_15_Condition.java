package me.staek.lock.reentrantlock.api;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 에서 제공하는 newConditioin을 이용한 예제
 * - synchronized의 wait(), notify(), notifyAll()과 같은 기능을하는 메소드로 협력기능을 구현할 수 있다.
 * - java 는 signal and continue 으로 동작합을 확인할 수 있다.
 */
public class _15_Condition {

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean flag = false;

    public void awaiting() throws InterruptedException {
        lock.lock();
        try {
            while (!flag) {
                System.out.println("조건이 만족 하지 못해 대기함");
                condition.await();
            }
            System.out.println("임계영역 수행");
        } finally {
            lock.unlock();
        }
    }

    public void signaling() {
        lock.lock();
        try {
            flag = true;
            System.out.println("조건을 만족 시키고 깨움");
            condition.signalAll();
            System.out.println("lock 반환 전 나머지 작업을 수행");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        _15_Condition conditionExample = new _15_Condition();

        Thread thread1 = new Thread(() -> {
            try {
                conditionExample.awaiting();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "THREAD-NAME-111");

        Thread thread2 = new Thread(conditionExample::signaling, "THREAD-NAME-222");

        thread1.start();
        Thread.sleep(1000);
        thread2.start();

        thread1.join();
        thread2.join();
        while (true) {
        }
    }
}
