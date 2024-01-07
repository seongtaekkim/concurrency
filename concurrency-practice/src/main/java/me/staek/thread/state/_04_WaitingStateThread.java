package me.staek.thread.state;

/**
 * WAITING
 */
public class _04_WaitingStateThread {

    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        Thread.sleep(100);
        System.out.println("스레드 상태: " + thread.getState());
    }
}
