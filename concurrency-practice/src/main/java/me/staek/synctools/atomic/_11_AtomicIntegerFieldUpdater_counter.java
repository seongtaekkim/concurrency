package me.staek.synctools.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicIntegerFieldUpdater
 * - 여러 스레드로 counter 처리로직 작성
 */
public class _11_AtomicIntegerFieldUpdater_counter {

    public static class Counter {
        volatile int counter;
        public int getCounter() {
            return counter;
        }
    }

    private static AtomicIntegerFieldUpdater<Counter> updater;
    private static final int THREAD_NUM = 3;
    private static final int NUMS = 10000;
    public static void main(String[] args) {

        updater = AtomicIntegerFieldUpdater.newUpdater(Counter.class, "counter");

        Counter counter = new Counter();
        Thread[] threads = new Thread[THREAD_NUM];

        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < NUMS; j++) {
                    int currentValue;
                    int newValue;
                    do {
                        currentValue = updater.get(counter); // volatile 정보를 가져옴
                        newValue = currentValue + 1;
                    } while (!updater.compareAndSet(counter, currentValue, newValue));
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("합계: " + updater.get(counter));
    }
}
