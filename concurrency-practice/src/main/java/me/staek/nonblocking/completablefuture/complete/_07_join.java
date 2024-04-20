package me.staek.nonblocking.completablefuture.complete;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture join
 * - 작업이 완료될 때 까지 대기하고 결과를 반환한다
 * - 언체크드 예외로서 발생하는 예외에 대한 처리는 필요 없지만 별도의 대응은 필요함.
 */
public class _07_join {
    public static void main(String[] args) {

        MyClass myService = new MyClass();

        CompletableFuture<String> service1Future = CompletableFuture.supplyAsync(() -> myService.mymethod("Service1"));
        CompletableFuture<String> service2Future = CompletableFuture.supplyAsync(() -> myService.mymethod("Service2"));
        CompletableFuture<String> service3Future = CompletableFuture.supplyAsync(() -> myService.mymethod("Service3"));

        CompletableFuture<Void> allOf = CompletableFuture.allOf(service1Future, service2Future, service3Future);
        allOf.join(); // 모든 작업이 완료될 때까지 대기

        // 각 서비스의 결과를 가져올 수 있음, 결과는 모두 완료된 상태임
        String result1 = service1Future.join();
        String result2 = service2Future.join();
        String result3 = service3Future.join();

        // 결과를 처리하는 로직을 추가
        System.out.println("Service1 결과: " + result1);
        System.out.println("Service2 결과: " + result2);
        System.out.println("Service3 결과: " + result3);

    }

    static class MyClass {
        public String mymethod(String serviceName) {
            return serviceName.toUpperCase();
        }
    }
}
