package com.justnow.androidsummarize.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.justnow.androidsummarize.R;
import com.justnow.androidsummarize.fragment.axdefault.AxFragmentActivity;
import com.justnow.androidsummarize.fragment.navigation.NavigationFragmentActivity;

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