package com.justnow.androidsummarize.fragment.axdefault.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justnow.androidsummarize.fragment.axdefault.IAxFragmentManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BaseAxFragment extends Fragment implements View.OnClickListener {

    public static final String KEY_PARAM_1 = "param1";
    public static final String KEY_PARAM_2 = "param2";

    protected IAxFragmentManager mAxFragmentManager;

    protected View mContainerView;

    public void setFragmentManager(IAxFragmentManager manager) {
        mAxFragmentManager = manager;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return mContainerView;
    }

    @Override
    public void onClick(View v) {

    }
}
