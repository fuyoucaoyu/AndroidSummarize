package com.justnow.androidsummarize.fragment.axdefault;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;

import com.justnow.androidsummarize.fragment.axdefault.fragments.BaseAxFragment;

import java.util.List;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * 通过 addToBackStack 添加到回退栈方式：
 * 1. Fragment 页面编辑的文本等，回退时仍然存在
 * 2. AppCompatActivity 的 onBackPressed 已经支持了 popBackStackImmediate
 * 3. Replace 切换生 Fragment： 会销毁视图，处理有内存回收需要的情况，比如有大量图片的Fragment
 *      切换Fragment：
 *          Second: onAttach > onCreate >>
 *          First: onPause > onStop > onDestroyView >>
 *          Second: onCreateView > onStart > onResume
 *      Back 返回栈内前一个：
 *          Second: onPause > onStop > onDestroyView > onDestroy
 *          First: onCreateView > onStart > onResume
 * 4. Add + Show/hide 切换 Fragment: setVisibility 显示/隐藏，处理高的概率会复用的情况
 *      切换Fragment：
 *          Second: onAttach > onCreate >>
 *          First: onHiddenChanged >>
 *          Second: onCreateView > onStart > onResume
 *      Back 返回栈内前一个：
 *          Second: onHiddenChanged > onPause > onStop > onDestroyView > onDestroy > onDetach
 *          First: onHiddenChanged
 *
 * 5. addToBackStack 添加到回退栈的方式
 *          回退时只要有ID的组件，都可以保存状态
 * 6. popBackStack() 回退到栈内前一个 Fragment
 * 7. popBackStack(int id, int flag) 回退到栈内特定的 Fragment
 * 8. 回退栈列表：FragmentManager.getFragments()
 * 9. 回退栈变化监听，处理 back 建触发的默认回退：onBackStackChanged
 */
public class AxFragmentManager implements IAxFragmentManager, FragmentManager.OnBackStackChangedListener {

    private static final String TAG = "AxFragmentManager";
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

        // 管理
        mFragmentManager.addOnBackStackChangedListener(this);
    }

    public boolean showFragment(int type, Bundle bundle) {
        BaseAxFragment target = null;
        List<Fragment> list = mFragmentManager.getFragments();
        BaseAxFragment targetItem;
        for (Fragment item : list) {
            targetItem = (BaseAxFragment) item;
            if (targetItem.getType() == type) {
                target = targetItem;
                break;
            }
        }

        if (null == target) {
            target = mFragmentFactory.createFragment(type,
                    bundle.getString(KEY_PARAM_1, "default_1"),
                    bundle.getString(KEY_PARAM_2, "default_2"));
            target.setFragmentManager(this);
            replace(type, target);
        } else {
            target.setArguments(bundle);
            mFragmentManager.popBackStack(target.getType(), 0);
        }

        return true;
    }

    public boolean back(Bundle bundle) {
        return false;
    }

    public int getStackCount() {
        return mFragmentManager.getBackStackEntryCount();
    }

    private boolean replace(int type, BaseAxFragment target) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.addToBackStack(String.valueOf(type));

        // replace() 会销毁视图，即调用onDestoryView、onCreateView等一系列生命周期
//        transaction.replace(mContainerId, target);
//        transaction.commit();
//        Log.d(TAG, "replace");

        // show()，hide() 是让Fragment的 View setVisibility(true/false)，不会调用生命周期；
        if (null != mCurrentFragment) {
            Log.d(TAG, "pre fragment" + mCurrentFragment.getType());
            transaction.hide(mCurrentFragment);
        } else {
            Log.d(TAG, "no pre fragment");
        }
        if (!target.isAdded()) {
            transaction.add(mContainerId, target);
        }
        transaction.show(target);
        transaction.commit();
        Log.d(TAG, "show + hide");

        return true;
    }

    @Override
    public void onBackStackChanged() {
        List<Fragment> list = mFragmentManager.getFragments();
        if (null != list && list.size() > 0) {
            mCurrentFragment = (BaseAxFragment) list.get(list.size() - 1);
        } else {
            mCurrentFragment = null;
        }
        for (Fragment item : list) {
            Log.d(TAG, "list item type: " + ((BaseAxFragment) item).getType());
        }
    }
}
