package me.staek.nonblocking.nonblock;

/**
 * synchronous & blocking
 * <p>
 * synchronous - 작업을 같은 스레드에서 실행
 * blocking - 작업 완료될 때까지 대기
 */
public class _02_SyncBlocking {

    public static void main(String[] args) {
        blocking();
        System.out.println("메인 스레드 종료");
    }

    public static void blocking() {

        try {
            Thread.sleep(3000);
            System.out.println("작업 종료");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
