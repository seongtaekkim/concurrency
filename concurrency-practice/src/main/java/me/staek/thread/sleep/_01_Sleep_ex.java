package me.staek.thread.sleep;

public class _01_Sleep_ex {
    public static void main(String[] args) {
        try {
            Thread.sleep(2000);
            System.out.println("Hello World");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
