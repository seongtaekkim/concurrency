package me.staek.lock.reentrantlock.api;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock 타입의 ReentrantReadWriteLock Read 관점
 * - Read 로직이 대부분인 경우 동시성이 높다.
 */
public class _09_ReentrantReadWriteLock_ReadAspect {

    public static void main(String[] args) throws InterruptedException {

        String name = "staek";
        HashMap<String, Integer> accounts = new HashMap<>();
        accounts.put(name, 10000);
        try (BankAccount bankAccount = new BankAccount(accounts, new ReentrantReadWriteLock())) {

            Thread[] threads = new Thread[20];

            for (int i = 0; i < 10; i++) {
                threads[i] = new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + ": get account: " + bankAccount.getAccount(name));
                }, "Thread-" + i);
                threads[i].start();
            }

            Thread writeThread1 = new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + ":======= deposit: " + bankAccount.deposit(name, 10000));
            }, "Write Thread1");
            writeThread1.start();

            Thread writeThread2 = new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + ":======== withdraw: " + bankAccount.withdraw(name,1000));
            }, "Write Thread2");
            writeThread2.start();

            for (int i = 10; i < 20; i++) {
                threads[i] = new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + ": get account: " + bankAccount.getAccount(name));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }, "Thread-" + i);
                threads[i].start();
            }


            for (int i = 0; i < threads.length; i++)
                threads[i].join();
            writeThread1.join();
            writeThread2.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
