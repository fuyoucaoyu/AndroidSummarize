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

    private static final String TAG = "AxThirdFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Log.d(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
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

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(TAG, "onLowMemory");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }
}