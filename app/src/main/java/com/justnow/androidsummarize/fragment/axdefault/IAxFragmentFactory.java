package com.justnow.androidsummarize.fragment.axdefault;

import androidx.fragment.app.Fragment;

public interface IAxFragmentFactory {
    Fragment createFragment(int type, String param1, String param2);
}
