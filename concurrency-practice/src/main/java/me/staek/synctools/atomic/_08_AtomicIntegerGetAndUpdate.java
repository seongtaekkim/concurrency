package me.staek.synctools.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger - getAndUpdate
 *
 * IntUnaryOperator 를 이용해서 로직을 전달하면 원자적인 처리가 가능한 기능
 *
 */
public class _08_AtomicIntegerGetAndUpdate {
    private static AtomicInteger account = new AtomicInteger(1000);
    private static int account2 = 1000;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                int withdrawalAmount = 500;
                int updatedBalance = account.getAndUpdate(balance -> {
                    if (balance >= withdrawalAmount) {
                        return balance - withdrawalAmount;
                    } else {
                        return -1; // 출금실패
                    }
                });
                if (updatedBalance == -1) {
                    System.out.println(Thread.currentThread().getName() + " : 잔고부족: " + account.get());
                } else {
                    System.out.println(Thread.currentThread().getName() + " : 출금 후 잔고: " + account.get());
                }
            }).start();

        }
        Thread.sleep(1000);
        System.out.println("====================================");
        System.out.println("====================================");
        System.out.println("====================================");

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                int withdrawalAmount = 500;
                int updatedBalance = 0;
                synchronized (_08_AtomicIntegerGetAndUpdate.class) {
                    if (account2 >= withdrawalAmount) {
                        updatedBalance = account2 - withdrawalAmount; // 출금 성공
                        account2 = updatedBalance;
                    } else {
                        updatedBalance = -1;
                    }
                }

                if (updatedBalance < 0) {
                    System.out.println(Thread.currentThread().getName() + " : 잔고부족: " + updatedBalance);
                } else {
                    System.out.println(Thread.currentThread().getName() + " : 출금 후 잔고: " + updatedBalance);
                }
            }).start();
        }
    }
}
