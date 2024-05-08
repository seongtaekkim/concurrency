package me.staek.nonblocking.completablefuture.forkjoinpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinPool - RecursiveTask example
 */
public class _01_CustomForkJoinPool {

    public static void main(String[] args) {

        int[] array = new int[1000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
//        [0,1,2,3,4,5,6,7,8,9]
//        [0,1] = 0
//        [1,2] = 1 = 1
//        [2,3] = 2
//        [3,4] = 3 = 5 = 6
//        [4,5] = 4
//        [5,6] = 5 = 9
//        [6,7] = 6
//        [7,8] = 7 = 13 = 22
//        [8,9] = 8
//        [9,10] = 9 = 17
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
//        ForkJoinPool pool = new ForkJoinPool(200);

        RecursiveTask<Long> task = new CustomRecursiveTask(array, 0, array.length);
        long result = pool.invoke(task);

        System.out.println("result = " + result);
        System.out.println("pool = " + pool);
        System.out.println("stealing = " + pool.getStealCount());

        pool.shutdown();
    }
}
