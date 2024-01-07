package me.staek.synchronization.semaphore;

/**
 * BinarySemaphore test
 *
 * - 공유자원에 스레드가 하나만 작업할 수 있어서 상호배제가 된다.
 */
public class _02_BinarySemaphore_test {

    public static void main(String[] args) {

        MySemaphore semaphore = new _02_BinarySemaphore();
        SharedData resource = new SharedData(semaphore);

        Thread t1 = new Thread(resource::sum);
        Thread t2 = new Thread(resource::sum);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("최종 값: " + resource.getSum());
    }
}
