package me.staek.threadpool.executeservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 메서드의 매개변수로 원하는 스레드 개수를 지정할 수 있으며 지정한 개수만큼 스레드가 생성되어 작업을 처리하게 된다
 * 스레드 풀은 모든 스레드가 공유하는 대기열을 가지고 있으며 대기열은 무한한 크기의 대기열로 스레드가 가용 상태이면 대기 중인 작업을 처리한다
 * 모든 스레드가 활성 상태이고 작업이 추가적으로 제출되면 스레드가 사용 가능한 상태가 될 때까지 작업들은 대기열에서 대기한다
 * 스레드 풀이 종료하기 전에 어떤 스레드가 실패로 종료하게 되면 필요한 경우 새로운 스레드가 대신하게 된다
 * nThreads 이 0 보다 작거나 같으면 IllegalArgumentException 예외가 발생한다
 */
public class _12_FixedThreadPool {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            executorService.submit(() -> {
                System.out.println("Thread : " + Thread.currentThread().getName());
            });
        }

        executorService.shutdown();
    }
}
