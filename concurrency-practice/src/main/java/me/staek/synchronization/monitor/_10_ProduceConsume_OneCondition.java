package me.staek.synchronization.monitor;


import java.util.LinkedList;
import java.util.Queue;

class SharedQueue {
    Queue<Integer> queue = new LinkedList<>();
    final int capacity = 5;
    Object lock = new Object();
    public void produce(int data) throws InterruptedException {
        synchronized (lock) {
            while (this.capacity == queue.size()) {
                lock.wait();
            }
            System.out.println(Thread.currentThread().getName() + " produce data: " + data);
            queue.add(data);
            lock.notifyAll();
        }
    }
    public int consume() throws InterruptedException {
        synchronized (lock) {
            while (queue.size() == 0)
                lock.wait();
            Integer poll = queue.poll();
            System.out.println(Thread.currentThread().getName() + " consume data: " + poll);
            lock.notifyAll();
            return poll;
        }
    }
}

/**
 * 모니터의 상호배제와 협력에대한 대표적인 예시인 생산자소비자를 구현해보았다.
 *
 * 두 개의 스레드가 각각 생산과 소비를 반복한다.
 * 생산,소비를 할 수 없을 때의 wait,notify를 이용한 협력을 예시로 구현한 예제이다.
 */
public class ProduceConsume_OneCondition {
    public static void main(String[] args) {
        SharedQueue sharedQueue = new SharedQueue();
        Thread produce = new Thread(() -> {
            int data = 1;
            try {
                for (int i=0; i<=10 ; i++)
                    sharedQueue.produce(i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consume = new Thread(() -> {
            try {
                for (int i=0; i<=10 ; i++)
                    sharedQueue.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        produce.start();
        consume.start();

        try {
            produce.join();
            consume.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
