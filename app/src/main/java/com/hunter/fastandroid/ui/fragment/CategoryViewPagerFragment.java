package com.hunter.fastandroid.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hunter.fastandroid.R;
import com.hunter.fastandroid.dao.Category;
import com.hunter.fastandroid.interfaces.presenter.CategoryPresenterImpl;
import com.hunter.fastandroid.interfaces.view.CategoryView;
import com.hunter.fastandroid.ui.vholder.CategoryViewHolder;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.zhiao.baselib.base.BaseListFragment;

/**
 * author：Administrator on 2017/4/1 09:24
 * company: xxxx
 * email：1032324589@qq.com
 */
public class CategoryViewPagerFragment extends BaseListFragment<Category> implements CategoryView{


    @Bind(R.id.tv_brand_category_result)
    TextView tvBrandCategoryResult;
    @Bind(R.id.prrcv_search_goods_result)
    EasyRecyclerView prrcvSearchGoodsResult;
    @Bind(R.id.rl_empty)
    RelativeLayout rlEmpty;
    @Bind(R.id.pb)
    RelativeLayout pb;
    private DataAdapter mCategoryAdapter;
    private LinearLayoutManager mLinearLayoutManagerCategory;
    private List<Category> datas = new ArrayList<>();
    private List<Category> datasAll = new ArrayList<>();
    private CategoryPresenterImpl presenter;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_category_view_pager;
    }

    @Override
    public void initView() {
        mLinearLayoutManagerCategory = new LinearLayoutManager(getActivity());
        mLinearLayoutManagerCategory.setOrientation(LinearLayoutManager.VERTICAL);
        prrcvSearchGoodsResult.setLayoutManager(mLinearLayoutManagerCategory);
        mCategoryAdapter = new DataAdapter(getContext());
        prrcvSearchGoodsResult.setAdapter(mCategoryAdapter);
        presenter.getCategorys("");
    }

    @Override
    protected void initPresenter() {
        presenter = new CategoryPresenterImpl(getContext(),this);
    }

    @Override
    public BaseViewHolder<Category> getViewHolder(ViewGroup parent, int viewType) {
        return new CategoryViewHolder(getContext(),getBaseActivity(),mCategoryAdapter,datas,datasAll,mLinearLayoutManagerCategory,parent);
    }

    @Override
    public void getCategorys(List<Category> model) {
        datas = model;
        datasAll = model;
        mCategoryAdapter.addAll(model);
        mCategoryAdapter.notifyDataSetChanged();
    }
}
