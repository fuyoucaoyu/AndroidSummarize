package com.justnow.androidsummarize.fragment.axdefault.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.justnow.androidsummarize.R;
import com.justnow.androidsummarize.fragment.axdefault.AxFragmentFactory;
import com.justnow.androidsummarize.fragment.axdefault.AxFragmentManager;

/**
 * A simple {@link BaseAxFragment} subclass.
 * Use the {@link AxThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AxThirdFragment extends BaseAxFragment {

    private Button mGotoFirstBtn;
    private Button mGotoSecondBtn;
    private Button mGotoFourthBtn;
    private EditText mEditText1;
    private EditText mEditText2;

    public AxThirdFragment() {
        super();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AxThirdFragment.
     */
    public static AxThirdFragment newInstance(String param1, String param2) {
        AxThirdFragment fragment = new AxThirdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "2 onCreateView");
        // Inflate the layout for this fragment
        mContainerView = inflater.inflate(R.layout.fragment_ax_third, container, false);
        initView();
        return mContainerView;
    }

    private void initView() {
        mGotoFirstBtn = mContainerView.findViewById(R.id.ax_third_to_first);
        mGotoSecondBtn = mContainerView.findViewById(R.id.ax_third_to_second);
        mGotoFourthBtn = mContainerView.findViewById(R.id.ax_third_to_fourth);
        mEditText1 = mContainerView.findViewById(R.id.ax_third_editor_1);
        mEditText2 = mContainerView.findViewById(R.id.ax_third_editor_2);

        mGotoSecondBtn.setOnClickListener(this);
        mGotoFirstBtn.setOnClickListener(this);
        mGotoFourthBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString(AxFragmentManager.KEY_PARAM_1, "param1_from_fragment_3");
        bundle.putString(AxFragmentManager.KEY_PARAM_2, "param2_from_fragment_3");
        switch (v.getId()) {
            case R.id.ax_third_to_first:
                mAxFragmentManager.showFragment(AxFragmentFactory.FRAGMENT_FIRST, bundle);
                break;
            case R.id.ax_third_to_second:
                mAxFragmentManager.showFragment(AxFragmentFactory.FRAGMENT_SECOND, bundle);
                break;
            case R.id.ax_third_to_fourth:
                mAxFragmentManager.showFragment(AxFragmentFactory.FRAGMENT_FOURTH, bundle);
                break;
        }
    }
}