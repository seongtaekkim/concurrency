package me.staek.synchronization.semaphore;

class SharedData {
    private int value = 0;
    private MySemaphore semaphore;

    public SharedData(MySemaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void sum() {
        semaphore.acquired();
        for(int i=0; i< 1000000; i++) {
            value++;
        }
        semaphore.release();
    }
    public int getSum() {
        return value;
    }
}
