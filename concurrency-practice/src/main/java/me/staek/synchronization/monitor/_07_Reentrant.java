package me.staek.synchronization.monitor;

class Parent {
    public synchronized void parentMethod() {
        System.out.println("call Parent method");
    }
}

class Child extends Parent {

    public synchronized void childMethod() {
        System.out.println("call Child method");
        parentMethod();
    }
}

/**
 * 상속관계의 객체의 모니터 락은 같다.
 * 자바의 같은 모니터 락은 재진입이 가능하므로,
 * 예제와 같이 하위 객체의 모니터 내부에서 부모의 모니터 메서드를 호출하여 재진입 할 수 있따.
 */
public class _07_Reentrant {

    public static void main(String[] args) {
        Child child = new Child();

        Thread t1 = new Thread(() -> {
            child.childMethod();
        });

        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
