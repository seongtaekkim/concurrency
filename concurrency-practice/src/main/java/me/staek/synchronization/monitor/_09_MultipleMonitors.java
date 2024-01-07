package me.staek.synchronization.monitor;

/**
 * 입금,출금,송금으로
 * 모니터락 재진입과, 두가지 락 사용에 대한 예제.
 *
 * - 문제점이 있다.
 */
class BankAccount {
    private double balance;
    private final Object lock = new Object();

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        synchronized (lock) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        synchronized (lock) {
            if (balance < amount) {
                return false;
            }
            balance -= amount;
            return true;
        }
    }

    public boolean transfer(BankAccount to, double amount) {
        synchronized (this.lock) {
            if (this.withdraw(amount)) {
                synchronized (to.lock) {
                    to.deposit(amount);
                    return true;
                }
            }
            return false;
        }
    }

    public double getBalance() {
        synchronized (lock) {
            return balance;
        }
    }

}

public class _09_MultipleMonitors {

    public static void main(String[] args) throws InterruptedException {
        BankAccount accountA = new BankAccount(1000);
        BankAccount accountB = new BankAccount(1000);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                boolean result = accountA.transfer(accountB, 10);
                if (result) {
                    System.out.println("accountA에서 accountB로 10 송금 성공");
                } else {
                    System.out.println("accountA에서 accountB로 10 송금 실패");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                boolean result = accountB.transfer(accountA, 10);
                if (result) {
                    System.out.println("accountB에서 accountA로 10 송금 성공");
                } else {
                    System.out.println("accountB에서 accountA로 10 송금 실패");
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("accountA 잔액: " + accountA.getBalance());
        System.out.println("accountB 잔액: " + accountB.getBalance());
    }
}
