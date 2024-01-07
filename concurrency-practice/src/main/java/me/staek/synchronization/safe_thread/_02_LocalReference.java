package me.staek.synchronization.safe_thread;

/**
 * thread 작업공간에서 생성되는 객체는 그 자체로 외부 thread와 격리되어 있어 thread-safe 하다.
 *
 * thread 작업공간 밖에서 생성되는 객체는 다른 thread가 접근 가능하기에 안전하지 않다.
 */
public class _02_LocalReference {
    class LocalObject {
        private int value;

        public void increment() {
            value++;
        }

        @Override
        public String toString() {
            return "LocalObject{" + "value=" + value + '}';
        }
    }

    /**
     * 다른 스레드가 접근 가능하므로 안전하지 않다.
     */
//    LocalObject localObject = new LocalObject();

    public void useLocalObject() {
        /**
         * 지역객체 참조는 외부 스레드로부터 독립되어 있어 thread-safe 하다.
         */
        LocalObject localObject = new LocalObject();

        for (int i = 0; i < 5; i++) {
            localObject.increment();
            System.out.println(Thread.currentThread().getName() + " - " + localObject);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        _02_LocalReference example = new _02_LocalReference();

        Thread thread1 = new Thread(() -> {
            example.useLocalObject();
        }, "Thread-1");

        Thread thread2 = new Thread(() -> {
            example.useLocalObject();
        }, "Thread-2");

        thread1.start();
        thread2.start();
    }
}
