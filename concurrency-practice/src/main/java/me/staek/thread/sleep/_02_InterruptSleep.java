package me.staek.thread.sleep;

public class _02_InterruptSleep {
    public static void main(String[] args) throws InterruptedException {

        Thread sleepingThread = new Thread(() -> {
            try {

                Thread.sleep(20000);
                System.out.println("remaining");

            } catch (InterruptedException e) {
                System.out.println("인터럽트 됨 : " + Thread.currentThread().getState());
            }
        });

        sleepingThread.start();
        Thread.sleep(1000);
        sleepingThread.interrupt();
    }
}
