package me.staek.nonblocking.completablefuture.forkjoinpool;

import java.util.stream.IntStream;

/**
 * 스레드 블로킹
 * I/O 바운드 작업은 스레드를 블록시키는 작업으로 commonPool()에서 실행 시 스레드 부족으로 다른 작업이 지연 될 수 있다
 * 별도의 스레드 풀을 생성하여 I/O 작업과 CPU 작업을 분리하고 I/O 작업을 별도의 스레드에서 처리하는 것을 고려해야 한다
 * <p>
 * Starvation (기아 상태)
 * I/O 작업이 지속적으로 블록되면 CPU 작업이 실행 기회를 얻지 못하고 기아 상태에 빠질 수 있다
 * 스레드 풀을 분리하여 CPU 작업이 충분한 실행 기회를 얻도록 관리해야 하고 대신 스레드가 필요 이상으로 생성되어 리소스 비용이 커지지 않도록 해야 한다
 */
public class ParallelStream {
    public static void main(String[] args) {

        int[] array = IntStream.range(0, 10).toArray();
        long sum = IntStream.of(array).parallel().sum();

        System.out.println("The sum is " + sum);
    }
}
