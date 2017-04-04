package com.hunter.fastandroid.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hunter.fastandroid.R;
import com.hunter.fastandroid.adapter.autosrcollviewpager.ImagePagerBaseAdapter2;
import com.hunter.fastandroid.dao.GoodDetail;
import com.hunter.fastandroid.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import cn.zhiao.baselib.base.BaseFragment;
import cn.zhiao.baselib.custom.SlideDetailsLayout;
import cn.zhiao.baselib.utils.CommonUtil;

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
    @Bind(R.id.vp_goodsdetail)
    AutoScrollViewPager vpGoodsdetail;
    @Bind(R.id.ll_container_dot_goods_detail)
    LinearLayout llContainerDotGoodsDetail;
    @Bind(R.id.tv_goods_attribute)
    TextView tvGoodsAttribute;
    @Bind(R.id.tv_goods_shoufu)
    TextView tvGoodsShoufu;
    @Bind(R.id.tv_goods_price)
    TextView tvGoodsPrice;
    @Bind(R.id.tv_goods_fenqinum)
    TextView tvGoodsFenqinum;
    @Bind(R.id.tv_goods_discount)
    TextView tvGoodsDiscount;
    @Bind(R.id.tv_area)
    TextView tvArea;
    @Bind(R.id.tv_area_price)
    TextView tvAreaPrice;
    @Bind(R.id.tv_carriage)
    TextView tvCarriage;
    @Bind(R.id.iv_cut_down)
    ImageView ivCutDown;
    @Bind(R.id.tv_goods_number)
    TextView tvGoodsNumber;
    @Bind(R.id.iv_add)
    ImageView ivAdd;
    @Bind(R.id.tv_goods_evaluation_number)
    TextView tvGoodsEvaluationNumber;
    @Bind(R.id.sdv_person_header_goods_detail)
    SimpleDraweeView sdvPersonHeaderGoodsDetail;
    @Bind(R.id.tv_person_name_goods_detail)
    TextView tvPersonNameGoodsDetail;
    @Bind(R.id.tv_person_comment_goods_detail)
    TextView tvPersonCommentGoodsDetail;
    @Bind(R.id.ll_goods_all_evaluate_goods_detail)
    LinearLayout llGoodsAllEvaluateGoodsDetail;
    @Bind(R.id.ll_goods_comment)
    LinearLayout llGoodsComment;
    @Bind(R.id.ll_empty_comment)
    LinearLayout llEmptyComment;
    @Bind(R.id.sdv_merchant_header_goods_detail)
    SimpleDraweeView sdvMerchantHeaderGoodsDetail;
    @Bind(R.id.tv_merchant_name_goods_detail)
    TextView tvMerchantNameGoodsDetail;
    @Bind(R.id.tv_all_goods_number_goods_detail)
    TextView tvAllGoodsNumberGoodsDetail;
    @Bind(R.id.tv_sale_goods_number_goods_detail)
    TextView tvSaleGoodsNumberGoodsDetail;
    @Bind(R.id.tv_graphic_detail)
    TextView tvGraphicDetail;
    @Bind(R.id.tv_service)
    TextView tvService;
    @Bind(R.id.tv_add_to_shopping_cart)
    TextView tvAddToShoppingCart;
    @Bind(R.id.tv_purchase_now)
    TextView tvPurchaseNow;
    private ImagePagerBaseAdapter2 mImagePagerBaseAdapter2;
    private List<String> mListPicture = new ArrayList<>();
    //装点点的ImageView集合
    private List<ImageView> mListTips = new ArrayList<>();
    private GoodDetail mGoodDetail = new GoodDetail();
    private String pid;
    private String goodsNumber;

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
        mListPicture.add("http://img1.imgtn.bdimg.com/it/u=1538368858,4289171815&fm=11&gp=0.jpg");
        mListPicture.add("http://img1.imgtn.bdimg.com/it/u=1538368858,4289171815&fm=11&gp=0.jpg");
        mListPicture.add("http://img1.imgtn.bdimg.com/it/u=1538368858,4289171815&fm=11&gp=0.jpg");
        bindTips();
        bindViewPager();
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
                if (!CommonUtils.getLoginState(getContext())) {
                    CommonUtils.gotoLogin(getContext());
                    return;
                }
                GoodsDetailDialogFragment.showDialog((AppCompatActivity) getActivity(), mGoodDetail, new GoodsDetailDialogFragment.OnResponseListenter() {
                    @Override
                    public void cancal() {
                        Toast.makeText(getActivity(), "取消", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void ok(String sku, double shoufu, int loanTime) {

                    }
                });
                break;
            case R.id.tv_purchase_now:
                if (CommonUtils.getLoginState(getContext())) {
                    //无pkcust时，直接跳转到个人信息页面
//                    if (CommonUtil.getDarlingScore(getContext())) {
                    if (!CommonUtils.getBindingPhone(getContext())) {
                        buyNow();
                    } else {
                        CommonUtils.gotoValidPhone(getContext());
                    }
                } else {
                    //末登录
//                    showTips(1);
                    CommonUtils.gotoLogin(getContext());
                }
                break;
        }
    }
    private void buyNow() {
        if (mGoodDetail != null) {
            //if (mGoodDetail.getSkus().size() > 0) {
            GoodsDetailDialogFragment.showDialog((AppCompatActivity) getActivity(), mGoodDetail, new GoodsDetailDialogFragment.OnResponseListenter() {
                @Override
                public void cancal() {
                    showToast("取消");
                }

                @Override
                public void ok(String sku, final double shoufu, final int loanTime) {
                    String pid_skuid = pid;
                    if (!TextUtils.isEmpty(sku)) {
                        pid_skuid = pid + "." + sku;
                    }
                    final String finalPid_skuid = pid_skuid;
                    new Handler().postDelayed(new Runnable() {     //延迟0.2秒（不延迟操作跳不过去）
                        @Override
                        public void run() {
                            OrderConfirmationFragment.jumpIn((AppCompatActivity) getActivity(), finalPid_skuid + "", goodsNumber + "", mGoodDetail,shoufu,loanTime);
                        }
                    }, 200);
                }
            });
        }
    }
    //绑定点点
    private void bindTips() {
        if (llContainerDotGoodsDetail != null) {
            llContainerDotGoodsDetail.removeAllViews();//如果不移除所有的原来imageView。屏幕滑动到底部在滑上来在复用的时候轮播图的点点会越来越多
        }

        for (int i = 0; i < mListPicture.size(); i++) {
            if (getActivity() != null) {
                ImageView imageView = new ImageView(getActivity().getApplicationContext());
                imageView.setLayoutParams(new ViewGroup.LayoutParams(10, 10));
                mListTips.add(imageView);
                if (i == 0) {
                    mListTips.get(i).setBackgroundResource(R.drawable.dot_focused_red);
                } else {
                    mListTips.get(i).setBackgroundResource(R.drawable.dot_unfocused_gray);
                }

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                layoutParams.leftMargin = 5;
                layoutParams.rightMargin = 5;
                if (llContainerDotGoodsDetail != null) {
                    llContainerDotGoodsDetail.addView(imageView, layoutParams);
                }
            }
        }
    }

    //绑定ViewPager数据
    private void bindViewPager() {

        if (vpGoodsdetail != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) vpGoodsdetail.getLayoutParams();
            int x = CommonUtil.getScreenMetrics(getActivity()).x;
            layoutParams.width = x;
            layoutParams.height = CommonUtil.dipTopx(320);
            vpGoodsdetail.setLayoutParams(layoutParams);

            vpGoodsdetail.setInterval(4000);//延迟
            vpGoodsdetail.setCycle(true);
            //设置Adapter
            if (mImagePagerBaseAdapter2 == null) {
                mImagePagerBaseAdapter2 = new ImagePagerBaseAdapter2(this, mListPicture);
            }
            vpGoodsdetail.setAdapter(mImagePagerBaseAdapter2.setInfiniteLoop(true));
            //设置监听，主要是设置点点的背景
            vpGoodsdetail.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    setImageBackground(position % mListPicture.size());
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            vpGoodsdetail.startAutoScroll();
        }
    }
    /**
     * 设置选中的tip的背景
     *
     * @param selectItems
     */
    private void setImageBackground(int selectItems) {
        for (int i = 0; i < mListTips.size(); i++) {
            if (i == selectItems) {
                mListTips.get(i).setBackgroundResource(R.drawable.dot_focused_red);
            } else {
                mListTips.get(i).setBackgroundResource(R.drawable.dot_unfocused_gray);
            }
        }
    }

}
