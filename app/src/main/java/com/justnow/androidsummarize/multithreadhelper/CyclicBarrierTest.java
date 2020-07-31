package com.justnow.androidsummarize.multithreadhelper;

import android.util.Log;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier是一个同步辅助类，可以让正在运行中的线程与其他线程在某一公共时刻进行同步。
 *
 * 可以用来做：
 *      1. 所有线程等待相同的时间开始同步运行
 */
public class CyclicBarrierTest {

    private final static String TAG = "CyclicBarrierTest";

    private CyclicBarrier mCyclicBarrier;

    public CyclicBarrierTest() {
        init();
    }

    private void init() {
        // 正在运行中的线程与其他线程在某一公共时刻进行同步
        mCyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "All threads ready");
            }
        });

        for (int i = 0; i < 3; i++) {
            new WaitThread().start();
        }
    }

    private class WaitThread extends Thread {
        @Override
        public void run() {
            try {
                Log.d(TAG, "WaitThread: " + Thread.currentThread().getName() + ": ready ");
                Thread.sleep(2000);
                mCyclicBarrier.await();
                Log.d(TAG, "WaitThread: " + Thread.currentThread().getName() + ": go ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
