package me.staek.threadpool.executeservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 작업이 제출되면 현재 사용 가능한 스레드가 있는지 확인하고 없으면 새 스레드를 생성하여 작업을 수행한다.
 * 일반적으로 많은 수의 짧은 작업들을 병렬로 실행하면서 처리 성능을 향상시킬 수 있으며 60초 동안 사용되지 않은 스레드는 자동 종료되고 캐시에서 제거된다
 * 작업을 담아놓고 대기시키는 블록킹 큐가 아닌 스레드 간 작업을 주고 받는 동기 큐를 사용하기 때문에 작업이 제출되면 해당 작업이 즉시 실행된다
 * 스레드의 개수를 제한하지 않으며 작업 요청이 많을 때는 스레드 수가 증가하고 작업 요청이 감소하면 유휴 상태의 스레드가 종료되어 스레드 풀의 스레드 개수가 조절된다
 */
public class _14_CachedThreadPool {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 1; i <= 20; i++) {
            executorService.submit(() -> {
                System.out.println("Thread : " + Thread.currentThread().getName());
            });
        }

        executorService.shutdown();
    }
}
