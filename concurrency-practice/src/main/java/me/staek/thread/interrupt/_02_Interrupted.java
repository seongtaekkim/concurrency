package me.staek.thread.interrupt;

/**
 * Thread interrupted()
 * - interrupt 되었을때 true를 반환하고, false로 초기화한다.
 */
public class _02_Interrupted {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("스레드 작동 중");
                if (Thread.interrupted()) {
                    System.out.println("인터럽트 상태 초기화 되었습니다.");
                    break;
                }
            }
            System.out.println("인터럽트 상태: " + Thread.currentThread().isInterrupted());
            Thread.currentThread().interrupt();
            System.out.println("인터럽트 상태: " + Thread.currentThread().isInterrupted());
        });

        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }
}
