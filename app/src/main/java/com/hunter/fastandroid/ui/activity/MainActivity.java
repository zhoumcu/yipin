package com.hunter.fastandroid.ui.activity;

import android.view.View;
import android.widget.FrameLayout;

import com.hunter.fastandroid.R;
import com.hunter.fastandroid.ui.fragment.CategoryFragment;
import com.hunter.fastandroid.ui.fragment.HomeFragment;
import com.hunter.fastandroid.ui.fragment.SettingFragment;

import butterknife.Bind;
import butterknife.OnClick;
import cn.zhiao.baselib.base.BaseActivity;

public class MainActivity extends BaseActivity {
    @Bind(R.id.contains)
    FrameLayout contains;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        addFragment(R.id.contains, new HomeFragment());
    }

    @Override
    public void initPresenter() {

    }

    @OnClick({R.id.ll_shopping, R.id.ll_category, R.id.ll_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_shopping:
                replaceFragment(R.id.contains, new HomeFragment());
                break;
            case R.id.ll_category:
                replaceFragment(R.id.contains, new CategoryFragment());
                break;
            case R.id.ll_setting:
                replaceFragment(R.id.contains, new SettingFragment());
                break;
        }
    }
}
