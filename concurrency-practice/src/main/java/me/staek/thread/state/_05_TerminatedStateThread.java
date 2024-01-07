package me.staek.thread.state;

/**
 * TERMINATED
 */
public class _05_TerminatedStateThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("스레드 실행 중");
        });
        thread.start();
        thread.join();
        System.out.println("스레드 상태: " + thread.getState());
    }

}
