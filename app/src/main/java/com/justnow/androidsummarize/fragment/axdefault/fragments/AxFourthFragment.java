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
 * Use the {@link AxFourthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AxFourthFragment extends BaseAxFragment {

    private Button mGotoFirstBtn;
    private Button mGotoSecondBtn;
    private Button mGotoThirdBtn;
    private EditText mEditText1;
    private EditText mEditText2;

    public AxFourthFragment() {
        super();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AxFourthFragment.
     */
    public static AxFourthFragment newInstance(String param1, String param2) {
        AxFourthFragment fragment = new AxFourthFragment();
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
        Log.d(TAG, "3 onCreateView");
        // Inflate the layout for this fragment
        mContainerView = inflater.inflate(R.layout.fragment_ax_fourth, container, false);
        initView();
        return mContainerView;
    }

    private void initView() {
        mGotoFirstBtn = mContainerView.findViewById(R.id.ax_fourth_to_first);
        mGotoSecondBtn = mContainerView.findViewById(R.id.ax_fourth_to_second);
        mGotoThirdBtn = mContainerView.findViewById(R.id.ax_fourth_to_third);
        mEditText1 = mContainerView.findViewById(R.id.ax_fourth_editor_1);
        mEditText2 = mContainerView.findViewById(R.id.ax_fourth_editor_2);

        mGotoFirstBtn.setOnClickListener(this);
        mGotoSecondBtn.setOnClickListener(this);
        mGotoThirdBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString(AxFragmentManager.KEY_PARAM_1, "param1_from_fragment_4");
        bundle.putString(AxFragmentManager.KEY_PARAM_2, "param2_from_fragment_4");
        switch (v.getId()) {
            case R.id.ax_fourth_to_first:
                mAxFragmentManager.showFragment(AxFragmentFactory.FRAGMENT_FIRST, bundle);
                break;
            case R.id.ax_fourth_to_second:
                mAxFragmentManager.showFragment(AxFragmentFactory.FRAGMENT_SECOND, bundle);
                break;
            case R.id.ax_fourth_to_third:
                mAxFragmentManager.showFragment(AxFragmentFactory.FRAGMENT_THIRD, bundle);
                break;
        }
    }
}