package net.danylenko;

import net.danylenko.one_shot_latch.OneShotLatch;
import net.danylenko.one_shot_latch.OneShotLatchAQS;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        OneShotLatch latch = new OneShotLatchAQS();
        Thread thread = new Thread(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
            latch.awaitUninterruptibly();
            System.out.println("Rleased 1");
        });
        thread.start();
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
            latch.awaitUninterruptibly();
            System.out.println("Rleased 2");
        });
        thread1.start();

        Thread.sleep(10);

        System.out.println("Starting");
        latch.release();

    }
}