package me.staek.thread.join;

public class _02_InterruptedWithJoin {

    public static void main(String[] args) {

        Thread mainThread = Thread.currentThread();

        Thread longRunningThread = new Thread(() -> {
            try {
                    System.out.println("오래걸리는 스레드");
                    Thread.sleep(10000);
            } catch (InterruptedException e) {
                mainThread.interrupt();
                System.out.println("오래걸리는 스레드 인터럽트되고, mainThread 인터럽트 검");
            }
        });

        longRunningThread.start();

        Thread interruptingThread = new Thread(() -> {
            try {
                Thread.sleep(2000);
                longRunningThread.interrupt();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        interruptingThread.start();


        try {
            longRunningThread.join();
            System.out.println("메인 스레드 작업 완료");

        } catch (InterruptedException e) {
            System.out.println("메인 스레드 인터럽트걸림");
            throw new RuntimeException(e);
        }
    }
}
