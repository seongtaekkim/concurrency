package me.staek.synchronization.monitor;

/**
 * instance synchronized method 와 static synchronized method 는 모니터가 다르다.
 * 하나는 class, 하나는 instance
 * 그러므로 다른 스레드가 서로 접근할 수 있다.
 */
public class _04_InstanceStaticMethodSynchronized {

    private static int staticCount = 0;
    private int instanceCount = 0;

    public synchronized void incrementInstanceCount(){
        instanceCount++;
        staticCount++; // 상호배제가 안되기 때문에 총 합이 달라진다.
        System.out.println(Thread.currentThread().getName() + " 가 인스턴스 카운터를 증가시켰습니다. 현재 값:" + instanceCount);
    }
    public static synchronized void incrementStaticCount(){
        staticCount++;
        System.out.println(Thread.currentThread().getName() + " 가 장적 카운터를 증가시켰습니다. 현재 값:" + staticCount);
    }

    public static void main(String[] args) {
        _04_InstanceStaticMethodSynchronized example = new _04_InstanceStaticMethodSynchronized();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                example.incrementInstanceCount();
            }
        },"스레드 1");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                example.incrementInstanceCount();
            }
        },"스레드 2");

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                _04_InstanceStaticMethodSynchronized.incrementStaticCount();
            }
        },"스레드 3");

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                _04_InstanceStaticMethodSynchronized.incrementStaticCount();
            }
        },"스레드 4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("최종값:"  + example.instanceCount);
        System.out.println("최종값:"  + staticCount);
    }
}
