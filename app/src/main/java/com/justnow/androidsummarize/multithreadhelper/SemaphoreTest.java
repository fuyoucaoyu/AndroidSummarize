package com.justnow.androidsummarize.multithreadhelper;

import android.util.Log;

import java.util.concurrent.Semaphore;

/**
 * Semaphore是一个计数信号量。
 * 信号量中维护着一个信号量许可集，线程可以通过调用acquire()来获取信号量的许可。
 * 当信号量被许可时，线程可以向下执行，否则线程等待。
 * 同时，线程也可以通过release()来释放它的信号量。
 *
 * 可以用来做：
 *      1. 并发数控制
 *      2. 多线程顺序执行的控制
 *      3. 依赖控制
 *
 */
public class SemaphoreTest {
    private final static String TAG = "SemaphoreTest";

    private Semaphore mSemaphore;
    private Semaphore mSequenceSemaphore;
    private Semaphore mOrderSemaphore;

    public SemaphoreTest() {
        init();
    }

    private void init() {
        mSemaphore = new Semaphore(3);

        // 并发数控制
        for (int i = 0; i < 10; i++) {
            new ConcurrencyThread().start();
        }

        // 串行控制
        mSequenceSemaphore = new Semaphore(1);
        for (int j = 0; j < 5; j++) {
            new SequenceThread().start();
        }

        // 顺序控制
        mOrderSemaphore = new Semaphore(0);
        new Order1Thread().start();
        new Order2Thread().start();
    }


    private class ConcurrencyThread extends Thread {
        @Override
        public void run() {
            try {
                mSemaphore.acquire();
                Log.d(TAG, "ConcurrencyThread: " + Thread.currentThread().getName() + ": start");
                Thread.sleep(2000);
                mSemaphore.release();
                Log.d(TAG, "ConcurrencyThread: " + Thread.currentThread().getName() + ": end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class SequenceThread extends Thread {
        @Override
        public void run() {
            try {
                mSequenceSemaphore.acquire();
                Log.d(TAG, "SequenceThread: " + Thread.currentThread().getName() + ": start");
                Thread.sleep(2000);
                mSequenceSemaphore.release();
                Log.d(TAG, "SequenceThread: " + Thread.currentThread().getName() + ": end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class Order1Thread extends Thread {
        @Override
        public void run() {
            try {
                Log.d(TAG, "Order1Thread: " + Thread.currentThread().getName() + ": start");
                Thread.sleep(2000);
                mOrderSemaphore.release();
                Log.d(TAG, "Order1Thread: " + Thread.currentThread().getName() + ": end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class Order2Thread extends Thread {
        @Override
        public void run() {
            try {
                mOrderSemaphore.acquire();
                Log.d(TAG, "Order2Thread: " + Thread.currentThread().getName() + ": start");
                Thread.sleep(2000);
                mOrderSemaphore.release();
                Log.d(TAG, "Order2Thread: " + Thread.currentThread().getName() + ": end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
