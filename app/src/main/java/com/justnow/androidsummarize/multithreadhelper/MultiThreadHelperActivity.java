package com.justnow.androidsummarize.multithreadhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.justnow.androidsummarize.R;

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