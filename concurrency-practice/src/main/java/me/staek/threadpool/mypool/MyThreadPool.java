package me.staek.threadpool.mypool;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 간단한 ThreadPool 구현
 *
 * isShutdown
 * - volatile
 * shutdown()
 * - 현재 진행중인 작업 스레드만 완료하고 바로 종료한다.
 *
 */
public class MyThreadPool {
    private final int taskNum;
    private final Queue<Runnable> taskQueue;
    private final Thread[] threads;
    private volatile boolean isShutdown;

    public MyThreadPool(int taskNum) {
        this.taskNum = taskNum;
        this.taskQueue = new LinkedList<>();
        threads = new Thread[taskNum];
        for (int i=0; i<taskNum ; i++) {
            threads[i] = new Worker();
            threads[i].start();
        }
        this.isShutdown = false;
    }

    public void submit(Runnable task) {
        if (!this.isShutdown) {
            synchronized (taskQueue) {
                taskQueue.offer(task);
                taskQueue.notifyAll();
            }
        }
    }

    public void shutdown() {
        this.isShutdown = true;
        synchronized (taskQueue) {
            taskQueue.notifyAll();
        }
        for (int i=0 ; i<taskNum ; i++) {
            try {
                this.threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public class Worker extends Thread {
        @Override
        public void run() {
            while (!isShutdown) {
                Runnable task;
                synchronized (taskQueue) {
                    while (taskQueue.isEmpty() && !isShutdown) {
                        try {
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                        if (!taskQueue.isEmpty()) {
                            task = taskQueue.poll();
                        } else {
                            continue;
                        }
                }
                task.run(); // 작업 실행
            }
        }
    }
}
