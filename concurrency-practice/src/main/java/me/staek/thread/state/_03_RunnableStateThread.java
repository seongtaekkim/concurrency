package me.staek.thread.state;

/**
 * RUNNABLE
 */
public class _03_RunnableStateThread {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
                System.out.println("스레드 상태: " + Thread.currentThread().getState());
        });
        thread.start();
    }
}
