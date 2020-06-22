package com.justnow.androidsummarize.fragment.navigation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import android.os.Bundle;

import com.justnow.androidsummarize.R;


/**
 * 通过 Navigation 管理 Fragment 方式：
 * 1. Fragment 回退时只要有ID的组件，都可以保存状态，如页面编辑的文本等，回退时仍然存在
 * 2. AppCompatActivity 的 onBackPressed 已经支持了 NavController::popBackStack()
 * 3. 切换生 Fragment： 会销毁视图
 *      切换Fragment：
 *          Second: onAttach > onCreate > onCreateView > onStart > onResume >>
 *          First: onPause > onStop > onDestroyView >>
 *      Back 返回栈内前一个：
 *          First: onCreateView > onStart > onResume
 *          Second: onPause > onStop > onDestroyView > onDestroy > onDetach
 * 4. NavController::navigate 路由 Fragment，可以带参数
 * 5. NavController::popBackStack(@IdRes int destinationId, boolean inclusive) 回退到栈内特定的 Fragment
 * 6. NavController 的 navigateUp 和 popBackStack 都可以返回上一级：
 *      popBackStack 可以返回所有的栈到 Activity
 *      navigateUp 只能返回到最后一个 startDestination Fragment
 *
 * 7. todo: Navigation、SafeArgs、NavigationUI、DeepLink
 */
public class NavigationFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_fragment);

        Navigation.findNavController(NavigationFragmentActivity.this, R.id.nav_host_fragment)
                .addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
                    @Override
                    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

                    }
                });
    }

    @Override
    public void onBackPressed() {
        // 会返回到 Activity
//        super.onBackPressed();
        // 只能返回到 Root Fragment
        Navigation.findNavController(NavigationFragmentActivity.this, R.id.nav_host_fragment).navigateUp();
    }
}