package me.staek.synchronization.monitor;

/**
 * synchronized 종류 4가지
 *
 * static method
 * static block
 * => 같은종류 => 동시접근안됨
 *
 * instance method
 * instance block
 * => 같은종류 => 동시접근안됨
 */
public class _01_Synchronized_type {
    private int instanceCount = 0;
    private static int staticCount = 0;

    public synchronized void instanceMethod() {
        instanceCount++;
        System.out.println("인스턴스 메서드 동기화: " + instanceCount);
    }

    public static synchronized void staticMethod() {
        staticCount++;
        System.out.println("정적 메서드 동기화: " + staticCount);
    }

    public void instanceBlock() {
        synchronized (this) {
            instanceCount++;
            System.out.println("인스턴스 블록 동기화: " + instanceCount);
        }
    }

    public static void staticBlock() {
        synchronized (_01_Synchronized_type.class) {
            staticCount++;
            System.out.println("정적 블록 동기화: " + staticCount);
        }
    }

    public static void main(String[] args) {
        _01_Synchronized_type example = new _01_Synchronized_type();

        new Thread(example::instanceMethod).start();
        new Thread(example::instanceBlock).start();
        new Thread(_01_Synchronized_type::staticMethod).start();
        new Thread(_01_Synchronized_type::staticBlock).start();
    }
}
