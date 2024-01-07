package me.staek.thread.threadgroup;

/**
 * ThreadGroup 에 interrupt()를 걸면 해당 스레드 모두 예외가 발생한다.
 */
public class _03_ThreadGroupInterrupt {
    public static void main(String[] args) throws InterruptedException {

        ThreadGroup topGroup = new ThreadGroup("상위그룹");
        ThreadGroup subGroup = new ThreadGroup(topGroup, "하위그룹");

        Thread topGroupThread = new Thread(topGroup, () -> {
            while(true){
                System.out.println("상위 그룹 스레드 실행중...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }, "상위그룹스레드");

        Thread subGroupThread = new Thread(subGroup, () -> {
            while(true){
                System.out.println("하위 그룹 스레드 실행중...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }, "하위그룹스레드");

        Thread subGroupThread2 = new Thread(subGroup, () -> {
            while(true){
                System.out.println("하위 그룹 스레드 실행중...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }, "하위그룹스레드");

        topGroupThread.start();
        subGroupThread.start();
        subGroupThread2.start();

        Thread.sleep(3000);  // 스레드들이 실행되게 잠시 대기

        System.out.println("그룹 스레드들 중지...");
        subGroup.interrupt();
        topGroup.interrupt();
    }
}
