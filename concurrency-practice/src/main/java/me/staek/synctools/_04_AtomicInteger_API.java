package me.staek.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

/**
 * AtomicInteger API practice
 *
 * get()
 * set()
 * getAndSet()
 * incrementAndGet()
 * compareAndSet()
 * getAndUpdate(IntUnaryOperator)
 */
public class _04_AtomicInteger_API {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomic = new AtomicInteger(10);

        System.out.println("atomic get(): " + atomic.get()); // 10

        atomic.set(20);
        System.out.println("atomic set(): " + atomic.get()); // 20

        int prev = atomic.getAndSet(30);
        System.out.println("atomic getAndSet(): 이전값: " + prev + " 변경후: " + atomic.get());

        System.out.println("atomic incrementAndGet(): " + atomic.incrementAndGet()); // 31

        boolean updated = atomic.compareAndSet(31, 40);
        System.out.println("atomic compareAndSet(): " + atomic.get()); // 40

        prev = atomic.getAndUpdate(new IntUnaryOperator() {
            @Override
            public int applyAsInt(int operand) {
                return operand * operand;
            }
        });
        System.out.println("atomic getAndUpdate(): 변경전: " + prev + " 변경후: " + atomic.get());

    }
}
