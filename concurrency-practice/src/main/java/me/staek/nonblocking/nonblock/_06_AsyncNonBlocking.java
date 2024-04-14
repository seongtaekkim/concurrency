package me.staek.nonblocking.nonblock;


/**
 * asynchronous & nonblocking
 * <p>
 * asynchronous - 작업을 비동기로 실행
 * nonblocking - 작업요청 후 대기한다.
 */
public class _06_AsyncNonBlocking {

    static Integer resource = null;

    interface Callback {
        int onComplete(int result);
    }

    // 메인 메소드
    public static void main(String[] args) {
        // 비동기 작업 수행
        doAsyncWork(new Callback() {
            @Override
            public int onComplete(int result) {
                System.out.println("비동기 작업 결과: " + result);
                return result;
            }
        });

        // 아래처럼하면 동기 & 논블라킹
//        while (true) {
//            if (resource != null)
//                break;
//            else
//                System.out.println("메인스레드 작업");
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//        System.out.println(resource);
    }

    // 비동기 작업을 수행하는 메소드
    private static void doAsyncWork(Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("비동기 작업 시작");
                    Thread.sleep(2000);
                    resource = callback.onComplete(42);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
