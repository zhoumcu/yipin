package com.hunter.fastandroid.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hunter.fastandroid.R;
import cn.zhiao.baselib.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品清单界面
 * <p>
 */
public class OrderConfirmationFragment extends BaseFragment implements View.OnClickListener {

    public static final String TAG = OrderConfirmationFragment.class.getSimpleName();

    //请求地址码
    public static final int REQUEST_CODE_SELECT_ADDRESS_CODE = 1001;

    //门店地址
    @Bind(R.id.sdv_picture_order_confirmation)
    SimpleDraweeView sdvPictureOrderConfirmation;
    @Bind(R.id.tv_store_name_order_confirmation)
    TextView tvStoreNameOrderConfirmation;
    @Bind(R.id.tv_store_distance_order_confirmation)
    TextView tvStoreDistanceOrderConfirmation;
    @Bind(R.id.ll_consignee_address_order_confirmation)
    LinearLayout llConsigneeAddressOrderConfirmation;
    @Bind(R.id.tv_consignee_name_order_confirmation)
    TextView tvConsigneeNameOrderConfirmation;
    @Bind(R.id.tv_consignee_phone_order_confirmation)
    TextView tvConsigneePhoneOrderConfirmation;
//    @Bind(R.id.et_consignee_name_order_confirmation)
//    EditText etConsigneeNameOrderConfirmation;
//    @Bind(R.id.et_consignee_phone_order_confirmation)
//    EditText etConsigneePhoneOrderConfirmation;

    //无地址
    @Bind(R.id.tv_empty_address)
    TextView tvEmptyAddress;

    //个人地址
    @Bind(R.id.tv_person_name_order_confirmation)
    TextView tvPersonNameOrderConfirmation;
    @Bind(R.id.tv_person_phone_order_confirmation)
    TextView tvPersonPhoneOrderConfirmation;
    @Bind(R.id.tv_person_address_order_confirmation)
    TextView tvPersonAddressOrderConfirmation;
    @Bind(R.id.ll_person_address_order_confirmation)
    LinearLayout llPersonAddressOrderConfirmation;

    //商品详情
    @Bind(R.id.rcv)
    RecyclerView rcv;

    //付款(金额)
    @Bind(R.id.tv_money_order_confirmation)
    TextView tvMoneyOrderConfirmation;
    @Bind(R.id.tv_pay_order_confirmation)
    TextView tvPayOrderConfirmation;
    //新增加的内容
    @Bind(R.id.tv_fenqi_num)
    TextView tvFenqiNum;
    @Bind(R.id.cb_fenqi_pay)
    CheckBox cbFenqiPay;
    @Bind(R.id.ll_fenqi)
    LinearLayout llFenqi;
    @Bind(R.id.cb_allpay)
    CheckBox cbAllpay;
    @Bind(R.id.ll_allpqy)
    LinearLayout llAllpqy;
    @Bind(R.id.tv_pay_type)
    TextView tvPayType;
    @Bind(R.id.et_buyer_message)
    EditText etBuyerMessage;
    @Bind(R.id.tv_score)
    TextView tvScore;
    @Bind(R.id.tv_score_yuan)
    TextView tvScoreYuan;
    @Bind(R.id.cb_score)
    CheckBox cbScore;
    @Bind(R.id.ll_score)
    LinearLayout llScore;
    //    @Bind(R.id.ll_discount)
//    LinearLayout llDiscount;
//    @Bind(R.id.tv_discount_num) //放在每个商品的adapter里面
//    TextView tvDiscountNum;
    @Bind(R.id.order_list_view)
    LinearLayout orderListView;

    private Integer style = null;//回传的是个人地址/门店地址

    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;


    private String goodsId;//立即购买传来的
    private String goodsNumbers;//立即购买传回来的购买数量

    private int loanTime;//立即购买传来的贷款时间


    //积分
    private boolean mIsCheck = false;

    private double prices;//合计价格
    private boolean defaultAddressF = false;//标记是否有默认地址
    private boolean isChooseAddress = false;//标记用户是否选择了地址
    private double firstPay;//首付金额
    private int payType = 1;//支付方式，1为分期，2为全额，默认为分期
    private boolean isLoadedData = false;//商品信息加载是否完成
    private String subScorePrice;//选择抵扣积分之后的价格

    private double currentPrice;//目前显示的价格，跟着用户选择优惠券、积分变化

    /**
     * 设置价钱
     *
     * @param isCheck
     */
    public void settvMoneyOrderConfirmation(boolean isCheck) {
        mIsCheck = isCheck;
    }

    /**
     * 显示合计价格
     */
    private void ShowAllPrice() {
    }

    public static OrderConfirmationFragment newInstance() {
        OrderConfirmationFragment fragment = new OrderConfirmationFragment();
        return fragment;
    }


    public static void jumpIn(AppCompatActivity ac) {
        FragmentManager fragmentmanager = ac.getSupportFragmentManager();
        Fragment fragment = OrderConfirmationFragment.newInstance();
        fragmentmanager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment, OrderConfirmationFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_order_confirmation;
    }

    @Override
    public void initView() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {

        }
    }


    @Override
    protected void initPresenter() {

    }


    private void initEvent() {

        //付款
        tvPayOrderConfirmation.setOnClickListener(this);
        //暂无地址
        tvEmptyAddress.setOnClickListener(this);
        //门店选择地址
//        llConsigneeAddressOrderConfirmation.setOnClickListener(this);
        //个人选择地址
//        llPersonAddressOrderConfirmation.setOnClickListener(this);
        //分期支付
        llFenqi.setOnClickListener(this);
        //全额支付
        llAllpqy.setOnClickListener(this);
        //分期支付
        cbFenqiPay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {//显示分期支付字样提示并更改价格
//                    firstPay = goodsList.getItems().get(0).getFirst_pay();//目前为一个
//                    firstPay = (CommonUtil.keepTwoDecimal(Double.valueOf(firstPay) * goodsList.getPcount())) + "";//乘以购买数量
                    payType = 1;
                    tvPayType.setText("首付:");
                    notifyAllAdapter();

                    if (cbScore.isChecked()) {
//                        tvMoneyOrderConfirmation.setText(String.format("￥%s元", calculateScorePrice(1)));
                        tvMoneyOrderConfirmation.setText(String.format("￥%s元", calculateScorePrice(1)));
                    } else {
//                        tvMoneyOrderConfirmation.setText(String.format("￥%s元", firstPay));
                        tvMoneyOrderConfirmation.setText(String.format("￥%s元", calculateAllPrice()));
                    }
                    cbAllpay.setChecked(false);
                } else {
                    cbAllpay.setChecked(true);
                }
            }
        });

        //积分选项
        llScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbScore.isChecked()) {
                    cbScore.setChecked(false);
                } else {
                    cbScore.setChecked(true);
                }
            }
        });
        //积分checkbox
        cbScore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mIsCheck = isChecked;
                if (!isLoadedData) {//商品清单没有加载完成
                    return;
                }
                if (isChecked) {//选择了抵扣积分
                    if (payType == 1) {
                        if (firstPay >= 0.01) {
                            tvMoneyOrderConfirmation.setText(String.format("￥%s元", calculateScorePrice(1)));
                        }
                    } else if (payType == 2) {
                        tvMoneyOrderConfirmation.setText(String.format("￥%s元", calculateScorePrice(2)));
                    }
                } else {//取消选择
                    if (payType == 1) {
                        tvMoneyOrderConfirmation.setText(String.format("￥%s元", firstPay));
                    } else if (payType == 2) {

                    }
                }
            }
        });
//        llDiscount.setOnClickListener(this);//点击弹出优惠券选择
    }

    /**
     *
     */
    public void notifyAllAdapter() {

    }

    /**
     * @param payType 1为分期 2为全额
     * @return
     */
    private String calculateScorePrice(int payType) {
        double price = 0;
        return subScorePrice;
    }

    /**
     * 计算总价
     *
     * @return 返回计算的总价
     */
    private double calculateAllPrice() {//1为分期2为全额
        double allPrice = 0;

        return allPrice;
    }

    //请求数据  或  绑定数据
    private void requestData() {
        requestNewGoodsInfo();
        requestCouponList();//请求可用的优惠券列表  //lujixing open
    }

    /**
     * 请求可用优惠券列表
     */
    private void requestCouponList() {

    }


    /**
     * 默认地址
     */
    double time = 201512121043d;

    private void requestAddressInfo() {

    }


//    /**
//     * 商品清单
//     */
//    private void requestGoodsInfo() {
//        String urlShoppingConfirm = URL_SHOPPING_CONFIRM;
//        RequestParams params = new RequestParams();
//        ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
////        nameValuePairs.add(new BasicNameValuePair("products[22223527]", "1"));
////        nameValuePairs.add(new BasicNameValuePair("products[22223638]", "1"));
//
//        //购物车（结算）
//        if (mList != null) {
//            int size = mList.size();
//            for (int i = 0; i < size; i++) {
//                ShoppingCartGoods shoppingCartGoods = mList.get(i);
//                String pidSkuId = shoppingCartGoods.getProduct_id();
//                if (Integer.valueOf(shoppingCartGoods.getSku_id()) != 0) {
//                    pidSkuId = pidSkuId + "." + shoppingCartGoods.getSku_id();
//                }
//                nameValuePairs.add(new BasicNameValuePair("products[" + pidSkuId + "][quantity]", goodsNumbers));
//                nameValuePairs.add(new BasicNameValuePair("products[" + pidSkuId + "][shoufu]", shoppingCartGoods.getShoufu()));
//                nameValuePairs.add(new BasicNameValuePair("products[" + pidSkuId + "][instalments]", shoppingCartGoods.getInstalment()));
//
//            }
//        }
//
//        //单个商品（点击立即购买）
//        if (!TextUtils.isEmpty(goodsId)) {
//            nameValuePairs.add(new BasicNameValuePair("products[" + goodsId + "][quantity]", goodsNumbers));
//        }
//
//        if(firstPay!=0){
//            nameValuePairs.add(new BasicNameValuePair("products[" + goodsId + "][shoufu]", firstPay+""));
//        }
//
//        if(loanTime!=0){
//            nameValuePairs.add(new BasicNameValuePair("products[" + goodsId + "][instalments]", loanTime+""));
//        }
//        params.addBodyParameter("uid", SharePreferUtil.getString(QYApplication.getApplication(), "uid"));
//        params.addBodyParameter(nameValuePairs);
//        //20160708新添加参数：商品详情的json和tourtoken
////        L.e("mGoodDetail--:"+GsonUtils.getInstance().toJson(mGoodDetail));
//        params.addBodyParameter("json", GsonUtils.getInstance().toJson(mGoodDetail));
//        params.addBodyParameter("tour_token", SharePreferUtil.getString(getContext(), "tour_token"));
//
//        urlShoppingConfirm = UrlUtils.addUrlBodyParamsAfterLogin(getContext(), urlShoppingConfirm, params);
////        urlShoppingConfirm = urlShoppingConfirm+"?ajax=json"+"&tour_token="+ SharePreferUtil.getString(getContext(),"tour_token");
//        L.e(urlShoppingConfirm);
//        httpUtils.send(HttpRequest.HttpMethod.POST, urlShoppingConfirm, params, new RequestCallBack<String>() {
//            @Override
//            public void onSuccess(ResponseInfo<String> responseInfo) {
//                L.e("商品清单：" + responseInfo.result);
//                if (!responseInfo.result.contains("</b>")) {
//
//                    JSONObject json = JSONObject.parseObject(responseInfo.result);
//                    if(!json.getString("code").equals("1")){
//                        if (mListGoods.size() > 0) {
//                            mListGoods.clear();
//                        }
//                        Toast.makeText(getContext(),"请求失败",Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//
//                    goodsList = GsonUtils.jsonToBean(json.getJSONObject("data").toJSONString(), GoodsList.class);
//
////                    if (Integer.valueOf(goodsList.getCode()) > 0) {
//
//                        mListGoods.addAll(goodsList.getItems());
//                        mOrderConfirmationAdapter.setPoints(Double.valueOf(goodsList.getPoints()));
//                        mOrderConfirmationAdapter.notifyDataSetChanged();
//                        firstPay = goodsList.getTotal_shoufu();
////                        currentPrice = CommonUtil.keepTwoDecimal(Double.valueOf(firstPay) * goodsList.getPcount());//乘以购买数量
////                        firstPay = currentPrice + "";
//                        if (tvScore != null) {
//                            tvScore.setText(String.format("%s个白兔币", goodsList.getPoints()));
//                        }
//                        if (tvScoreYuan != null) {
//                            tvScoreYuan.setText(String.format("(本次可抵扣%s元)", CommonUtil.keepTwoDecimal(calculateScoreable(payType))));
//                        }
//                        isLoadedData = true;//商品信息加载完成
//                        if (goodsList != null) {
//                            if (tvMoneyOrderConfirmation != null) {
////                                tvMoneyOrderConfirmation.setText(goodsList.getSpend() + "");//原来代码
//                                ShowAllPrice();
//                            }
//                        }
//                        //该商品不支持分期
//
//                        if (goodsList!=null&&goodsList.getGconfigs().isInstalment()==false) {
//                            allPayInit();
//                        }
//                    //转化list结果集显示方式
//
//                    //先以供应商分类
//                    Map<String,ArrayList> ss = sortBySupplier(mListGoods);
//                    Iterator it = ss.keySet().iterator();
//                    Map<String,ArrayList<GoodsList.ItemsEntity>> tempMap = new HashMap<String, ArrayList<GoodsList.ItemsEntity>>();
//                    while(it.hasNext()){
//                        String key = (String)it.next();
//                        ArrayList list1 = ss.get(key);
//                        //再以贷款产品分类
//                        Map<String,ArrayList<GoodsList.ItemsEntity>> items = sortByLoan(list1);
//                        Iterator it1 = items.keySet().iterator();
//                        while(it1.hasNext()){
//                            String key2  = (String)it1.next();
//                            ArrayList<GoodsList.ItemsEntity> list2 = items.get(key2);
//                            tempMap.put(String.format("%s&%s",key,key2),list2);
//                        }
//
//                    }
//
//                    //包装好的结果集
//                    //订单个数：
//                    Iterator tempIt = tempMap.keySet().iterator();
//                    adapterList  = new ArrayList<OrderConfirmationNewAdapter>();
//                    while(tempIt.hasNext()){
//                        String key = (String)tempIt.next();
//                        ArrayList<GoodsList.ItemsEntity> itemList = tempMap.get(key);
//                        L.e("---------size"+itemList.size());
//                        OrderConfirmationNewAdapter orderConfirmationNewAdapter = new OrderConfirmationNewAdapter(OrderConfirmationFragment.this,itemList,itemList.get(0).getLoan() ,payType);
//                        ExStaggeredGridLayoutManager staggeredGridLayoutManager = new ExStaggeredGridLayoutManager(ROWNUMBER, StaggeredGridLayoutManager.VERTICAL);
//                        orderConfirmationNewAdapter.setUpdateComfirm(OrderConfirmationFragment.this);
//                        RecyclerView rcvTemp = (RecyclerView)LayoutInflater.from(getContext()).inflate(R.layout.rcv,null);
//
//                        rcvTemp.setLayoutManager(staggeredGridLayoutManager);
//                        rcvTemp.setNestedScrollingEnabled(false);//设置RecyclerView不可滑动
//
//                        rcvTemp.setAdapter(orderConfirmationNewAdapter);
//                        orderListView.addView(rcvTemp);
//                        adapterList.add(orderConfirmationNewAdapter);
//                    }
//
//
//
//
//
//
//
//                }
////                } else {
////                    Shareutl.shortToast("服务器连接失败！");
////                }
//            }
//
//            @Override
//            public void onFailure(HttpException e, String s) {
//                L.e("HttpException:" + e + ";" + s);
//            }
//        });
//    }


    /**
     * 商品清单
     */
    private void requestNewGoodsInfo() {

    }

    /**
     * 根据供应商分类
     *
     * @param list
     * @return
     */
    public Map<String, ArrayList> sortBySupplier(List list) {
        TreeMap tm = new TreeMap();

        return tm;
    }


    /**
     * 不支持分期初始化
     */
    private void allPayInit() {
        llFenqi.setVisibility(View.GONE);
        payType = 2;//设置为全额
        cbAllpay.setChecked(true);
        cbAllpay.setEnabled(false);
        llAllpqy.setEnabled(false);
    }

    /**
     * 订单确认
     */
    private void requestOrderConfirmNew() {

    }

    /**
     * 订单确认
     */
    private void requestOrderConfirm() {

    }


    @Override
    public void onResume() {
        super.onResume();
        if (!isChooseAddress) {//不是从地址过来的
            requestAddressInfo();//重新请求地址，防止用户修改默认地址
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //暂无地址
            case R.id.tv_empty_address:
                isChooseAddress = false;
//                AddressManagerFragment.jumpIn(mMainActivity);
                break;
            //选择地址：
//            case R.id.ll_consignee_address_order_confirmation:
//                isChooseAddress = false;
////                ChooseAdressFragment.jumpIn(this, ChooseAdressFragment.RESULT_CODE_SELECT_ADDRESS_CODE_ORDER_CONFIRMATION, REQUEST_CODE_SELECT_ADDRESS_CODE);
//                break;
            //个人选择地址
//            case R.id.ll_person_address_order_confirmation:
//                isChooseAddress = false;
////                ChooseAdressFragment.jumpIn(this, ChooseAdressFragment.RESULT_CODE_SELECT_ADDRESS_CODE_ORDER_CONFIRMATION, REQUEST_CODE_SELECT_ADDRESS_CODE);
//                break;
            //付款
            case R.id.tv_pay_order_confirmation:
                tvPayOrderConfirmation.setEnabled(false);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tvPayOrderConfirmation.setEnabled(true);
                    }
                }, 1000);
                //if (CommonUtil.vaildCardFlowFromStatus(getContext())) {

                // TODO: 2016/5/11 测试
//                    requestOrderConfirm();
                //(prices*Double.valueOf(mGoodDetail.getFenqi().get(0).getFenqi()))<=payableAmount
                break;
            case R.id.ll_fenqi://分期支付
                if (cbFenqiPay.isChecked()) {
                    cbFenqiPay.setChecked(false);
                } else {
                    cbFenqiPay.setChecked(true);
                }
                break;
            case R.id.ll_allpqy://全额支付
                if (cbAllpay.isChecked()) {
                    cbAllpay.setChecked(false);
                } else {
                    cbAllpay.setChecked(true);
                }
                break;
//            case R.id.ll_discount://弹出优惠券选择框
//                // TODO: 2016/7/8 弹出优惠券选择框，（使用优惠或不使用优惠）用户选择完后，使用优惠时，则要重新计算价格
//                if (couponEntity != null && couponEntity.getData().size() != 0) {
//                    MyBottomDialog myBottomDialog = new MyBottomDialog(getContext(), R.layout.coupons_bottom_dialog, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//                    final Dialog dialog = myBottomDialog.getDialog();
//                    Window window = myBottomDialog.getWindow();
//                    ImageView ivClose = (ImageView) window.findViewById(R.id.iv_close);
//                    ivClose.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            dialog.dismiss();
//                        }
//                    });
//
//                    RecyclerView rcv = (RecyclerView) window.findViewById(R.id.rcv);
//                    rcv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
//                    CouponAdapter couponAdapter = new CouponAdapter(getContext(), R.layout.item_coupon, couponEntity.getData(), 1);
//                    rcv.setAdapter(couponAdapter);
//                    couponAdapter.setOnItemClickListener(new OnItemClickListener() {
//                        @Override
//                        public void onItemClick(ViewGroup parent, View view, Object o, int position) {
//                            selectCoupon = couponEntity.getData().get(position);//用户选择了优惠券
//                            tvDiscountNum.setText(selectCoupon.getCoupon_type_uses());
//                            tvMoneyOrderConfirmation.setText(String.format("￥%s元", calculateAllPrice()));
//                            dialog.dismiss();
//                        }
//
//                        @Override
//                        public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
//                            return false;
//                        }
//                    });
//
//                } else {
//                    Shareutl.shortToast("没有可用的优惠券！");
//                }
//                break;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.tv_pay_order_confirmation)
    public void onClick() {
        OrderPayFragment.jumpIn((AppCompatActivity) getActivity());
    }
}
