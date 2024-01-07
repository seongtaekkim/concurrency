package me.staek.synchronization.semaphore;

/**
 * BinarySemaphore는 Mutex와 비슷하다.
 * S는 1이다.
 * 아래와 같은 로직을 따른다.
 *
 * do {
 * P(S);
 * 	critical section
 * V(S);
 * 	remainder section
 * } while (true);
 */
public class _02_BinarySemaphore implements MySemaphore {
    private int signal = 1;

    public synchronized void acquired() {
        while (this.signal == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // 현재 스레드의 인터럽트 상태 set
            }
        }
        this.signal = 0;
    }

    public synchronized void release() {
        this.signal = 1;
        this.notify();
    }
}
