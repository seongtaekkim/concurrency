package me.staek.nonblocking.nonblock;

/**
 * nonblocking
 * <p>
 * nonblocking - 작업요청 후 대기하지 않는다.
 */
public class _04_AsyncCall {

    public static void main(String[] args) {

        // 비동기 작업 실행
        asyncCall();
        System.out.println("결과를 기다리지 않음");
        System.out.println(Thread.currentThread().getName() + ": 메인 스레드 종료");
    }

    public static void asyncCall() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //비동기 실행
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + ": 비동기 작업 실행 완료");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }

}
