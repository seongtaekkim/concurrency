package me.staek.synchronization.semaphore;

public class _03_CountingSemaphore_test {
    public static void main(String[] args) {

        int permits = 10; // 최대 permit 개수만큼 공유자원에 접근 가능.
        _03_CountingSemaphore semaphore = new _03_CountingSemaphore(permits);
        SharedData sharedData = new SharedData(semaphore);

        int threadCount = 5; // 전체 스레드 개수

        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(sharedData::sum);
            threads[i].start();
        }

        for (int i = 0; i < threadCount; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("sum: " + sharedData.getSum());
    }
}
