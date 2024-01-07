package me.staek.synchronization.volatileex;

/**
 * volatile이 없는경우, 있는경우 가시성 차이를 테스트
 */
public class Volatile_test {
    // volatile 키워드 추가
//   volatile boolean running = true;
   boolean running = true;

    public void volatileTest() {
        new Thread(() -> {
            int count = 0;
            // running을 캐시에 넣어두고 비교중
            // thread2에서 running 값을 변경해도 인지하지 못한다.
            while (running) {
                // volatile 없이 회피할 수 있다.
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                count++;
            }
            System.out.println("Thread 1 종료. Count: " + count);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
            System.out.println("Thread 2 종료 중..");
            running = false; // false로 변경한 결과를 캐시에만 저장
        }).start();
    }

    public static void main(String[] args) {
        new Volatile_test().volatileTest();
    }
}
