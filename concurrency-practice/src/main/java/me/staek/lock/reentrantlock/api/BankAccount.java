package me.staek.lock.reentrantlock.api;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * 09,10 예제를 위한 class
 * - 예금, 출금에 write lock
 * - 현재금액 조회에 read lock 을 적용한 계좌관리 클래스이다.
 */
public class BankAccount implements AutoCloseable {
    private ReadWriteLock lock;
    private Map<String, Integer> accounts;

    public BankAccount(Map<String, Integer> accounts, ReadWriteLock lock) {
        Objects.requireNonNull(accounts);
        this.lock = lock;
        this.accounts = accounts;
    }

    public int getAccount(String name) {
        lock.readLock().lock();
        try {
            Thread.sleep(1000);
            return this.accounts.get(name);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.readLock().unlock();
        }
    }

    public int deposit(String name, int amount) {
        lock.writeLock().lock();
        try {
            Thread.sleep(2000);
            int sum = this.accounts.get(name)+amount;
            this.accounts.put(name, sum);
            return sum;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int withdraw(String name, int amount) {
        lock.writeLock().lock();
        try{
            Thread.sleep(300);
            int currAmount = accounts.get(name);
            if (currAmount >= amount) {
                currAmount -= amount;
                this.accounts.put(name, currAmount);
             } else
                System.out.println(Thread.currentThread().getName() + " - 출금 실패, 잔액부족");
            return currAmount;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void close() throws Exception {
        this.accounts = null;
        lock = null;
    }
}
