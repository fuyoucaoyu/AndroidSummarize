package com.justnow.androidsummarize.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.justnow.androidsummarize.R;
import com.justnow.androidsummarize.fragment.axdefault.AxFragmentActivity;

import androidx.appcompat.app.AppCompatActivity;

public class FragmentPageActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mAxFragmentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        init();
    }

    private void init() {
        mAxFragmentBtn = findViewById(R.id.ax_fragment_btn);
        mAxFragmentBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ax_fragment_btn:
                Intent intent = new Intent(FragmentPageActivity.this, AxFragmentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }
}