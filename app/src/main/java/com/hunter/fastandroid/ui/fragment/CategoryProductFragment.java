package com.hunter.fastandroid.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hunter.fastandroid.R;
import com.jude.easyrecyclerview.EasyRecyclerView;

import butterknife.Bind;
import cn.zhiao.baselib.base.BaseFragment;


/**
 * 分类或者品牌筛选出来的产品
 * Created by kuyue on 2016/5/25.
 */
public class CategoryProductFragment extends BaseFragment  {

    public static String TAG = CategoryProductFragment.class.getSimpleName();
    public static String SEARCH_GOODS_BY_CID = "/product/list"; //商品列表
    public static String SEARCH_GOODS_BRAND_ID = "/brand/list"; //商品品牌列表
    public static String ARG = "key";
    public static String ARG_NAME = "name";
    public static String CID = "cid";
    public static String BID = "bid";
    public static int ROWNUMBER = 2;
    public static int ROWNUMBER_POPWINDOW = 2;
    public static int DATA_NUMBER = 10;

    @Bind(R.id.prrcv_search_goods_result)
    EasyRecyclerView prrcvSearchGoodsResult;
    @Bind(R.id.tv_brand_category_result)
    TextView tvBrandCategoryResult;
    @Bind(R.id.pb)
    RelativeLayout pb;
    @Bind(R.id.rl_empty)
    RelativeLayout rlEmpty;

    private View viewMenu = null;
    private MenuItem item = null;

    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    private String key = "";  //点击商品名字
    private String cid = "";  //cid
    private String brandId = "";
    private int page = 1;//页数
    private int psize = DATA_NUMBER;//每页个数
    private boolean isRefresh = false;

    private String name;

    public CategoryProductFragment() {

    }

    @SuppressLint("ValidFragment")
    public CategoryProductFragment(String cid) {
        this.cid = cid;
    }

    public static CategoryProductFragment newInstance (String space_id, String name) {
        CategoryProductFragment shoppingVipListFragment = new CategoryProductFragment();

        Bundle bundle = new Bundle();
        bundle.putString(ARG, space_id);
        bundle.putString(ARG_NAME, name);
        shoppingVipListFragment.setArguments(bundle);

        return shoppingVipListFragment;
    }

    public static CategoryProductFragment newInstance (String key, String cid, String bid) {
        CategoryProductFragment searchGoodsResultFragment = new CategoryProductFragment();

        Bundle bundle = new Bundle();
        bundle.putString(ARG_NAME, key);
        bundle.putString(ARG, cid);
        bundle.putString(BID, bid);
        searchGoodsResultFragment.setArguments(bundle);

        return searchGoodsResultFragment;
    }

    public static void jumpIn (AppCompatActivity ac, String space_id, String name) {
        FragmentManager fragmentmanager = ac.getSupportFragmentManager();
        Fragment fragment = CategoryProductFragment.newInstance(space_id,name);
        fragmentmanager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment, CategoryProductFragment.TAG)
                .commitAllowingStateLoss();
    }

    public static void jumpIn (AppCompatActivity ac, String key, String cid, String bid) {
        Fragment fragment = CategoryProductFragment.newInstance(key,cid,bid);

        FragmentManager fragmentmanager = ac.getSupportFragmentManager();
        fragmentmanager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment, CategoryProductFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_search_goods_result2;
    }

    @Override
    public void initView() {

    }

    @Override
    protected void initPresenter() {

    }


}
