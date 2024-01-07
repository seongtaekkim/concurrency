package me.staek.synchronization.monitor;
/**
 * static synchronised method 는 종류가 하나이기 때문에
 * 서로 상호배제가 된다.
 */
public class _03_StaticMethodSynchronized {

    private static int count = 0;

    public static synchronized void increment(){
        count++;
        System.out.println(Thread.currentThread().getName() + " 가 증가시켰습니다. 현재 값:" + count);
    }
    public static synchronized void decrement(){
        count--;
        System.out.println(Thread.currentThread().getName() + " 가 감소시켰습니다. 현재 값:" + count);
    }

    public static int getCount(){
        return count;
    }

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                _03_StaticMethodSynchronized.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                _03_StaticMethodSynchronized.decrement();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("0이되어야함:"  + _03_StaticMethodSynchronized.getCount());
    }
}
