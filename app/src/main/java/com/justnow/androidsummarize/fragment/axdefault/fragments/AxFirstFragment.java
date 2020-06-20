package com.justnow.androidsummarize.fragment.axdefault.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.justnow.androidsummarize.R;
import com.justnow.androidsummarize.fragment.axdefault.AxFragmentFactory;
import com.justnow.androidsummarize.fragment.axdefault.AxFragmentManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;

/**
 * A simple {@link BaseAxFragment} subclass.
 * Use the {@link AxFirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AxFirstFragment extends BaseAxFragment {

    private EditText mEditText1;
    private EditText mEditText2;
    private Button mGotoSecondBtn;
    private Button mGotoThirdBtn;
    private Button mGotoFourthBtn;

    public AxFirstFragment() {
        super();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AxFirstFragment.
     */
    public static AxFirstFragment newInstance(String param1, String param2) {
        AxFirstFragment fragment = new AxFirstFragment();
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
        Log.d(TAG, "0 onCreateView");
        // Inflate the layout for this fragment
        mContainerView = inflater.inflate(R.layout.fragment_ax_first, container, false);
        initView();
        return mContainerView;
    }

    private void initView() {
        mGotoSecondBtn = mContainerView.findViewById(R.id.ax_first_to_second);
        mGotoThirdBtn = mContainerView.findViewById(R.id.ax_first_to_third);
        mGotoFourthBtn = mContainerView.findViewById(R.id.ax_first_to_fourth);
        mEditText1 = mContainerView.findViewById(R.id.ax_first_editor_1);
        mEditText2 = mContainerView.findViewById(R.id.ax_first_editor_2);

        mGotoSecondBtn.setOnClickListener(this);
        mGotoThirdBtn.setOnClickListener(this);
        mGotoFourthBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString(AxFragmentManager.KEY_PARAM_1, "param1_from_fragment_1");
        bundle.putString(AxFragmentManager.KEY_PARAM_2, "param2_from_fragment_1");
        switch (v.getId()) {
            case R.id.ax_first_to_second:
                mAxFragmentManager.showFragment(AxFragmentFactory.FRAGMENT_SECOND, bundle);
                break;
            case R.id.ax_first_to_third:
                mAxFragmentManager.showFragment(AxFragmentFactory.FRAGMENT_THIRD, bundle);
                break;
            case R.id.ax_first_to_fourth:
                mAxFragmentManager.showFragment(AxFragmentFactory.FRAGMENT_FOURTH, bundle);
                break;
        }
    }
}