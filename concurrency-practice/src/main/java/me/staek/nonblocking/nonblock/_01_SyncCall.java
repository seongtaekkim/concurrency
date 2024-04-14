package me.staek.nonblocking.nonblock;

/**
 * synchronous
 * <p>
 * synchronous - 작업을 비동기로 실행했으나, 완료되었는지 계속확인하기에 결국 동기적으로 동작한다.
 */
public class _01_SyncCall {

    public static void main(String[] args) {

        // 동기적인 실행
        int result = syncCall();
        System.out.println(Thread.currentThread().getName() + ": 요청작업결과: " + result);
        System.out.println("메인 스레드 종료");
    }

    public static int syncCall() {

        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + ": 동기실행 완료");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 10;
    }
}
