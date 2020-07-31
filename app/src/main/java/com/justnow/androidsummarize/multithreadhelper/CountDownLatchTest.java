package com.justnow.androidsummarize.multithreadhelper;

import android.util.Log;

import java.util.concurrent.CountDownLatch;

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
