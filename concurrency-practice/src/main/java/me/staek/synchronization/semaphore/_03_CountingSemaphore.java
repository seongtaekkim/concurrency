package me.staek.synchronization.semaphore;

/**
 * S가 2개이상인 경우 CountingSemaphore라 한다.
 *
 * - 동시에 여러개 스레드가 공유자원에 들어갈 수 있다.
 */
public class _03_CountingSemaphore implements MySemaphore {
    private int signal;
    private int permits;

    public _03_CountingSemaphore(int permits) {
        this.permits = permits;
        this.signal = permits;
    }

    public void acquired() {
        synchronized (this) {
            while (this.signal == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.signal--;
        }
        System.out.println(Thread.currentThread().getName() + " 락 획득, 현재 세마포어 값: " + signal);
    }

    public void release() {
        synchronized (this) {
            if (this.signal < permits) {
                this.signal++;
                System.out.println(Thread.currentThread().getName() + " 락 해제, 현재 세마포어 값: " + signal);
                notifyAll();
            }
        }
    }
}
