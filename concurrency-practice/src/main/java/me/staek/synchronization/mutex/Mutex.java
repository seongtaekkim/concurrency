package me.staek.synchronization.mutex;

/**
 * Mutex
 *
 * 뮤텍스(Mutual Exclusion)는 공유 자원에 대한 경쟁 상태를 방지하고 동시성을 제어한다.
 * Mutex 락을 가진 오직 한개의 스레드만이 임계영역에 진입할 수 있고, 락을 획득한 스레드만이 락을 해제 할 수 있도록 구현하였다.
 *
 */
public class Mutex {
    private boolean lock = false;
    public synchronized void acquired() {
        while (lock) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        this.lock = true;
    }
    public synchronized void release() {
        this.lock = false;
        this.notify();
    }
}
