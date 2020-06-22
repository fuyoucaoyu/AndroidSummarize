package com.justnow.androidsummarize.fragment.navigation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.justnow.androidsummarize.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NavigationSecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavigationSecondFragment extends BaseNavigationFragment {

    private Button mGotoFirstBtn;
    private Button mGotoThirdBtn;

    public NavigationSecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NavigationSecondFragment.
     */
    public static NavigationSecondFragment newInstance(String param1, String param2) {
        NavigationSecondFragment fragment = new NavigationSecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public String getType() {
        return "SecondFragment ";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, getType() + " onCreateView");
        // Inflate the layout for this fragment
        mContainerView = inflater.inflate(R.layout.fragment_navigation_second, container, false);
        initView();
        return mContainerView;
    }
    private void initView() {
        mGotoFirstBtn = mContainerView.findViewById(R.id.nav_second_to_first);
        mGotoThirdBtn = mContainerView.findViewById(R.id.nav_second_to_third);

        mGotoFirstBtn.setOnClickListener(this);
        mGotoThirdBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nav_second_to_first:
                Navigation.findNavController(v).navigate(R.id.action_navigationSecondFragment_to_navigationFirstFragment);
                break;
            case R.id.nav_second_to_third:
                Navigation.findNavController(v).navigate(R.id.action_navigationSecondFragment_to_navigationThirdFragment);
                break;
            default:
                break;
        }
    }
}