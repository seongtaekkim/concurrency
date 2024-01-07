package me.staek.synchronization.semaphore;

/**
 * OrderedSemaphore n acquire
 *
 * acquired 메서드를 호출할 때 인자로 순서를 구분하여 순서대로 획득하도록 제어할 수 있다.
 */
class OrderedSemaphore {
    private int signal = 0;  // 순서관리 신호 변수

    public synchronized void acquired(int order) {
        while (this.signal != order) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public synchronized void release() {
        this.signal++;
        notifyAll();
    }
}

public class _04_OrderedSemaphore {
    public static void main(String[] args) throws InterruptedException {
        OrderedSemaphore semaphore = new OrderedSemaphore();

        Thread B = new Thread(() -> {
            semaphore.acquired(1);
            System.out.println("Thread B is running");
            semaphore.release();
        });

        Thread.sleep(1000);

        Thread A = new Thread(() -> {
            semaphore.acquired(0);
            System.out.println("Thread A is running");
            semaphore.release();
        });


        Thread C = new Thread(() -> {
            semaphore.acquired(2);
            System.out.println("Thread C is running");
            semaphore.release();
        });


        // 스레드가 어떻게 시작되던 락 획득 후 로직실행순서는 같다.
        C.start();
        A.start();
        Thread.sleep(333);
        B.start();
    }
}
