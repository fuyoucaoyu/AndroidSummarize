package com.justnow.androidsummarize;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.justnow.androidsummarize.event.EventDispatchTestActivity;
import com.justnow.androidsummarize.fragment.FragmentPageActivity;
import com.justnow.androidsummarize.okhttp.OkHttpActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        Button fragmentBtn = findViewById(R.id.fragment_btn);
        fragmentBtn.setOnClickListener(this);

        Button eventBtn = findViewById(R.id.event_btn);
        eventBtn.setOnClickListener(this);

        Button okHttpBtn = findViewById(R.id.okhttp_btn);
        okHttpBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_btn:
                gotoFragmentPage();
                break;
            case R.id.event_btn:
                gotoEventTestPage();
                break;
            case R.id.okhttp_btn:
                gotoOkHttpPage();
                break;
            default:
                break;
        }
    }

    private void gotoFragmentPage() {
        Intent intent = new Intent(this, FragmentPageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void gotoEventTestPage() {
        Intent intent = new Intent(this, EventDispatchTestActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void gotoOkHttpPage() {
        Intent intent = new Intent(this, OkHttpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}