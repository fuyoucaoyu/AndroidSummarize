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
 * Use the {@link NavigationThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavigationThirdFragment extends BaseNavigationFragment {

    private Button mGotoFirstBtn;
    private Button mGotoSecondBtn;

    public NavigationThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NavigationThirdFragment.
     */
    public static NavigationThirdFragment newInstance(String param1, String param2) {
        NavigationThirdFragment fragment = new NavigationThirdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public String getType() {
        return "ThirdFragment ";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, getType() + " onCreateView");
        // Inflate the layout for this fragment
        mContainerView = inflater.inflate(R.layout.fragment_navigation_third, container, false);
        initView();
        return mContainerView;
    }
    private void initView() {
        mGotoFirstBtn = mContainerView.findViewById(R.id.nav_third_to_first);
        mGotoSecondBtn = mContainerView.findViewById(R.id.nav_third_to_second);

        mGotoSecondBtn.setOnClickListener(this);
        mGotoFirstBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nav_third_to_first:
                Navigation.findNavController(v).navigate(R.id.action_navigationThirdFragment_to_navigationFirstFragment);
                break;
            case R.id.nav_third_to_second:
                Navigation.findNavController(v).navigate(R.id.action_navigationThirdFragment_to_navigationSecondFragment);
                break;
            default:
                break;
        }
    }
}