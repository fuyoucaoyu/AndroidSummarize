package com.justnow.androidsummarize.event;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.justnow.androidsummarize.R;

/**
 * ViewGroup:
 *    如果顶级的ViewGroup拦截事件即onInterceptTouchEvent返回true的话，则事件会交给ViewGroup处理
 *          如果ViewGroup的onTouchListener被设置的话，则onTouch将会被调用，否则的话onTouchEvent将会被调用，
 *          也就是说：两者都设置的话，onTouch将会屏蔽掉onTouchEvent，在onTouchEvent中，如果设置了onClickerListener的话，那么onClick将会被调用。
 *
 *    如果顶级ViewGroup不拦截的话，那么事件将会被传递给它所在的点击事件的子view，这时候子view的dispatchTouchEvent将会被调用
 *          如果子view的onTouch返回true，那么父ViewGroup的onTouch则不执行，子View自己的onClick也不会执行
 *          如果子view的onClick执行，那么父ViewGroup的onTouch则不执行
 *          如果子view的onTouch返回false且无onClick，则父ViewGroup的onTouch和onClick才执行
 *
 * View 事件分发：
 *    dispatchTouchEvent -> onTouch(setOnTouchListener) -> onTouchEvent -> onClick(setOnClickListener)
 *
 * onTouch和onTouchEvent的区别
 *     两者都是在dispatchTouchEvent中调用的，onTouch优先于onTouchEvent，如果onTouch返回true，那么onTouchEvent则不执行，及onClick也不执行
 *
 *
 *
 */
public class EventDispatchTestActivity extends AppCompatActivity {

    private static final String TAG = "EventDispatchTest";

    private ViewGroup mParent;
    private ViewGroup mSubParent;
    private TextView mTouchBtn;
    private Button mClickBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_dispatch_test);

        initView();
    }

    private void initView() {
        mParent = findViewById(R.id.event_container_top);
        mSubParent = findViewById(R.id.event_container_second);
        mTouchBtn = findViewById(R.id.touch_event_btn);
        mClickBtn = findViewById(R.id.click_event_btn);

        initEvent();
    }

    private void initEvent() {
        mParent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "Parent onTouch");
                return true;
            }
        });

        mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Parent onClick");
            }
        });

        mSubParent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "Sub Parent onTouch");
                return false;
            }
        });

        mSubParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Sub Parent onClick");
            }
        });

        mTouchBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "TouchBtn onTouch");
                return false;
            }
        });

//        mTouchBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "TouchBtn onClick");
//            }
//        });
//
        mClickBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "ClickBtn onTouch");
                return true;
            }
        });

        mClickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "ClickBtn onClick");
            }
        });
    }


}