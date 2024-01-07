package me.staek.synchronization.monitor;


/**
 * static synchronised method 는 종류가 하나이기 때문에
 * 서로 상호배제가 된다.
 *
 */
class MethodBlock{}
public class _06_StaticBlockSynchronized {

    private static int count = 0;

    public static void incrementBlockClass(){
        synchronized (_06_StaticBlockSynchronized.class){
            count++;
            System.out.println(Thread.currentThread().getName() + " 가 StaticBlockSynchronizedExamples 에 의해 블록 동기화 함 : " + count);
        }
    }
    public static void incrementBlockOtherClass() {
        synchronized (MethodBlock.class){
            count++;
            System.out.println(Thread.currentThread().getName() + " 가 MethodBlock 에 의해 블록 동기화 함 : " + count);
        }
    }

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                _06_StaticBlockSynchronized.incrementBlockClass();
            }
        },"스레드 1");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                _06_StaticBlockSynchronized.incrementBlockOtherClass();
            }
        },"스레드 2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("최종값:"  + count);
    }
}
