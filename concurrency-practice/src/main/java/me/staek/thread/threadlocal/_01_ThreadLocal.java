package me.staek.thread.threadlocal;

/**
 * ThreadLocal
 * - Thread별 격리된 데이터를 관리.
 * - ThreadLocal.withInitial()
 *   - default data setting
 *   - remove를 하더라도 유지됨
 */
public class _01_ThreadLocal {
//    private static final ThreadLocal<String> LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<String> LOCAL = ThreadLocal.withInitial(() -> "default data");
    public static void main(String[] args) {

        Thread th1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": " + LOCAL.get());
            LOCAL.set("data: " + Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + ": " + LOCAL.get());
            LOCAL.remove();
            System.out.println(Thread.currentThread().getName() + ": " + LOCAL.get());
        }, "Thread-01");

        Thread th2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": " + LOCAL.get());
            LOCAL.set("data: " + Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + ": " + LOCAL.get());
            LOCAL.remove();
            System.out.println(Thread.currentThread().getName() + ": " + LOCAL.get());
        }, "Thread-02");

        th1.start();
        th2.start();
    }
}
