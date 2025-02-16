package net.danylenko.one_shot_latch;

import java.io.Serial;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class OneShotLatchAQS implements OneShotLatch {

    private Sync sync = new Sync();

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(0);
    }

    public void awaitUninterruptibly() {
        sync.acquireShared(0);
    }

    public void release() {
        sync.releaseShared(0);
    }

    private static class Sync extends AbstractQueuedSynchronizer {

        @Serial
        private static final long serialVersionUID = 1192457210091910933L;

        @Override
        protected int tryAcquireShared(int arg) {
            return (getState() == 1) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }
}
