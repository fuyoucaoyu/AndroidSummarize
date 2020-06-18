package com.justnow.androidsummarize.fragment.axdefault;

import android.os.Bundle;

import com.justnow.androidsummarize.fragment.axdefault.fragments.BaseAxFragment;

import androidx.annotation.IdRes;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * 通过 addToBackStack 添加到回退栈方式：
 * 1. Fragment 页面编辑的文本等，回退时仍然存在
 * 2. AppCompatActivity 的 onBackPressed 已经支持了 popBackStackImmediate
 * 3. 切换Fragment：
 *          Second: onAttach > onCreate >>
 *          First: onPause > onStop > onDestroyView >>
 *          Second: onCreateView > onStart > onResume
 * 4. Back 返回栈内前一个：
 *          Second: onPause > onStop > onDestroyView > onDestroy
 *          First: onCreateView > onStart > onResume
 * 5. addToBackStack 添加到回退栈的方式无法
 */
public class AxFragmentManager implements IAxFragmentManager {

    public static final String KEY_PARAM_1 = "param1";
    public static final String KEY_PARAM_2 = "param2";

    private IAxFragmentFactory mFragmentFactory;
    private FragmentActivity mFragmentActivity;
    private FragmentManager mFragmentManager;
    private BaseAxFragment mCurrentFragment;
    @IdRes
    private int mContainerId;

    public AxFragmentManager() {
    }

    public static AxFragmentManager newInstance(IAxFragmentFactory fragmentFactory,
                                                FragmentActivity activity,
                                                @IdRes int containerId) {
        AxFragmentManager manager = new AxFragmentManager();
        manager.init(fragmentFactory, activity, containerId);
        return manager;
    }

    private void init(IAxFragmentFactory fragmentFactory,
                      FragmentActivity activity,
                      @IdRes int containerId) {
        mFragmentFactory = fragmentFactory;
        mFragmentActivity = activity;
        mContainerId = containerId;
        mFragmentManager = mFragmentActivity.getSupportFragmentManager();
        mCurrentFragment = null;
    }

    public boolean showFragment(int type, Bundle bundle) {
        BaseAxFragment target = mFragmentFactory.createFragment(type,
                bundle.getString(KEY_PARAM_1, "default_1"),
                bundle.getString(KEY_PARAM_2, "default_2"));
        target.setFragmentManager(this);

        replace(type, target, false);
        return false;
    }

    public boolean back(Bundle bundle) {
        return false;
    }

    public int getStackCount() {
        return mFragmentManager.getBackStackEntryCount();
    }

    private boolean replace(int type, BaseAxFragment target, boolean isBack) {
        if (!isBack) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.addToBackStack(String.valueOf(type));
            transaction.replace(mContainerId, target);
            transaction.commit();
        } else {
            mFragmentManager.popBackStack();
            mFragmentManager.popBackStack();
        }

        return false;
    }
}
