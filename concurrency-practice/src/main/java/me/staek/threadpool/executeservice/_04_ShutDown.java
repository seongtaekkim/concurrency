package me.staek.threadpool.executeservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * ExecutorService - shutdown, shutdownNow
 *
 * isShutdown() -> shutdown() 실행하면 yes 됨 아닌면 no
 * isTerminated() -> 완전히 종료되면 yes
 * awaitTermination(Time) => 정해진 시간동안 기다린다.
 * => 시간이 지나면 메인스레드가 깨어난다
 *
 * isShutdown() => 실행중인 모든 스레드에 interrupt() 한다.
 *              => 만약 sleep, wait 등 로직이 없어 interruptException이 걸리지 않으면 해당 스레드가 종료될 때까지 기다려야 한다.
 *
 *
 */
public class _04_ShutDown {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            executorService.submit(()->{

                /**
                 * 스레드에 sleep 등 InterruptedException가 발생할 수 있는 로직이 있다면,
                 * shutdownNow()에 의해 interrupted 되어 종료될 수 있다.
                 */
//                try {
//                    Thread.sleep(10000);
//                    System.out.println(Thread.currentThread().getName() + ": 작업 종료");
//                } catch (InterruptedException e) {
//                    System.out.println("interrupted!!!");
//                    Thread.currentThread().interrupt();
//                    throw new RuntimeException();
//                }

                /**
                 * 스레드 작업에 InterruptedException가 없으면,
                 * shutdownNow() 에의해 종료되지 못하고, 스레드종료까지 기다려야 한다.
                 */
                Integer a=0;
                for (int j=0 ;j<1_000_000_000 ; j++) {
                    a += 1;
                }
                System.out.println("완료");
                return 100;
            });

        }

        executorService.shutdown();

        try {
            /**
             * awaitTermination() 로 일정시간 메인스레드를 대기시키다가
             * shutdownNow()으로 작업중인 스레드에 interrupt() 해서 즉시종류를 유도한다.
             * 작업스레드에 InterruptedException가 없다면 작업종료시 까지 대기해야 한다.
             */
            if(!executorService.awaitTermination(2, TimeUnit.SECONDS)){
                executorService.shutdownNow();
                System.out.println("스레드 풀 강제 종료 수행");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        if(executorService.isShutdown()){
            System.out.println("스레드 풀 shutdown 여부: " + executorService.isShutdown());
        }
        System.out.println("스레드 풀 terminate 여부: " + executorService.isTerminated());


        while(!executorService.isTerminated()){
            System.out.println("스레드 풀 종료 중..");
        }
        System.out.println("스레드 풀 terminate 여부: " + executorService.isTerminated());
        System.out.println("모든 작업이 종료되고 스레드 풀이 종료됨");
    }
}
