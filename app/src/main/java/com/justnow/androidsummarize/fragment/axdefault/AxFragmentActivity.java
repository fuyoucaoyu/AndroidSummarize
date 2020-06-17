package com.justnow.androidsummarize.fragment.axdefault;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.justnow.androidsummarize.R;

public class AxFragmentActivity extends AppCompatActivity {

    private static final String TAG = "AxFragmentActivity";

    private FrameLayout mFragmentContainer;
    private AxFragmentManager mAxFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ax_fragment);

        init();
    }

    private void init() {
        mFragmentContainer = findViewById(R.id.fragment_container);

        mAxFragmentManager = AxFragmentManager.newInstance(AxFragmentFactory.newInstance(),
                this, R.id.fragment_container);

        Bundle bundle = new Bundle();
        bundle.putString(AxFragmentManager.KEY_PARAM_1, "first_param_1");
        bundle.putString(AxFragmentManager.KEY_PARAM_2, "first_param_2");
        mAxFragmentManager.showFragment(AxFragmentFactory.FRAGMENT_FIRST, bundle);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Log.d(TAG, "onBackPressed " + mAxFragmentManager.getStackCount());
    }
}