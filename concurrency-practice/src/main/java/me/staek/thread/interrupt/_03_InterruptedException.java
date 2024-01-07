package me.staek.thread.interrupt;

/**
 * InterruptedException 이 발생하면 interrupt 상태는 false로 초기화된다.
 * 이 때 자기자신을 인터럽트하여 true로 변경할 수 있다.
 */
public class _03_InterruptedException {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("인터럽트 상태: " + Thread.currentThread().isInterrupted());
                Thread.sleep(5000);
                System.out.println("remaining");
            } catch (InterruptedException e) { // InterruptedException 예외가 발생하면 인터럽트 상태가 초기화 된다 : false
                System.out.println("interruped Exception !");
                System.out.println("인터럽트 상태: " + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
            }
        });

        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
        thread.join();
        System.out.println("인터럽트 상태: " + thread.isInterrupted());
    }
}
