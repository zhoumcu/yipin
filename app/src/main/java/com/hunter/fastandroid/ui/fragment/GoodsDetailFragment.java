package com.hunter.fastandroid.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.hunter.fastandroid.R;
import cn.zhiao.baselib.base.BaseFragment;
import cn.zhiao.baselib.custom.SlideDetailsLayout;
import cn.zhiao.baselib.utils.CommonUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author：Administrator on 2017/3/30 16:52
 * company: xxxx
 * email：1032324589@qq.com
 */

public class GoodsDetailFragment extends BaseFragment {

    public static final String MY_TAG = "GoodsDetailFragment";
    private static final String ARG = "arg";
    @Bind(R.id.slidedetails_behind)
    WebView slidedetailsBehind;
    @Bind(R.id.slidedetails)
    SlideDetailsLayout slidedetails;

    public static GoodsDetailFragment newInstance() {
        GoodsDetailFragment goodsDetailFragment = new GoodsDetailFragment();
        return goodsDetailFragment;
    }

    public static GoodsDetailFragment newInstance(String goodsId) {
        GoodsDetailFragment goodsDetailFragment = newInstance();
        Bundle bundle = new Bundle();
        bundle.putString(ARG, goodsId);
        goodsDetailFragment.setArguments(bundle);
        return goodsDetailFragment;
    }

    //TODO  记得把剩下的界面跳转过来必须传  pid
    public static void jumpIn(AppCompatActivity ac) {
        FragmentManager fragmentmanager = ac.getSupportFragmentManager();
        Fragment fragment = newInstance();
        fragmentmanager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment, GoodsDetailFragment.MY_TAG)
                .commitAllowingStateLoss();
    }

    public static void jumpIn(AppCompatActivity ac, String goodsId) {
        FragmentManager fragmentmanager = ac.getSupportFragmentManager();
        Fragment fragment = newInstance(goodsId);
        fragmentmanager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment, GoodsDetailFragment.MY_TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public void initView() {
        slidedetailsBehind.loadUrl("http://www.vip.com/detail-1155583-180629559.html");
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.aty_foods_detail;
    }


    @OnClick({R.id.tv_service, R.id.tv_add_to_shopping_cart, R.id.tv_purchase_now})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_service:
                break;
            case R.id.tv_add_to_shopping_cart:

                break;
            case R.id.tv_purchase_now:
                if (!CommonUtil.getLoginState(getContext())) {
                    CommonUtil.gotoLogin(getContext());
                    return;
                }
                GoodsDetailDialogFragment.showDialog((AppCompatActivity) getActivity(), new GoodsDetailDialogFragment.OnResponseListenter() {
                    @Override
                    public void cancal() {
                        Toast.makeText(getActivity(), "取消",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void ok(String sku, double shoufu, int loanTime) {

                    }
                });
                break;
        }
    }
}
