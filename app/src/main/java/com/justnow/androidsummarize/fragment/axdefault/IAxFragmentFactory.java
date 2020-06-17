package com.justnow.androidsummarize.fragment.axdefault;

import com.justnow.androidsummarize.fragment.axdefault.fragments.BaseAxFragment;

public interface IAxFragmentFactory {
    BaseAxFragment createFragment(int type, String param1, String param2);
}
