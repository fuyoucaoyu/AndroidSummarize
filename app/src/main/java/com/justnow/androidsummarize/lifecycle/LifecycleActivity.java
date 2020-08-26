package com.justnow.androidsummarize.lifecycle;

import androidx.annotation.Nullable;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import com.justnow.androidsummarize.R;

public class LifecycleActivity extends Activity {

    private static final String TAG = "LifecycleActivity";

    private static final String KEY_1 = "key1";
    private static final String KEY_2 = "key2";

    // 生命周期 onCreate

    /**
     * onSaveInstanceState(Bundle outState);
     * @param savedInstanceState 先前关闭，并重新初始化时，此 bundle 包含了之前在 onSaveInstanceState 保留的数据
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        Log.d(TAG, "onCreate");

        if (null != savedInstanceState) {
            Log.d(TAG, "onCreate: " + savedInstanceState.getString(KEY_1));
        }

        // URI Schema
        Intent intent = getIntent();
        Uri data = intent.getData();
        if (null != data) {
            Log.d(TAG, data.getHost() + ":" + data.getPort() + "/" + data.getPath()
                    + "?" + data.getQuery());
            String param = data.getQueryParameter("urikey");
            Log.d(TAG, "urikey:" + param);
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
        setContentView(R.layout.activity_lifecycle);

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