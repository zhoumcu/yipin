package com.hunter.fastandroid.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.hunter.fastandroid.R;
import com.hunter.fastandroid.ui.fragment.GoodsDetailFragment;

import butterknife.Bind;
import cn.zhiao.baselib.base.BaseActivity;

/**
 * author：Administrator on 2017/3/30 16:52
 * company: xxxx
 * email：1032324589@qq.com
 */

public class FoodDetailActivity extends BaseActivity {
    @Bind(R.id.container)
    FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public void initView() {
        addFragment(R.id.container,new GoodsDetailFragment());
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.frg_foods_detail;
    }
}
