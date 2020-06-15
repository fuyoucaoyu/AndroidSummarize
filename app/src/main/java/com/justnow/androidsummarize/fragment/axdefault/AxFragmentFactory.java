package com.justnow.androidsummarize.fragment.axdefault;

import com.justnow.androidsummarize.fragment.axdefault.fragments.AxFirstFragment;
import com.justnow.androidsummarize.fragment.axdefault.fragments.AxFourthFragment;
import com.justnow.androidsummarize.fragment.axdefault.fragments.AxSecondFragment;
import com.justnow.androidsummarize.fragment.axdefault.fragments.AxThirdFragment;

import androidx.fragment.app.Fragment;

public class AxFragmentFactory implements IAxFragmentFactory {

    private static final int FRAGMENT_FIRST = 0;
    private static final int FRAGMENT_SECOND = 1;
    private static final int FRAGMENT_THIRD = 2;
    private static final int FRAGMENT_FOURTH = 3;

    public Fragment createFragment(int type, String param1, String param2) {
        Fragment result;
        switch (type) {
            case FRAGMENT_FIRST:
                result = AxFirstFragment.newInstance(param1, param2);
                break;
            case FRAGMENT_SECOND:
                result = AxSecondFragment.newInstance(param1, param2);
                break;
            case FRAGMENT_THIRD:
                result = AxThirdFragment.newInstance(param1, param2);
                break;
            case FRAGMENT_FOURTH:
                result = AxFourthFragment.newInstance(param1, param2);
                break;
            default:
                result = AxFourthFragment.newInstance(param1, param2);
                break;
        }

        return result;
    }
}
