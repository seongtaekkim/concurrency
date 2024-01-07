package me.staek.synchronization.semaphore;

/**
 * Semaphore 인터페이스 정의
 */
public interface MySemaphore {
    void acquired();
    void release();
}
