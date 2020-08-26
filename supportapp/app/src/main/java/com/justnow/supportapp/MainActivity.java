package com.justnow.supportapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private static final String KEY_1 = "key1";
    private static final String KEY_2 = "key2";

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        initView();
//    }

    private void initView() {
        Button schemeBtn = findViewById(R.id.url_scheme_btn);
        schemeBtn.setOnClickListener(this);

        Button intentStartBtn = findViewById(R.id.intent_start);
        intentStartBtn.setOnClickListener(this);

        Button okHttpBtn = findViewById(R.id.okhttp_btn);
        okHttpBtn.setOnClickListener(this);

        Button multiThreadHelperBtn = findViewById(R.id.multi_threading_helper_btn);
        multiThreadHelperBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.url_scheme_btn:
                gotoSchemeActivityBtn();
                break;
            case R.id.intent_start:
                gotoActivityByIntent();
                break;
            case R.id.okhttp_btn:
                break;
            case R.id.multi_threading_helper_btn:
                break;
            default:
                break;
        }
    }

    private void gotoSchemeActivityBtn() {
        Uri data = Uri.parse("urlscheme://urlhost:8888/urlpath?urikey=ruivalue");
        Intent intent = new Intent(Intent.ACTION_VIEW, data);
        //保证新启动的APP有单独的堆栈，如果希望新启动的APP和原有APP使用同一个堆栈则去掉该项
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
            startActivity(intent);
//            startActivityForResult(intent, RESULT_OK);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "没有匹配的APP，请下载安装",Toast.LENGTH_SHORT).show();
        }
    }

    private void gotoActivityByIntent() {
        Intent intent = new Intent();
        intent.setAction("com.justnow.lifecycleaction");
        intent.addCategory("android.intent.category.DEFAULT");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 返回键不应该退出程序---而是返回桌面复写onKeyDown事件
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // 生命周期 onCreate

    /**
     * onSaveInstanceState(Bundle outState);
     * @param savedInstanceState 先前关闭，并重新初始化时，此 bundle 包含了之前在 onSaveInstanceState 保留的数据
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");

        initView();

        if (null != savedInstanceState) {
            Log.d(TAG, "onCreate: " + savedInstanceState.getString(KEY_1));
        }
    }

    /**
     * onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState);
     * @param savedInstanceState 先前关闭，并重新初始化时，此 bundle 包含了之前在 onSaveInstanceState 保留的数据
     * @param persistentState 简单对象的集合，可以安全的保存到磁盘并从磁盘还原
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);

        initView();

        if (null != savedInstanceState) {
            Log.d(TAG, "onCreate: " + savedInstanceState.getString(KEY_1));
        }

        if (Build.VERSION.SDK_INT >= 21) {
            if (null != persistentState) {
                Log.d(TAG, "onCreate: " + persistentState.getString(KEY_1));
            }
        } else {
            Log.d(TAG, "onCreate: sdk version < 21");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");

        if (null != savedInstanceState) {
            Log.d(TAG, "onCreate: " + savedInstanceState.getString(KEY_1));
        }
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        Log.d(TAG, "onRestoreInstanceState");

        if (null != savedInstanceState) {
            Log.d(TAG, "onCreate: " + savedInstanceState.getString(KEY_1));
        }
    }

    @Override
    protected void onSaveInstanceState(@androidx.annotation.NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "onSaveInstanceState without PersistableBundle");
        outState.putString(KEY_1, "bundle_key_1");
        outState.putString(KEY_2, "bundle_key_2");
    }

    @Override
    public void onSaveInstanceState(@androidx.annotation.NonNull Bundle outState,
                                    @androidx.annotation.NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        Log.d(TAG, "onSaveInstanceState with PersistableBundle");

        outState.putString(KEY_1, "bundle_key_1");
        outState.putString(KEY_2, "bundle_key_2");

        if (Build.VERSION.SDK_INT >= 21) {
            outPersistentState.putString(KEY_1, "bundle_key_1");
            outPersistentState.putString(KEY_2, "bundle_key_2");
        } else {
            Log.d(TAG, "sdk version < 21");
        }
    }
}
