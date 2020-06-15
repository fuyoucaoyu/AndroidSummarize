package com.justnow.androidsummarize.fragment.axdefault;

import android.os.Bundle;

public interface IAxFragmentManager {
    boolean showFragment(int type, Bundle bundle);
    boolean back(Bundle bundle);
}
