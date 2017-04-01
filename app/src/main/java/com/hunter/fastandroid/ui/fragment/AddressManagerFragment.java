package com.hunter.fastandroid.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.hunter.fastandroid.R;

import butterknife.Bind;
import cn.zhiao.baselib.base.BaseFragment;

/**
 * 地址管理
 * Created by  zhoumcu on 2015/11/10.
 */
public class AddressManagerFragment extends BaseFragment {

    public static String TAG = "AddressManagerFragment";
    public static String URL_ADDRESS_MANAGER = "/address/list";
    private static String ARG = "isFromActivity";
    @Bind(R.id.lv_address_manage)
    ListView lvAddressManage;
    @Bind(R.id.pb)
    RelativeLayout pb;
    @Bind(R.id.rl_empty)
    RelativeLayout rlEmpty;
    @Bind(R.id.btn_add)
    Button btnAdd;

    public AddressManagerFragment() {

    }

    public static AddressManagerFragment newInstance() {
        AddressManagerFragment addressManagerFragment = new AddressManagerFragment();
        return addressManagerFragment;
    }

    public static AddressManagerFragment newInstance(Boolean isFromActivity) {
        AddressManagerFragment addressManagerFragment = new AddressManagerFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(ARG, isFromActivity);
        addressManagerFragment.setArguments(bundle);
        return addressManagerFragment;
    }

    public static void jumpIn(AppCompatActivity ac) {
        Fragment fragment = AddressManagerFragment.newInstance();

        FragmentManager fragmentmanager = ac.getSupportFragmentManager();
        fragmentmanager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment, AddressManagerFragment.TAG)
                .commitAllowingStateLoss();
    }

    public static void jumpIn(AppCompatActivity ac, Boolean isFromActivity) {
        Fragment fragment = AddressManagerFragment.newInstance(isFromActivity);

        FragmentManager fragmentmanager = ac.getSupportFragmentManager();
        fragmentmanager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment, AddressManagerFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_address_manage;
    }

    @Override
    public void initView() {

    }

    @Override
    protected void initPresenter() {

    }
}
