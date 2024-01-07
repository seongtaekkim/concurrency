package me.staek.os;

import java.util.ArrayList;
import java.util.List;

/**
 * cpu core 개수만큼 데이터를 생성하고, Stream의 병렬처리를 유도해본다.
 * - 처리할 데이터 개수가 cpu core보다 작으면 1회씩만 처리하면 되지만
 * - 2배만큼 많다면 2회씩 처리해야 할 것이다.
 * - 1개만큼 더 많다고 해도 최종 처리완료 시간은 2회분 일 것이다.
 *
 * Concurrency 는 작업스레드가 i/o 등 대기할 요소가 많을 때, cpu 공백을 메울 다른 작업쓰레드가 있을 경우 효과가 좋다.
 * (core 개수 < thread 개수)
 */
public class Concurrency {
    public static void main(String[] args) {

        int cpuCores = Runtime.getRuntime().availableProcessors() * 2; // 코어개수 2배 개수
//        int cpuCores = Runtime.getRuntime().availableProcessors() + 1; // 코어개수 + 1개

        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < cpuCores; i++) {
            data.add(i);
        }

        long startTime2 = System.currentTimeMillis();
        long sum2 = data.parallelStream()
                .mapToLong(i -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return i * i;
                })
                .sum();

        long endTime2 = System.currentTimeMillis();

        System.out.println("걸린 시간: " + (endTime2 - startTime2) + "ms");
    }
}
