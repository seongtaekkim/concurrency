package me.staek.thread.UncaughtException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * thread.setUncaughtExceptionHandler()
 * - 특정 스레드에서 발생하는 uncaughtException() 를 처리하는 인스턴스 메서드
 * - setDefaultUncaughtExceptionHandler() 보다 우선순위가 높다.
 */
public class _03_UncaughtExceptionHandler {
    private static final Logger LOGGER = Logger.getLogger(_03_UncaughtExceptionHandler.class.getName());

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started");
            throw new RuntimeException("치명적인 예외 발생");
        });

        thread.setUncaughtExceptionHandler((t, e) -> {
            LOGGER.log(Level.SEVERE, t.getName() + ": 예외발생.", e);
            noti(e);
        });
        thread.start();
    }

    private static void noti(Throwable e) {
        System.out.println(e.getMessage());
    }
}
