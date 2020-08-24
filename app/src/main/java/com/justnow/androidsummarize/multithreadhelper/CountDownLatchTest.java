package com.justnow.androidsummarize.multithreadhelper;

import android.util.Log;

import java.util.concurrent.CountDownLatch;

/**
 *  是 java.util.concurrent 包下的一个同步辅助类，
 *  它能使一个或多个线程在其他的线程的一系列操作完成之前一直等待
 *
 *  await() 方法是线程阻塞，直到计数器为0，才会启动；
 *  countDown() 方法使计数器减1。
 *
 *  如果当前线程设置了 interrupted 状态，或者在调用 await() 方法之后等待的过程中，被其他线程打断（如调用 interrupt() 方法），
 *  会抛出 InterruptedException 异常，因此最好捕捉相应的异常。
 */
public class CountDownLatchTest {

    private final static String TAG = "CountDownLatchTest";

    private CountDownLatch mCountDownLatch;

    public CountDownLatchTest() {
        init();
    }

    private void init() {
        mCountDownLatch = new CountDownLatch(4);

        for (int i = 0; i < 4; i++) {
            new TestThread().start();
        }

        try {
            Log.d(TAG, "Main wait");
            mCountDownLatch.await();
            Log.d(TAG, "Main Go on");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class TestThread extends Thread {
        @Override
        public void run() {
            Log.d(TAG, Thread.currentThread().getName() + "START");
            mCountDownLatch.countDown();
            Log.d(TAG, Thread.currentThread().getName() + "DONE");
        }
    }
}
