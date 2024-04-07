package me.staek.threadpool.threadpoolexecutor;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * LinkedBlockingQueue - take, put
 * 테스트) take, put 메서드 디버그 잡고 테스트
 */
public class _04_LinkedBlockingQueue {
    public static void main(String[] args) {

        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);

        // 생성자
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("Producing: " + i);
                    queue.put(i); // 데이터를 큐에 넣음 (큐가 가득 차면 Blocking)
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // 소비자
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    int data = queue.take(); // 데이터를 큐에서 가져옴 (큐가 비어있으면 Blocking)
                    System.out.println("Consuming: " + data);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
