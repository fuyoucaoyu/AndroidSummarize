package com.justnow.androidsummarize.fragment.axdefault;

import com.justnow.androidsummarize.fragment.axdefault.fragments.AxFirstFragment;
import com.justnow.androidsummarize.fragment.axdefault.fragments.AxFourthFragment;
import com.justnow.androidsummarize.fragment.axdefault.fragments.AxSecondFragment;
import com.justnow.androidsummarize.fragment.axdefault.fragments.AxThirdFragment;
import com.justnow.androidsummarize.fragment.axdefault.fragments.BaseAxFragment;

public class AxFragmentFactory implements IAxFragmentFactory {

    public static final int FRAGMENT_FIRST = 0;
    public static final int FRAGMENT_SECOND = 1;
    public static final int FRAGMENT_THIRD = 2;
    public static final int FRAGMENT_FOURTH = 3;

    public static AxFragmentFactory newInstance() {
        AxFragmentFactory result = new AxFragmentFactory();
        return result;
    }

    public BaseAxFragment createFragment(int type, String param1, String param2) {
        BaseAxFragment result;
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
            default:
                result = AxFourthFragment.newInstance(param1, param2);
                break;
        }

        return result;
    }
}
