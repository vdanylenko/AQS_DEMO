package net.danylenko.one_shot_latch;

public interface OneShotLatch {
    void await() throws InterruptedException;

    void awaitUninterruptibly();

    void release();
}
