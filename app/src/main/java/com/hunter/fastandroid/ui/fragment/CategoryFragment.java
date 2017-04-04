package com.hunter.fastandroid.ui.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hunter.fastandroid.R;
import com.hunter.fastandroid.ui.activity.ShoppingCacheActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.zhiao.baselib.base.BaseFragment;

/**
 * author：Administrator on 2017/3/24 14:38
 * company: xxxx
 * email：1032324589@qq.com
 */

public class CategoryFragment extends BaseFragment implements ViewPager.OnPageChangeListener{

    @Bind(R.id.tv_category)
    TextView tvCategory;
    @Bind(R.id.tv_category_line)
    TextView tvCategoryLine;
    @Bind(R.id.tv_brand)
    TextView tvBrand;
    @Bind(R.id.tv_brand_line)
    TextView tvBrandLine;
    @Bind(R.id.iv_search)
    ImageView ivSearch;
    @Bind(R.id.id_vp)
    ViewPager idVp;
    private List<Fragment> mTabContents = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;

    @Override
    public int getLayoutRes() {
        return R.layout.frg_category;
    }

    @Override
    public void initView() {
        CategoryViewPagerFragment fragment = new CategoryViewPagerFragment();
        mTabContents.add(fragment);
        BrandViewPagerFragment fragment1 = new BrandViewPagerFragment();
        mTabContents.add(fragment1);
        mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return mTabContents.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mTabContents.get(position);
            }

            @Override
            public long getItemId(int position) {
                // 获取当前数据的hashCode
                return mTabContents.get(position).hashCode();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                return super.instantiateItem(container, position);
            }

            @Override
            public int getItemPosition(Object object) {
                return PagerAdapter.POSITION_NONE;
            }

        };

        idVp.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        idVp.setOnPageChangeListener(this);
        setCategory();
    }

    @Override
    public void initPresenter() {
    }
    /**
     * 选择分类
     */
    private void setCategory() {
        tvCategory.setSelected(true);
        tvCategoryLine.setSelected(true);
        tvBrand.setSelected(false);
        tvBrandLine.setSelected(false);
    }
    /**
     * 选择品牌
     */
    private void setBrand() {
        tvCategory.setSelected(false);
        tvCategoryLine.setSelected(false);
        tvBrand.setSelected(true);
        tvBrandLine.setSelected(true);
    }
    @OnClick({R.id.tv_category, R.id.tv_brand, R.id.iv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_category:
                setCategory();
                idVp.setCurrentItem(0);
                break;
            case R.id.tv_brand:
                setBrand();
                idVp.setCurrentItem(1);
                break;
            case R.id.iv_search://跳转到搜索页面
                Intent intent = new Intent(getContext(), ShoppingCacheActivity.class);
                intent.putExtra("type", "7");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //设置跳转
        switch (position) {
            case 0:
                setCategory();
                break;
            case 1:
                setBrand();
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
