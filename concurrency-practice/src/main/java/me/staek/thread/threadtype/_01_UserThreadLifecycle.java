package me.staek.thread.threadtype;

/**
 * UserThread 종료시점 테스트
 */
public class _01_UserThreadLifecycle {
    public static void main(String[] args) throws InterruptedException {
        Thread userThread1 = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("사용자 스레드 1 종료.");
        });

        Thread userThread2 = new Thread(() -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("사용자 스레드 2 종료.");
        });

        userThread1.start();
        userThread2.start();

//        userThread1.join();
//        userThread2.join();

        System.out.println("main exit");
    }
}
