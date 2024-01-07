package me.staek.synchronization.mutex;

/**
 * 공유객체 사용은 아래와 같은 Mutex 매커니즘을 따르도록 하였다.
 *
 * do {
 *       acquired(mutex)
 *          critical section
 *      release(mutex)
 *         remainder section
 * } while (true);
 *
 */
class SharedData {
    private int data = 0;
    private Mutex mutex;
    public SharedData(Mutex mutex) {
        this.mutex = mutex;
    }
    public void sum() {
        mutex.acquired();
        try {
            for (int i = 0; i < 10_000_0000; i++)
                data++;
        } finally {
            mutex.release();
        }
    }
    public int getSum() {
        return data;
    }
}
