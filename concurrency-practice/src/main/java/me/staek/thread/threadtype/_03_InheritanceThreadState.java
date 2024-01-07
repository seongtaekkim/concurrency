package me.staek.thread.threadtype;

/**
 * 스레드가 생성한 스레드는 부모의 스레드 타입과 같은지 테스트
 */
public class _03_InheritanceThreadState {
    public static void main(String[] args) {

        Thread userThread = new Thread(() -> {
            Thread childOfUserThread = new Thread(() -> {
                System.out.println("사용자 스레드의 자식 스레드의 데몬 상태: " + Thread.currentThread().isDaemon());
            });
            childOfUserThread.start();
            System.out.println("사용자 스레드의 데몬 상태: " + Thread.currentThread().isDaemon());
        });

        Thread daemonThread = new Thread(() -> {
            Thread childOfDaemonThread = new Thread(() -> {
                System.out.println("데몬 스레드의 자식 스레드의 데몬 상태: " + Thread.currentThread().isDaemon());
            });
            childOfDaemonThread.start();
            System.out.println("데몬 스레드의 데몬 상태: " + Thread.currentThread().isDaemon());
        });
        daemonThread.setDaemon(true);

        userThread.start();
        daemonThread.start();
    }
}
