package me.staek.synchronization.monitor;

/**
 * instance synchronised method 는 종류가 하나이기 때문에
 * 서로 상호배제가 된다.
 */
public class _05_InstanceBlockSynchronized {

    private int count = 0;

    private Object lockObject = new Object();

    public void incrementBlockThis(){
        synchronized (this){
            count++;
            System.out.println(Thread.currentThread().getName() + " 가 This 에 의해 블록 동기화 함 : " + count);
        }
    }
    public void incrementBlockLockObject() {
        synchronized (lockObject){
            count++;
            System.out.println(Thread.currentThread().getName() + " 가 LockObject 에 의해 블록 동기화 함 : " + count);
        }
    }
    public static void main(String[] args){

        _05_InstanceBlockSynchronized example = new _05_InstanceBlockSynchronized();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                example.incrementBlockThis();
            }
        },"스레드 1");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                example.incrementBlockLockObject();
            }
        },"스레드 3");


        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("최종값:"  + example.count);

    }
}
