package me.staek.synchronization.safe_thread;

/**
 * thread 작업공간 내부에 지역변수, 매개변수를 정의하면 각 스레드는 이 변수의 스택에 독립된 복사본을 갖게 된다. - thread-safe
 *
 * thread 작업공간 외부의 변수를 참조하게되면, 외부 thread가 참조 가능하므로 안전하지 않다.
 */
public class _03_LocalVariable {

    /**
     * 안전하지 않음
     */
//    int variable = 0;
public void sumCount(int init) {

    /**
     * 지역변수 - 안전
     */
    int variable = 0;

    for (int i = 1; i <= 100; i++) {
        variable += i;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    variable += init;
    System.out.println(Thread.currentThread().getName() + " sum: " + variable);
}

    public static void main(String[] args) {
        _03_LocalVariable object = new _03_LocalVariable();

        Thread thread1 = new Thread(() -> {
            object.sumCount(100);
        }, "Thread-1");

        Thread thread2 = new Thread(() -> {
            object.sumCount(10);
        }, "Thread-2");

        thread1.start();
        thread2.start();
    }
}
