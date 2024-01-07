package me.staek.thread.threadlocal;

/**
 * InheritableThreadLocal
 * - 자식스레드가 부모스레드의 ThreadLocal이 관리하는 데이터를 가져다 쓸 수 있다.
 * - 자식스레드는 부모스레드의 ThreadLocal을 수정해서 사용할 수 있지만, 부모스레드에 적용되진 않는다.
 */
public class _02_InheritableThreadLocal {
    private static final InheritableThreadLocal<String> LOCAL = new InheritableThreadLocal();

    public static void main(String[] args) throws InterruptedException {
        LOCAL.set("data: " + Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName() + ": " + LOCAL.get());

        Thread th1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": " + LOCAL.get());
            LOCAL.set("data: " + Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + ": " + LOCAL.get());
        }, "Thread-01");

        th1.start();
        th1.join();
        System.out.println(Thread.currentThread().getName() + ": " + LOCAL.get());
    }
}
