package me.staek.synctools.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReference
 * - 여러 스레드에서 compareAndSet 실행하는 예제
 */
public class _07_AtomicReference_API2 {

    public static void main(String[] args) throws InterruptedException {

        User user1 = new User("seongtki", "123");
        User user2 = new User("staek", "123");

        AtomicReference<Object> reference = new AtomicReference<>(user1);

        Thread t1 = new Thread(() -> {
            if (reference.compareAndSet(user1, user2)) {
                System.out.println(Thread.currentThread().getName() + " 변경성공");
            } else {
                System.out.println(Thread.currentThread().getName() + " 변경실패");
            }
        });

        Thread t2 = new Thread(() -> {
            User user3 = new User("seongatekkim", "123");
            if (reference.compareAndSet(user2, user3)) {
                System.out.println(Thread.currentThread().getName() + " 변경성공");
            } else {
                System.out.println(Thread.currentThread().getName() + " 변경실패");
            }
        });


        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(reference.get());

    }

    static class User {
        private final String name;
        private final String password;

        public User(String name, String password) {
            this.name = name;
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public String getPassword() {
            return password;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }
}
