package com.justnow.androidsummarize.multithreadhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.justnow.androidsummarize.R;

/**
 * 1、归总
 * Semaphore属于有上限的线程容器，当容器满的时候，不会再往容器中增加新的线程。比如：打印机
 * CyclicBarrier属于等待线程容器，只有当容器的线程数满了后，才会执行所有线程，主线程不用等待（子线程需要等待）
 * CountDownLatch属于等待线程容器，只有当容器的线程数满了后，才会执行所有线程，主线程需要等待（子线程不需要等待）
 *
 * 2、区别
 * Semaphore和CyclicBarrier和CountDownLatch区别在于：
 *      Semaphore 容器里的线程从加入的时刻就会开始执行
 *      CyclicBarrier 和 CountDownLatch容器里的线程只有容器满了后才统一执行
 * CyclicBarrier和CountDownLatch区别在于：
 *      CyclicBarrier 主线程不用等待，CountDownLatch 主线程需要等待
 *      CyclicBarrier 的计数器可以被重置后使用，CountDownLatch的计数器无法被重置
 */
public class MultiThreadHelperActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_thread_helper);

        initViews();
    }

    private void initViews() {

        Button semaphoreBtn = findViewById(R.id.semaphore_test);
        semaphoreBtn.setOnClickListener(this);
        Button cyclicBarrierBtn = findViewById(R.id.cyclic_barrier_test);
        cyclicBarrierBtn.setOnClickListener(this);
        Button countDownLatchBtn = findViewById(R.id.count_down_latch_test);
        countDownLatchBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.semaphore_test:
                new SemaphoreTest();
                break;
            case R.id.cyclic_barrier_test:
                new CyclicBarrierTest();
                break;
            case R.id.count_down_latch_test:
                new CountDownLatchTest();
                break;
        }
    }
}