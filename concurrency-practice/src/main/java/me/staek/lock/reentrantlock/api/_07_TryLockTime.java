package me.staek.lock.reentrantlock.api;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock - tryLock(duration)
 *
 * 주어진 대기 시간 내에 다른 스레드에 의해 보유되지 않으면 락을 획득하고 락의 보유 횟수를 1로 설정하고 true 를 반환한다
 * 현재 스레드가 이미 이 락을 보유하고 있다면 보유 횟수가 1 증가하고 메서드는 true 를 반환하고 락이 다른 스레드에 의해 보유되어 있다면 락이 획득될 때까지 대기한다
 * 현재 스레드가 이 메서드를 호출할 때 인터럽트 상태가 설정되어 있거나 락을 획득하는 동안 인터럽트가 발생한 경우 InterruptedException 이 발생 되고 인터럽트 상태가 초기화된다
 * 지정된 대기 시간이 경과하면 값 false 가 반환되며 만약 시간이 0보다 작거나 같으면 메서드는 전혀 대기하지 않는다
 * 락의 정상적인 또는 재진입 획득 및 대기 시간 경과 보다 인터럽트에 응답하는 것이 우선적으로 처리된다
 */
public class _07_TryLockTime {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            try {
                if (lock.tryLock(2, TimeUnit.SECONDS)) {
                    try {
                        System.out.println("스레드1 - 락 획득");
                        Thread.sleep(3000);
                    } finally {
                        lock.unlock();
                        System.out.println("스레드1 - 락 해제");
                    }
                } else {
                    System.out.println("스레드1 - 락 획득 실패");
                }
            } catch (InterruptedException e) {
                System.out.println("스레드1 - 인트럽트 발생");
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                if (lock.tryLock(2, TimeUnit.SECONDS)) {
                    try {
                        System.out.println("스레드2 - 락 획득");
                    } finally {
                        lock.unlock();
                        System.out.println("스레드2 - 락 해제");
                    }
                } else {
                    System.out.println("스레드2 - 락 획득 실패");
                }
            } catch (InterruptedException e) {
                System.out.println("스레드1 - 인트럽트 발생");
            }
        });

        thread1.start();
        thread2.start();

//        thread1.interrupt();
    }
}
