package me.staek.thread.threadgroup;

/**
 * ThreadGroup 생성 방법과 조회방법
 */
public class _01_ThreadGroup {

    public static void main(String[] args) {
        // 메인 스레드 그룹
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println(mainGroup.getName());

        // 메인 스레드 그룹 상위 ? // system
        System.out.println(mainGroup.getParent().getName());

        // 새로운 스레드 그룹 생성
        ThreadGroup customGroup = new ThreadGroup("Custom Thread Group");

        // 기본 스레드 그룹에 속한 스레드 생성
        Thread defaultGroupThread = new Thread(new GroupRunnable(), "DefaultGroupThread");

        // 메인 스레드 그룹에 속한 스레드 생성
        Thread mainGroupThread = new Thread(mainGroup, new GroupRunnable(), "MainGroupThread");

        // 직접 생성한 스레드 그룹에 속한 스레드 생성
        Thread customGroupThread = new Thread(customGroup, new GroupRunnable(), "CustomGroupThread");

        defaultGroupThread.start();
        mainGroupThread.start();
        customGroupThread.start();
    }

    static class GroupRunnable implements Runnable {
        @Override
        public void run() {
            Thread currentThread = Thread.currentThread();
            System.out.println(currentThread.getName() + " 는 " + currentThread.getThreadGroup().getName() + " 에 속한다");
        }
    }
}
