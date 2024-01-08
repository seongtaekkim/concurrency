package me.staek.cas;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * AtomicBoolean
 *
 * 여러 스레드의 compareAndSet 테스트
 */
public class _03_AtomicBoolean {
    static AtomicBoolean bool = new AtomicBoolean(false);

    public static void main(String[] args) {

        new Thread(() -> {
            for (int j=0 ;j<5 ; j++) {
                if (!bool.compareAndSet(false, true)) {
                    System.out.println(Thread.currentThread().getName() + " 변경실패");
                }
                System.out.println(Thread.currentThread().getName() + " critical section");
                bool.set(false);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            for (int j=0 ;j<5 ; j++) {
                if (!bool.compareAndSet(true, false)) {
                    System.out.println(Thread.currentThread().getName() + " 변경실패");
                }
                System.out.println(Thread.currentThread().getName() + " critical section");
                bool.set(true);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
