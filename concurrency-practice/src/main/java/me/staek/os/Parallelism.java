package me.staek.os;

import java.util.ArrayList;
import java.util.List;

/**
 * 병렬성은 CPU 가 동시에 많은 일을 수행하는 것에 중점을 둔다. 즉 CPU 가 놀지 않고 최대한 바쁘게 동작해야 한다
 *
 * Parallel은 cpu연산을 주로 할 때 코어 개수보다 작업 스레드가 같거나 작아야
 * 가장 큰 효과가 있다.
 * - context switching 을 안해도 되기 때문.
 *
 */
public class Parallelism {
    public static void main(String[] args) {
        int cpuCores = 1;
//        int cpuCores = Runtime.getRuntime().availableProcessors();

        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < cpuCores; i++) {
            data.add(i);
        }

        long startTime1 = System.currentTimeMillis();
        long sum1 = data.parallelStream()
                .mapToLong(i -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return i * i;
                })
                .sum();

        long endTime1 = System.currentTimeMillis();

        System.out.println("걸린 시간: " + (endTime1 - startTime1) + "ms");
        System.out.println("결과1: " + sum1);

    }
}
