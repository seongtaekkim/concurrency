package me.staek.synctools.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger 를 이용하여 01번 예제를 수정
 *
 * - AtomicInteger 의 value는 기본적으로 volatile이 적용된다.
 * 1. 데이터 변경 전 메인메모리로부터 기존 값을 가져온다.
 * 2. compareAndSet() 를 통해 기존 값과 메인메모리 값을 다시 비교하고 같으면 새로운 값으로 변경한다.
 * 3. 이때 compareAndSet() 는 원자적으로 실행된다.
 * 4. 1번의 값이 3번을 실행하기 직전에 다른스레드로부터 이미 변경되었다면 실패하여 false를 리턴한다.
 * 5. 성공할 때까지 while 에서 바쁜대기를 하며 compareAndSet() 를 수행힌다.
 */
public class _02_Atomic_RaceCondition {

    static AtomicInteger data = new AtomicInteger(0);
    static final int THREAD_NUM = 3;

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[THREAD_NUM];
        for (int i=0; i<threads.length ; i++) {

            threads[i] = new Thread(() -> {
                for (int j=0 ;j<100_000_00 ; j++) {
                    int expected = data.get();
                    int newValue = expected + 1;
                    while (!data.compareAndSet(expected, newValue)) {
                        expected = data.get();
                        newValue = expected + 1;
                    }
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
