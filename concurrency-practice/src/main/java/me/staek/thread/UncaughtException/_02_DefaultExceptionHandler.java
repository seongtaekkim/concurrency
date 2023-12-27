package me.staek.thread.UncaughtException;

/**
 * 스레드가 비정상적으로 종료되었거나 특정한 예외를 스레드 외부에서 캐치하기 위해
 * UncaughtExceptionHandler 인터페이스가 제공된다.
 *
 * Thread.setDefaultUncaughtExceptionHandler()
 * - 모든 thread 에 대한 uncaughtException() 를 처리하는 메서드.
 */
public class _02_DefaultExceptionHandler {

    public static void main(String[] args) {

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + ": 예외발생 " + e);
            }
        });

        Thread thread1 = new Thread(() -> {
            throw new RuntimeException(Thread.currentThread().getName());
        });

        Thread thread2 = new Thread(() -> {
            throw new RuntimeException(Thread.currentThread().getName());
        });

        thread1.start();
        thread2.start();
    }
}
