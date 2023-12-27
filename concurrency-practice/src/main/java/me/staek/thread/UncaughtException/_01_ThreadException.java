package me.staek.thread.UncaughtException;

/**
 * run() 은 예외를 던질 수 없도록 되어있다.
 * 따라서 예외를 던지더라도 thread 밖에서 예외를 catch 할 수 없다.
 */
public class _01_ThreadException {

    public static void main(String[] args) {
        try {
            new Thread(() -> {
                throw new RuntimeException(Thread.currentThread().getName() + ": 예외발생");
            }).start();
        }catch(Exception e){
            System.out.println("관리자에게 알림: " + e.getMessage());
        }
    }
}
