package me.staek.synctools.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier (작업스레드 개수, 작업완료 스레드)
 *
 * 1. 모든 스레드가 작업 후 await를 호출하면, 2번째 인자에 정의된 작업완료 스레드를 실행한다.
 * 2. await이 정해진 스레드 개수만큼 호출되지 않으면 무한대기 혹은 의도한 Exception이 발생한다.
 * 3. 작업스레드는, 1번이 완료된 후  await() 이후 작업을 진행하고 종료한다.
 * 4. 3번에서 다시 await()를하면 다시 한번   작업완료 스레드를 호출할 수 있다.
 *
 * 여러 스레드가 할 작업을 나누어서 준다는 점에서 병렬적인 로직처리가 가능하다.
 * => 동시성 문제를 고려할 필요가 없다.
 */
public class _01_CyclicBarrier {
    public static void main(String[] args) {

        Thread[] threads = new Thread[4];
        int[] nums = new int[] {1,2,3,4,5,6,7,8,9,10};
        int[] resultByParallel = new int[threads.length];
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threads.length, new BarrierAction(resultByParallel));

        for (int i=0 ; i<threads.length ; i++) {
            new Thread(new Worker(cyclicBarrier, nums, i, resultByParallel)).start();
        }
    }

    static class BarrierAction implements Runnable {

        private final int[] resultByParallel;

        public BarrierAction(int[] resultByParallel) {
            this.resultByParallel = resultByParallel;
        }

        @Override
        public void run() {
            int sum=0;
            for (int i=0 ; i<resultByParallel.length ; i++) {
                sum += resultByParallel[i];
            }
            System.out.println(sum);
            System.out.println("할 끝");
        }
    }

    static class Worker implements Runnable {

        private CyclicBarrier cyclicBarrier;
        private int[] nums;
        private final int threadId;
        private final int[] resultByParallel;

        public Worker(CyclicBarrier cyclicBarrier, int[] nums, int threadId, int[] resultByParallel) {
            this.cyclicBarrier = cyclicBarrier;
            this.nums = nums.clone();
            this.threadId = threadId;
            this.resultByParallel = resultByParallel;
        }

        @Override
        public void run() {
            try {
                int sum =0;
                for (int i=0 ; i<this.nums.length ; i++) {
                    sum += nums[i];
                }
                this.resultByParallel[threadId] = sum;
//                if (threadId ==1)
//                    cyclicBarrier.reset();
                cyclicBarrier.await();
                System.out.println("끝");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
