package me.staek.synchronization.safe_thread;

/**
 * Immutable 객체가 공유객체면, thread-safe 하다.
 */
public class _01_Immutable implements Runnable {
    private ImmutableUser user;

    public _01_Immutable(ImmutableUser user) {
        this.user = user;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " " + user);
    }

    public static void main(String[] args) {
        ImmutableUser user = new ImmutableUser("staek", 33);
        for (int i = 0; i < 10; i++) {
            new Thread(new _01_Immutable(user)).start();
        }
    }
}

final class ImmutableUser {
    private final String name;
    private final int age;

    public ImmutableUser(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
