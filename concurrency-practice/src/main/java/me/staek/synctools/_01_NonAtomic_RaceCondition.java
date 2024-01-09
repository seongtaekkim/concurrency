package me.staek.atomic;

/**
 * 스레드 여러개를 생성 후, 공유자원 counting 결과를 출력
 *
 * - 동시성 문제 발생
 */
public class _01_NonAtomic_RaceCondition {

    static int data = 0;
    static final int THREAD_NUM = 3;

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[THREAD_NUM];
        for (int i=0; i<threads.length ; i++) {

            threads[i] = new Thread(() -> {
               for (int j=0 ;j<100_000_00 ; j++) {
                   int expected = data;
                   int newValue = expected + 1;
                   data = newValue;
//                   System.out.println(Thread.currentThread().getName() + " expected: " + expected + " newValue: " + newValue );
               }

            });
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("최종값: " + data);
    }
}
