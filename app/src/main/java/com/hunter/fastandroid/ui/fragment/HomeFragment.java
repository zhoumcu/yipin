package com.hunter.fastandroid.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;

import com.hunter.fastandroid.R;
import com.hunter.fastandroid.dao.ShoppingVip;
import com.hunter.fastandroid.interfaces.presenter.IHomePresenterImpl;
import com.hunter.fastandroid.interfaces.view.IHomeView;
import com.hunter.fastandroid.ui.vholder.HomeViewHolder;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.util.List;

import butterknife.Bind;
import cn.zhiao.baselib.base.BaseListFragment;
import cn.zhiao.baselib.custom.TitleBar;
import cn.zhiao.baselib.custom.weight.FullyLinearLayoutManager;

/**
 * author：Administrator on 2017/3/24 14:38
 * company: xxxx
 * email：1032324589@qq.com
 */

public class HomeFragment extends BaseListFragment<ShoppingVip> implements IHomeView {

    @Bind(R.id.recycler)
    EasyRecyclerView recycler;
    @Bind(R.id.title_bar)
    TitleBar titleBar;
    private DataAdapter adapter;
    private IHomePresenterImpl iHomePresenter;

    @Override
    public int getLayoutRes() {
        return R.layout.frg_home;
    }

    @Override
    public void initView() {
        titleBar.setTitle("一品商城");
        //StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        FullyLinearLayoutManager fullyLinearLayoutManager = new FullyLinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false );
        recycler.setLayoutManager(fullyLinearLayoutManager);
        adapter = new DataAdapter(getContext());
        recycler.setAdapter(adapter);
        iHomePresenter.getHomeProductors("");
    }

    @Override
    public void initPresenter() {
        iHomePresenter = new IHomePresenterImpl(getContext(), this);
    }

    @Override
    public void ProdcutorResult(List<ShoppingVip> productses) {
        adapter.addAll(productses);
        adapter.notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder<ShoppingVip> getViewHolder(ViewGroup parent, int viewType) {
        return new HomeViewHolder(getContext(),getBaseActivity(),parent);
    }
}
