package me.staek.synchronization.semaphore;

/**
 * binary semaphore 순서기 작성
 *
 * 스레드1 : run()은 acquire 만 한다.
 * 스레드2 : run()은 release 만 한다.
 * => 순서대로 동작.
 */
public class _04_OrderedBinarySemaphore {
    public static void main(String[] args) throws InterruptedException {
        MySemaphore semaphore = new BinaryCommonSemaphore();
        WaitThread waitThread = new WaitThread(semaphore);
        SignalThread signalThread = new SignalThread(semaphore);

        waitThread.start();
        Thread.sleep(1000);
        signalThread.start();
    }

    static class BinaryCommonSemaphore implements MySemaphore {
        private int signal = 0;

        public synchronized void acquired() {
            while (this.signal == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.signal = 0;
        }

        public synchronized void release() {
            this.signal = 1;
            notify();
        }
    }
}

class WaitThread extends Thread {
    private MySemaphore semaphore;

    public WaitThread(MySemaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void run() {
        this.semaphore.acquired();
        System.out.println(Thread.currentThread().getName() + ": WaitThread");
    }
}

class SignalThread extends Thread {
    private MySemaphore semaphore;

    public SignalThread(MySemaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + ": SignalThread");
        this.semaphore.release();
    }
}
