package com.hunter.fastandroid.ui.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hunter.fastandroid.R;
import com.hunter.fastandroid.ui.fragment.AddressManagerFragment;
import com.hunter.fastandroid.ui.fragment.GoodsDetailFragment;
import com.hunter.fastandroid.ui.fragment.OrderFragment;
import com.hunter.fastandroid.ui.fragment.SearchGoodsFragment;
import com.hunter.fastandroid.ui.fragment.ShoppingCartFragment;

import butterknife.Bind;
import cn.zhiao.baselib.base.BaseActivity;

/**
 * author：Administrator on 2017/4/1 16:04
 * company: xxxx
 * email：1032324589@qq.com
 */
public class ShoppingCacheActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.container)
    FrameLayout container;
    @Bind(R.id.iv_category_bootom)
    ImageView ivCategoryBootom;
    @Bind(R.id.tv_category_bootom)
    TextView tvCategoryBootom;
    @Bind(R.id.ll_category_bottom)
    LinearLayout llCategoryBottom;
    @Bind(R.id.iv_per_bootom)
    ImageView ivPerBootom;
    @Bind(R.id.tv_per_bootom)
    TextView tvPerBootom;
    @Bind(R.id.ll_person_bottom)
    LinearLayout llPersonBottom;
    @Bind(R.id.rl_bottom)
    RelativeLayout rlBottom;
    @Bind(R.id.ll_content)
    LinearLayout llContent;
    private String type = "10";//1为进来时为商品详情，2为立即购买，3为订单，4为地址管理，5为邀请好友
    private TextView mTvTitle;
    private String product_id;

    public static ShoppingCacheActivity getInstance() {
        return instance;
    }

    private static ShoppingCacheActivity instance;

    @Override
    public void initView() {
        instance = this;
        setSupportActionBar(toolbar);
        addViewToToolBar();
        //确定显示哪个Fragment
        setLlSelected();
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.shopping_activity_main;
    }

    //设置ToolBar
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void addViewToToolBar() {
        mTvTitle = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.shopping_tv_title_toolbar, null);
        Toolbar.LayoutParams mParams = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
        mTvTitle.setLayoutParams(mParams);
        toolbar.addView(mTvTitle);
    }

    private void setLlSelected() {

        switch (type) {
            case "1"://商品详情
                GoodsDetailFragment.jumpIn(this, product_id);
                break;
            case "2"://立即购买
                break;
            case "3"://订单
                OrderFragment.jumpIn(this);
                break;
            case "4"://地址管理
                AddressManagerFragment.jumpIn(this, true);//true表示从Activity进来，防止从商品详情进来时，点击返回也直接finish掉Activity
                break;
            case "5"://邀请好友
                //InviteFriendsFragment.jumpIn(this);
                break;
            case "6"://专享
                //ShoppingVipListFragment.jumpIn(this, space_id, name);
                break;
            case "7"://搜索
                SearchGoodsFragment.jumpIn(this);
                break;
            case "8"://分类--根据分类筛选出来的产品
                //CategoryProductFragment.jumpIn(this, space_id, name);
                break;
            case "9"://品牌--根据品牌筛选出来的产品
                //CategoryProductFragment.jumpIn(this, name, "", space_id);//这里为品牌id
                break;
            case "10"://购物车
                ShoppingCartFragment.jumpIn(this);
                break;
        }
    }
}
