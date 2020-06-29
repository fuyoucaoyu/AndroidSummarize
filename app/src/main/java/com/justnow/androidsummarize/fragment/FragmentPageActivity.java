package com.justnow.androidsummarize.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.justnow.androidsummarize.R;
import com.justnow.androidsummarize.fragment.axdefault.AxFragmentActivity;
import com.justnow.androidsummarize.fragment.navigation.NavigationFragmentActivity;

/**
 * 多 Activity 架构：Activity 即页面
 *       优势：系统内存告急时可以回收处于后台的 Activity，保证更多的资源给前台的页面和任务
 *       劣势：启动新 Activity 是在 AMS 中运行（非 UI 主线程），它需要执行一系列耗时的操作，我们能明显地意识到「页面切换」这个动作
 *
 * 单 Activity 多 Fragment 架构：Fragment 即页面，适合页面层级结构不深的场景
 *       优势：消耗更少的资源，能更快地响应页面间切换和交互
 *       劣势：层次深的页面进行现场保存和还原会消耗更多的资源和时间
 *
 */
import androidx.appcompat.app.AppCompatActivity;

public class FragmentPageActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mAxFragmentBtn;
    private Button mNavFragmentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        init();
    }

    private void init() {
        mAxFragmentBtn = findViewById(R.id.ax_fragment_btn);
        mAxFragmentBtn.setOnClickListener(this);

        mNavFragmentBtn = findViewById(R.id.navigation_fragment_btn);
        mNavFragmentBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.ax_fragment_btn:
                intent = new Intent(FragmentPageActivity.this, AxFragmentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.navigation_fragment_btn:
                intent = new Intent(FragmentPageActivity.this, NavigationFragmentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }
}