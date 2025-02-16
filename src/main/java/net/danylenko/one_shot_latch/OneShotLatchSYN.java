package net.danylenko.one_shot_latch;

public class OneShotLatchSYN implements OneShotLatch {

    private volatile boolean signaled;

    public synchronized void awaitUninterruptibly() {
        boolean interrupted = Thread.currentThread().isInterrupted();
        while (!signaled) {
            try {
                wait();
            } catch (InterruptedException e) {
                interrupted = true;
            }
        }
        if (interrupted) {
            Thread.currentThread().interrupt();
        }
    }


    public synchronized void await() throws InterruptedException {
        if( Thread.interrupted() ) throw new InterruptedException();
        while (!signaled) {
               wait();
        }

    }

    public synchronized void release() {
        if (!signaled) {
            signaled = true;
            notifyAll();
        }
    }
}
