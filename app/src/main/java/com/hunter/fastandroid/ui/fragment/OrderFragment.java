package com.hunter.fastandroid.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.hunter.fastandroid.R;
import com.jude.easyrecyclerview.EasyRecyclerView;

import butterknife.Bind;
import cn.zhiao.baselib.base.BaseFragment;

/**
 * 订单
 * <p/>
 * Created by on 2015/11/10.
 */
public class OrderFragment extends BaseFragment {

    public static final String TAG = OrderFragment.class.getSimpleName();//之前为private，被改成public
    private static final String POSITION = "position";

    private static final String URL_ORDER_ALL = "/order/all";//所有订单
    private static final String URL_UNPAID_ORDER = "/order/unpaid";//待付款
    private static final String URL_ORDER_UNSHIPPED = "/order/unshipped";//待发货
    private static final String URL_ORDER_SHIPPED = "/order/shipped";//待收货
    private static final String URL_ORDER_UNRATED = "/order/unrated";//待评价

    private static final int ROWNUMBER = 1;//列数
    private static final int PAGE_SIZE = 10;//每页数据

    public static final int POSITION_ALL = 0;//全部
    public static final int POSITION_WAITING_PAY = 1;//代付款
    public static final int POSITION_WAITING_SEND_OUT = 2;//代发货
    public static final int POSITION_WAITING_RECEIPT = 3;//待收货
    public static final int POSITION_WAITING_EVALUATE = 4;//待评价

    @Bind(R.id.rg_person_order)
    RadioGroup rgPersonOrder;
    @Bind(R.id.rb_all_person_order)
    RadioButton rbAllPersonOrder;
    @Bind(R.id.rb_waiting_pay_person_order)
    RadioButton rbWaitingPayPersonOrder;
    @Bind(R.id.rb_waiting_send_out_person_order)
    RadioButton rbWaitingSendOutPersonOrder;
    @Bind(R.id.rb_waiting_receipt_person_order)
    RadioButton rbWaitingReceiptPersonOrder;
    @Bind(R.id.rb_waiting_evaluate_person_order)
    RadioButton rbWaitingEvaluatePersonOrder;
    @Bind(R.id.pb)
    RelativeLayout pb;
    @Bind(R.id.rl_empty)
    RelativeLayout rlEmpty;
    @Bind(R.id.prrcv_goods_order)
    EasyRecyclerView prrcvGoodsOrder;

    private LinearLayoutManager linearLayoutManagerGoods;
    private boolean isFresh = false;//是否下拉刷新
    private int mPage = 1;//页码
    private int mPsize = PAGE_SIZE;//数量

    public int position = POSITION_ALL;//默认加载第几个位置数据

    public int getPosition() {
        return position;
    }

    public OrderFragment() {
    }

    public static OrderFragment newInstance() {
        OrderFragment orderFragment = new OrderFragment();
        return orderFragment;
    }

    public static OrderFragment newInstance(int position) {
        OrderFragment orderFragment = new OrderFragment();

        Bundle bunble = new Bundle();
        bunble.putInt(POSITION, position);
        orderFragment.setArguments(bunble);

        return orderFragment;
    }

    public static void jumpIn(AppCompatActivity ac) {
        Fragment fragment = OrderFragment.newInstance();

        FragmentManager fragmentmanager = ac.getSupportFragmentManager();
        fragmentmanager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment, OrderFragment.TAG)
                .commitAllowingStateLoss();
    }

    public static void jumpIn(AppCompatActivity ac, int position) {
        Fragment fragment = OrderFragment.newInstance(position);

        FragmentManager fragmentmanager = ac.getSupportFragmentManager();
        fragmentmanager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment, OrderFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_order;
    }

    @Override
    public void initView() {

    }

    @Override
    protected void initPresenter() {

    }


    @Override
    public void onResume() {
        super.onResume();
        if (position == POSITION_ALL) {
            rgPersonOrder.check(R.id.rb_all_person_order);
        } else if (position == POSITION_WAITING_PAY) {
            rgPersonOrder.check(R.id.rb_waiting_pay_person_order);
        } else if (position == POSITION_WAITING_SEND_OUT) {
            rgPersonOrder.check(R.id.rb_waiting_send_out_person_order);
        } else if (position == POSITION_WAITING_RECEIPT) {
            rgPersonOrder.check(R.id.rb_waiting_receipt_person_order);
        } else if (position == POSITION_WAITING_EVALUATE) {
            rgPersonOrder.check(R.id.rb_waiting_evaluate_person_order);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
