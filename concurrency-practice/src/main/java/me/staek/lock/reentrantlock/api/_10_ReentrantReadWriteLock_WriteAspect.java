package me.staek.lock.reentrantlock.api;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock 타입의 ReentrantReadWriteLock Write 관점
 * - Write 로직이 대부분인 경우, 대부분이 상호배제라서 동시성이 높지는 않다.
 */
public class _10_ReentrantReadWriteLock_WriteAspect {
    public static void main(String[] args) throws InterruptedException {
        String name = "staek";
        HashMap<String, Integer> accounts = new HashMap<>();
        accounts.put(name, 10000);


        try (BankAccount bankAccount = new BankAccount(accounts, new ReentrantReadWriteLock())) {
            // 읽기 스레드가 잔액 조회
            Thread readThread1 = new Thread(() -> {
                int balance = bankAccount.getAccount(name);
                System.out.println("현재 잔액: " + balance);
            });


            Thread[] threads = new Thread[10];
            // 여러 스레드가 출금
            for (int i = 0; i < 10; i++) {
                threads[i] = new Thread(() -> {
                    int withdrawAmount = (int) (Math.random() * 1000);
                    System.out.println("출금: " + withdrawAmount + " 남은돈: " + bankAccount.withdraw(name, withdrawAmount));
                });
                threads[i].start();
            }

            // 읽기 스레드가 잔액 조회
            Thread readThread2 = new Thread(() -> {
                int balance = bankAccount.getAccount(name);
                System.out.println("현재 잔액: " + balance);
            });

            readThread1.start();
            readThread2.start();
            for (int i = 0; i < 10; i++)
                threads[i].join();
            readThread1.join();
            readThread2.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
