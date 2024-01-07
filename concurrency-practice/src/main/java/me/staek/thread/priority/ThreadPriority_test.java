package me.staek.thread.priority;

/**
 * 우선순위를 부여하고 스레드를 실행하였다.
 *
 * waiting queue에서 판단할 정도로 스레드가 복잡하고 많지 않아서 결과순서가 뒤죽박죽..
 */
public class ThreadPriority_test {

    public static void main(String[] args) throws InterruptedException {

        CountingThread maxThread = new CountingThread("우선 순위: " + Thread.MAX_PRIORITY + " 스레드", Thread.MAX_PRIORITY);
        CountingThread normThread = new CountingThread("우선 순위: " + Thread.NORM_PRIORITY + " 스레드", Thread.NORM_PRIORITY);
        CountingThread minThread = new CountingThread("우선 순위: " + Thread.MIN_PRIORITY + " 스레드", Thread.MIN_PRIORITY);

        maxThread.start();
        normThread.start();
        minThread.start();

        maxThread.join();
        normThread.join();
        minThread.join();

    }

    static class CountingThread extends Thread {
        private final String threadName;
        private int count = 0;

        public CountingThread(String threadName, int priority) {
            this.threadName = threadName;
            setPriority(priority);
        }

        @Override
        public void run() {
            while (count < 10000000) {
                count++;
            }
            System.out.println(threadName + ": " + count);
        }
    }
}
