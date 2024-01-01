package me.staek.synchronization.monitor;


/**
 * wait(), notify(), notifyAll()은 synchronized 블록 안에서만 사용할 수있고
 * 그렇지 않으면 예외 (java.lang.IllegalMonitorStateException)가 발생한다.
 */
class NomalClassA {
    void methodA() {
        try {
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
public class WrongSynchronized {
    public static void main(String[] args) {
        new NomalClassA().methodA();
    }
}
