package me.staek.cas;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;


/**
 * AtomicReference
 *
 * get()
 * set()
 * compareAndSet()
 * getAndSet()
 * getAndUpdate()
 */
public class _06_AtomicReference_API {
    public static void main(String[] args) throws InterruptedException {
        AtomicReference<String> reference = new AtomicReference<>("Initial Value");

        System.out.println("AtomicReference get: " + reference.get());

        reference.set("New Value");
        System.out.println("AtomicReference set: " + reference.get());

        boolean is = reference.compareAndSet("New Value", "Updated Value");
        System.out.println("AtomicReference compareAndSet: " + reference.get());

        String prev = reference.getAndSet("Final Value");
        System.out.println("AtomicReference getAndSet(): " + "이전값: " + prev + " 현재값: " + reference.get());

        prev = reference.getAndUpdate(new UnaryOperator<String>() {
            @Override
            public String apply(String s) {
                return s.concat(" append data");
            }
        });

        System.out.println("AtomicReference getAndUpdate(): 이전값: " + prev + " 현재값: " + reference.get());
    }
}
