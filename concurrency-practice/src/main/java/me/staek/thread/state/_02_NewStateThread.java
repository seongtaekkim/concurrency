package me.staek.thread.state;

/**
 * NEW
 */
public class _02_NewStateThread {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
        });
        System.out.println("state: " + thread.getState());
    }

}
