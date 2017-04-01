package com.hunter.fastandroid.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hunter.fastandroid.R;
import cn.zhiao.baselib.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * 订单支付
 *  2015.12.18 新增传递过来的确认订单的信息，加入订单支付时获取签名的接口
 * Created by  on 2015/11/20.
 */
public class OrderPayFragment extends BaseFragment  {

    public static final String TAG = OrderPayFragment.class.getSimpleName();
    public static final String URL_ORDER_SIGN = "/order/sign";
    public static final String ALIPAY_PAY = "alipay";
    public static final String WEIXIN_PAY = "wxpay";
    public static final String ARG = "data";
    public static final String ARG_ORDER = "data_Order";
    public static final String ARG_GOODS_DETAILS = "goods_Details";

    @Bind(R.id.tv_goods_name_order_pay)
    TextView tvGoodsNameOrderPay;
    @Bind(R.id.tv_goods_price_order_pay)
    TextView tvGoodsPriceOrderPay;
    @Bind(R.id.tv_goods_number_order_pay)
    TextView tvGoodsNumberOrderPay;
    @Bind(R.id.rb_aliPay_order_pay)
    RadioButton rbAliPayOrderPay;
    @Bind(R.id.rb_weixin_order_pay)
    RadioButton rbWeixinOrderPay;
    @Bind(R.id.tv_confirmation_pay_order_pay)
    TextView tvConfirmationPayOrderPay;

    private String orderId = null;
    private String subject = null;
    private String spend = null;
    private String pcount = null;

//    private ImageView mBtnBack;//返回
    private LinearLayout periodView;
    private LinearLayout mLinearLayout;
    private LinearLayout periodSecondView;
    private TextView tvMonthpay;//显示首付金额
//    private TextView mTvManifesto;
//    private TextView mTvTime;
//    private TextView mTvPeople;
//    private TextView mTvPrice;//
//    private ImageButton mIbtSubmit;//提交
    private String titleName, order_id, datatime, amount, pkIndivcust, productid, loanterm, payAmount;
    private boolean isInstallment;
//    private boolean isDownPayment;//0首付
    private int selectProductIndex;
    private List<TextView> textList = new ArrayList<TextView>();
    //---------------------------------------分期------------------

    //支付方式
    public String pay_method = ALIPAY_PAY;

    public static boolean successWeixinPay = false;


    public OrderPayFragment() {

    }

    public static OrderPayFragment newInstance () {
        OrderPayFragment fragment = new OrderPayFragment();
        return fragment;
    }


    public static void jumpIn (AppCompatActivity ac) {
        FragmentManager fragmentmanager = ac.getSupportFragmentManager();
        Fragment fragment = OrderPayFragment.newInstance();
        fragmentmanager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment, OrderPayFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_order_pay;
    }

    @Override
    public void initView() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onResume () {
        super.onResume();
       /*if (successWeixinPay) {
            OrderFragment.jumpIn(mMainActivity, OrderFragment.POSITION_ALL);
        }*/
    }

}
