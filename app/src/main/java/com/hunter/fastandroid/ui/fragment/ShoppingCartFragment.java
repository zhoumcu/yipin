package com.hunter.fastandroid.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hunter.fastandroid.R;
import com.hunter.fastandroid.dao.ShoppingCartGoods;
import com.jude.easyrecyclerview.EasyRecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.Bind;
import cn.zhiao.baselib.base.BaseFragment;


/**
 * 购物车
 *  2015.11.16加入布局，适配器，全选单选的逻辑。数据用的假数据(数量价钱逻辑没写，需要在fragment和adapter之间传值)
 *  2015.12.11加入购物车真实数据(接口)，修改全选/全不选的逻辑，以及传值到Adapter的值，和保存fragment的状态进入下一个界面返回来后还能显示上次的状态
 *  2015.12.12加入减少/增加商品数量，以及删除商品,清空购物车,保持fragment的数据同步着更新
 *  2015.12.18加入结算的功能
 * Created by zhoumcu on 2015/11/16.
 */
public class ShoppingCartFragment extends BaseFragment {

    public static String TAG = ShoppingCartFragment.class.getSimpleName();
    public static final int REQUEST_PAY = 30001;//购物车，结算
    public static String URL_GOODS_ALL = "/cart/list";
    public static String URL_EMPTY_SHOPPING_CART = "/cart/empty";
    public static int ROWNUMBER = 1;

    @Bind(R.id.prrcv)
    EasyRecyclerView prrcv;
    @Bind(R.id.ck_check_all)
    public CheckBox ckCheckAll;
    @Bind(R.id.tv_goods_price_and_carriage)
    TextView tvGoodsPriceAndCarriage;
    @Bind(R.id.tv_pay)
    TextView tvPay;
    @Bind(R.id.pb)
    RelativeLayout pb;
    @Bind(R.id.rl_empty)
    RelativeLayout rlEmpty;
    @Bind(R.id.tv_goods_shoufu_and_carriage)
    TextView tvGoodsShoufuAndCarriage;
    @Bind(R.id.tv_goods_monthpay_and_carriage)
    TextView tvGoodsMonthpayAndCarriage;

    private View viewMenu;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;

    private int goodsNumber;//商品数量(不能删)
    private double goodsPrice;//商品价钱(不能删)

    //设置商品数量
    public void setGoodsNumber (int goodsNumber) {
        this.goodsNumber = goodsNumber;
        setTvGoodsNumber(goodsNumber);
    }

    public void setTvGoodsNumber (int goodsNumber) {
        tvPay.setText("结算");
    }

    //设置商品价钱   carriage="不含运费"
    public void setGoodsPrice (double goodsPrice, String carriage) {
        this.goodsPrice = goodsPrice;
        setTvGoodsPrice(goodsPrice, carriage);
    }

    public void setGoodsPrice (double goodsPrice) {
        this.goodsPrice = goodsPrice;
        setTvGoodsPrice(goodsPrice);
    }

    public void setTvGoodsPrice (double goodsPrice) {
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        tvGoodsPriceAndCarriage.setText(getResources().getString(R.string.money_us_dollar) + decimalFormat.format(goodsPrice));
    }

    public void setTvGoodsPrice (double goodsPrice, String carriage) {
        tvGoodsPriceAndCarriage.setText(getResources().getString(R.string.money_us_dollar) + goodsPrice + "\n" + carriage);
    }

    public void setTvGoodsShoufuAndCarriage (double shoufuPay) {
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        tvGoodsShoufuAndCarriage.setText("首付:" +getResources().getString(R.string.money_us_dollar)+ decimalFormat.format(shoufuPay));
    }

    public void setTvGoodsMonthpayAndCarriage (double monthPay) {
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        tvGoodsMonthpayAndCarriage.setText("首期月供:"+getResources().getString(R.string.money_us_dollar) + decimalFormat.format(monthPay));
    }

    public ShoppingCartFragment() {

    }

    public static ShoppingCartFragment newInstance () {
        ShoppingCartFragment shoppingCartFragment = new ShoppingCartFragment();
        return shoppingCartFragment;
    }

    public static void jumpIn (AppCompatActivity ac) {
//        FragmentManager fragmentManager = ac.getFragmentManager();
        FragmentManager fragmentmanager = ac.getSupportFragmentManager();
        Fragment fragment = ShoppingCartFragment.newInstance();
        fragmentmanager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment, ShoppingCartFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    public void initView() {

    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    protected void initPresenter() {

    }


    /**
     * 得到商品总数
     *
     * @param mListGoods
     * @return
     */
    public int getGoodsTotalNumber (List<ShoppingCartGoods> mListGoods) {
        int size = mListGoods.size();
        int totalNumber = 0;
        for (int i = 0; i < size; i++) {
            ShoppingCartGoods shoppingCartGoods = mListGoods.get(i);
            totalNumber += Integer.valueOf(shoppingCartGoods.getQuantity());
        }
        return totalNumber;
    }

    /**
     * 得到商品总价钱
     *
     * @param mListGoods
     */
    public double getGoodsTotalPrice (List<ShoppingCartGoods> mListGoods) {
        int size = mListGoods.size();
        double totalPrice = 0;
        for (int i = 0; i < size; i++) {
            ShoppingCartGoods shoppingCartGoods = mListGoods.get(i);
            totalPrice += Integer.valueOf(shoppingCartGoods.getQuantity()) * Double.valueOf(shoppingCartGoods.getPrice());
        }
        return totalPrice;
    }

    /**
     * 设置付款项目值：合计，首付，月供
     *
     * @param mListGoods
     */
    public void setGoodsPayPrice(List<ShoppingCartGoods> mListGoods){
        double totalPrice = 0;
        double shoufuPay = 0;
        double monthPay = 0;
        for (int i = 0; i < mListGoods.size(); i++) {
            ShoppingCartGoods shoppingCartGoods = mListGoods.get(i);
            if(shoppingCartGoods.getInstalments() > 0){
                totalPrice += Integer.valueOf(shoppingCartGoods.getQuantity()) * Double.valueOf(shoppingCartGoods.getShoufu());
                shoufuPay += Integer.valueOf(shoppingCartGoods.getQuantity()) * Double.valueOf(shoppingCartGoods.getShoufu());
                monthPay += Integer.valueOf(shoppingCartGoods.getQuantity()) * shoppingCartGoods.getmonthpay();
            }else{
                totalPrice += Integer.valueOf(shoppingCartGoods.getQuantity()) * Double.valueOf(shoppingCartGoods.getPrice());
            }
        }
        setGoodsPrice(totalPrice);
        setTvGoodsShoufuAndCarriage(shoufuPay);
        setTvGoodsMonthpayAndCarriage(monthPay);
    }

    /**
     * 请求商品数量
     */
    double time = 201512291043d;

    public void requestData () {

    }

    public void setGoodNumberAndPrice () {
    }



    @Override
    public void onPrepareOptionsMenu (Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    /**
     * 跳转到商品清单页面
     */
    private void entryOrderConfirmFragment () {
    }

}
