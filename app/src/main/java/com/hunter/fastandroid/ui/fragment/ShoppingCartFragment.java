package com.hunter.fastandroid.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hunter.fastandroid.R;
import com.hunter.fastandroid.adapter.shoppingcart.ShoppingcartAdapter;
import com.hunter.fastandroid.dao.ShoppingCartGoods;
import com.hunter.fastandroid.utils.CommonUtils;
import com.jude.easyrecyclerview.EasyRecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.zhiao.baselib.base.BaseFragment;
import cn.zhiao.baselib.utils.CommonUtil;


/**
 * 购物车
 * 2015.11.16加入布局，适配器，全选单选的逻辑。数据用的假数据(数量价钱逻辑没写，需要在fragment和adapter之间传值)
 * 2015.12.11加入购物车真实数据(接口)，修改全选/全不选的逻辑，以及传值到Adapter的值，和保存fragment的状态进入下一个界面返回来后还能显示上次的状态
 * 2015.12.12加入减少/增加商品数量，以及删除商品,清空购物车,保持fragment的数据同步着更新
 * 2015.12.18加入结算的功能
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
    private ShoppingcartAdapter mShoppingcartAdapter;
    private List<ShoppingCartGoods> mListGoods = new ArrayList<>();

    //设置商品数量
    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
        setTvGoodsNumber(goodsNumber);
    }

    public void setTvGoodsNumber(int goodsNumber) {
        tvPay.setText("结算");
    }

    //设置商品价钱   carriage="不含运费"
    public void setGoodsPrice(double goodsPrice, String carriage) {
        this.goodsPrice = goodsPrice;
        setTvGoodsPrice(goodsPrice, carriage);
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
        setTvGoodsPrice(goodsPrice);
    }

    public void setTvGoodsPrice(double goodsPrice) {
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        tvGoodsPriceAndCarriage.setText(getResources().getString(R.string.money_us_dollar) + decimalFormat.format(goodsPrice));
    }

    public void setTvGoodsPrice(double goodsPrice, String carriage) {
        tvGoodsPriceAndCarriage.setText(getResources().getString(R.string.money_us_dollar) + goodsPrice + "\n" + carriage);
    }

    public void setTvGoodsShoufuAndCarriage(double shoufuPay) {
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        tvGoodsShoufuAndCarriage.setText("首付:" + getResources().getString(R.string.money_us_dollar) + decimalFormat.format(shoufuPay));
    }

    public void setTvGoodsMonthpayAndCarriage(double monthPay) {
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        tvGoodsMonthpayAndCarriage.setText("首期月供:" + getResources().getString(R.string.money_us_dollar) + decimalFormat.format(monthPay));
    }

    public ShoppingCartFragment() {

    }

    public static ShoppingCartFragment newInstance() {
        ShoppingCartFragment shoppingCartFragment = new ShoppingCartFragment();
        return shoppingCartFragment;
    }

    public static void jumpIn(AppCompatActivity ac) {
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    protected void initPresenter() {

    }

    //绑定数据
    private void bindData() {

        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(ROWNUMBER, StaggeredGridLayoutManager.VERTICAL);
        prrcv.setLayoutManager(mStaggeredGridLayoutManager);
        if (mShoppingcartAdapter == null) {
            mShoppingcartAdapter = new ShoppingcartAdapter(this, mListGoods);
        }
        prrcv.setAdapter(mShoppingcartAdapter);

        //动态设置高度
        int y = CommonUtil.getScreenMetrics(getActivity()).y;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) prrcv.getLayoutParams();
        layoutParams.height = y - CommonUtil.xmldip2px(getActivity(), R.dimen.common_padding40) * 3;
        prrcv.setLayoutParams(layoutParams);
    }

    private void initEvent() {
        //全选
        ckCheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isCheck = ckCheckAll.isChecked();
                if (mShoppingcartAdapter != null) {
                    mShoppingcartAdapter.setCheckAll(isCheck);
                    mShoppingcartAdapter.notifyDataSetChanged();
                    if (isCheck) {

                        //注意这个数量一定要你按商品数量(一种商品可能选择了几件)   -->2015.12.11加入真实数据已修改 （已去掉TODO提示）
                        setGoodsNumber(getGoodsTotalNumber(mListGoods)); //商品总数
                        //  这里缺少设置价钱,注意：这里一定要算总的商品的价钱(一种商品可能选择了几件)    -->同上（已去掉TODO提示）
                        //setGoodsPrice(getGoodsTotalPrice(mListGoods), "不含运费");//商品总价
                        //setGoodsPrice(getGoodsTotalPrice(mListGoods));
                        mShoppingcartAdapter.setCheckALL(isCheck);
                        mShoppingcartAdapter.setCheckNumber(mListGoods.size());
                        setGoodsPayPrice(mListGoods);
                    } else {
                        setGoodsNumber(0);
                        mShoppingcartAdapter.setCheckALL(isCheck);
                        mShoppingcartAdapter.setCheckNumber(0);
                        //  这里缺少设置价钱    -->已修改为正式数据  （已去掉TODO提示）
                        //setGoodsPrice(0.0, "不含运费");//商品总价
                        setGoodsPrice(0.0);//商品总价
                        setTvGoodsShoufuAndCarriage(0);
                        setTvGoodsMonthpayAndCarriage(0);
                    }
                }
            }
        });

        //Item的点击
        mShoppingcartAdapter.setOnItemClickListener(new ShoppingcartAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                GoodsDetailFragment.jumpIn((AppCompatActivity) getActivity(), mListGoods.get(position).getProduct_id());
            }
        });
    }

    /**
     * 得到商品总数
     *
     * @param mListGoods
     * @return
     */
    public int getGoodsTotalNumber(List<ShoppingCartGoods> mListGoods) {
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
    public double getGoodsTotalPrice(List<ShoppingCartGoods> mListGoods) {
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
    public void setGoodsPayPrice(List<ShoppingCartGoods> mListGoods) {
        double totalPrice = 0;
        double shoufuPay = 0;
        double monthPay = 0;
        for (int i = 0; i < mListGoods.size(); i++) {
            ShoppingCartGoods shoppingCartGoods = mListGoods.get(i);
            if (shoppingCartGoods.getInstalments() > 0) {
                totalPrice += Integer.valueOf(shoppingCartGoods.getQuantity()) * Double.valueOf(shoppingCartGoods.getShoufu());
                shoufuPay += Integer.valueOf(shoppingCartGoods.getQuantity()) * Double.valueOf(shoppingCartGoods.getShoufu());
                monthPay += Integer.valueOf(shoppingCartGoods.getQuantity()) * shoppingCartGoods.getmonthpay();
            } else {
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

    public void requestData() {

    }

    public void setGoodNumberAndPrice() {
        if (mShoppingcartAdapter != null) {
            boolean checked;
            if (ckCheckAll != null) {
                checked = ckCheckAll.isChecked();
                if (checked) {
                    setGoodsNumber(getGoodsTotalNumber(mListGoods)); //商品总数
                    mShoppingcartAdapter.setCheckALL(checked);
                    mShoppingcartAdapter.setCheckNumber(mListGoods.size());
                    //setGoodsPrice(getGoodsTotalPrice(mListGoods), "不含运费");//商品总价
                    //setGoodsPrice(getGoodsTotalPrice(mListGoods));//商品总价
                    setGoodsPayPrice(mListGoods);
                } else {
//                setGoodsNumber(0);
//                mShoppingcartAdapter.setCheckALL(checked);
//                mShoppingcartAdapter.setCheckNumber(0);
//                setGoodsPrice(0.0, "不含运费");

                    //跳转页面返回来后，设置上次保存的状态
                    SparseArray<Boolean> isCheckArr = mShoppingcartAdapter.getIsCheckArr();

                    //计算选中商品总数
                    int totalNumber = 0;
                    double totalPrice = 0;
                    double shoufuPay = 0;
                    double monthPay = 0;
                    for (int i = 0; i < mListGoods.size(); i++) {
                        ShoppingCartGoods shoppingCartGoods = mListGoods.get(i);
                        Boolean aBoolean = isCheckArr.get(i);
                        if (aBoolean != null) {
                            if (aBoolean) {
                                totalNumber += Integer.valueOf(shoppingCartGoods.getQuantity());
                                if (shoppingCartGoods.getInstalments() > 0) {
                                    totalPrice += Integer.valueOf(shoppingCartGoods.getQuantity()) * Double.valueOf(shoppingCartGoods.getShoufu());
                                    shoufuPay += Integer.valueOf(shoppingCartGoods.getQuantity()) * Double.valueOf(shoppingCartGoods.getShoufu());
                                    monthPay += Integer.valueOf(shoppingCartGoods.getQuantity()) * shoppingCartGoods.getmonthpay();
                                } else {
                                    totalPrice += Integer.valueOf(shoppingCartGoods.getQuantity()) * Double.valueOf(shoppingCartGoods.getPrice());
                                }
                            }
                        }
                    }
                    setGoodsNumber(totalNumber); //商品总数
                    //setGoodsPrice(totalPrice, "不含运费");//商品总价
                    setGoodsPrice(totalPrice);
                    setTvGoodsShoufuAndCarriage(shoufuPay);
                    setTvGoodsMonthpayAndCarriage(monthPay);
                }
            }
        }
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.tv_pay)
    public void onClick() {
        if (!CommonUtils.getLoginState(getContext())) {
            CommonUtils.gotoLogin(getContext());
            return;
        }
        entryOrderConfirmFragment();
    }
    /**
     * 跳转到商品清单页面
     */
    private void entryOrderConfirmFragment () {
        if (mShoppingcartAdapter != null) {

            ArrayList<ShoppingCartGoods> list = new ArrayList<>();

            boolean checked;
            if (ckCheckAll != null) {
                checked = ckCheckAll.isChecked();
                if (checked) {
                    list.addAll(mListGoods);
                } else {
                    SparseArray<Boolean> isCheckArr = mShoppingcartAdapter.getIsCheckArr();
                    for (int i = 0; i < mListGoods.size(); i++) {
                        ShoppingCartGoods shoppingCartGoods = mListGoods.get(i);

                        Boolean aBoolean = isCheckArr.get(i);
                        if (aBoolean != null) {
                            if (aBoolean) {
                                list.add(shoppingCartGoods);
                            }
                        }
                    }
                }
            }

            OrderConfirmationFragment.jumpIn((AppCompatActivity)getActivity(),list);
        }
    }
}
