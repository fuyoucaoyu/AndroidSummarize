package com.justnow.androidsummarize.fragment.axdefault.fragments;

import com.justnow.androidsummarize.fragment.axdefault.IAxFragmentManager;

import androidx.fragment.app.Fragment;

public class BaseAxFragment extends Fragment {

    protected IAxFragmentManager mAxFragmentManager;
    public void setFragmentManager(IAxFragmentManager manager) {
        mAxFragmentManager = manager;
    }
}
