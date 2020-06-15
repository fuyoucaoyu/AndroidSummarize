package com.justnow.androidsummarize.fragment.axdefault;

import android.os.Bundle;

public class AxFragmentManager implements IAxFragmentManager {

    public AxFragmentManager() {

    }

    public static AxFragmentManager newInstance(IAxFragmentFactory fragmentFactory) {
        AxFragmentManager manager = new AxFragmentManager();
        return manager;
    }

    public boolean showFragment(int type, Bundle bundle) {

        return false;
    }

    public boolean back(Bundle bundle) {
        return false;
    }
}
