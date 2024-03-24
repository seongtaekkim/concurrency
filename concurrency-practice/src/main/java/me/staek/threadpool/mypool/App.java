package me.staek.threadpool.mypool;

/**
 * MyThreadPool test
 *
 * 1. 최초 작업스레드 개수 지정
 * 2. 필요한 작업을 인자로 호출
 * 3. 스레드풀 종료
 *
 */
public class App {
    public static void main(String[] args) {
        MyThreadPool pool = new MyThreadPool(3);

        for (int i=0 ;i<10 ; i++) {
            pool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " 시작");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " 종료");
            });
        }

        pool.shutdown();
    }
}
