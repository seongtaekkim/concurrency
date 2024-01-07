package me.staek.synchronization.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Semaphore API test example
 */
public class _01_Semaphore_test {
    private static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            Thread thread = new Thread(new _01_Worker(i, semaphore));
            thread.start();
        }
    }
}
