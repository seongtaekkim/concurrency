package me.staek.synchronization.safe_thread;

/**
 * 스레드 생성시 인자에 객체를 넣을 경우,
 * - 스레드별로 생성되는 객체를 입력하면, 외부 스레드와 공유하지 않으므로 thread-safe하다.
 * - 이미 존재하는 객체를 입력하면, 외부 스레드가 접근가능하므로 안전하지 않다.
 */
public class _04_MemberReference {
    public static void main(String[] args) throws InterruptedException {

        /**
         * 안전함
         */
        new Thread(new MyRunnable(new Study("Java"))).start();
        new Thread(new MyRunnable(new Study("Java"))).start();

        Thread.sleep(1000);
        System.out.println("============================================================");

        /**
         * 안전하지 않음
         */
        Study study = new Study("Java");
        new Thread(new MyRunnable(study)).start();
        new Thread(new MyRunnable(study)).start();
    }
}

class MyRunnable implements Runnable {
    private Study study;

    public MyRunnable(Study study) {
        this.study = study;
    }

    @Override
    public void run() {
        study.changeName(Thread.currentThread().getName());
    }
}

class Study {
    private Subject subject;

    public Study(String name) {
        this.subject = new Subject(name);
    }

    public synchronized void changeName(String name) {
        String oldName = subject.getName();
        subject.setName(name);
        System.out.println("원래이름: " + oldName + ", 변경된이름: " + subject.getName());
    }
}

class Subject {
    private String name;

    public Subject(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
