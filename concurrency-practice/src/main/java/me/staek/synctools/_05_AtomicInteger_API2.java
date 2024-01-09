package me.staek.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger
 *
 * incrementAndGet()
 * - 여러 스레드가 단순히 공유자원 count를 한다면,
 * - 원자적으로 counting이 가능하다. (상호배제가 락 없이 가능하고 바쁜대기도 하지 않는다)
 */
public class _05_AtomicInteger_API2 {
    private static AtomicInteger counter = new AtomicInteger(0);
    private static int THREAD_NUM = 5;

    public static void main(String[] args) {

        Thread[] threads = new Thread[THREAD_NUM];

        for (int i = 0; i <THREAD_NUM; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 1_000_000; j++) {
                    counter.incrementAndGet();
//                    System.out.println(counter.get());
                }
            });
            threads[i].start();
        }
        for(Thread thread:threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("합계: " + counter.get());
    }
}
