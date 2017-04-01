package com.hunter.fastandroid.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hunter.fastandroid.R;
import com.hunter.fastandroid.adapter.SectionAdapter;
import com.hunter.fastandroid.dao.GoodsBrand;
import com.hunter.fastandroid.interfaces.presenter.BrandPresenterImpl;
import com.hunter.fastandroid.interfaces.view.BrandView;
import com.hunter.fastandroid.ui.activity.ShoppingCacheActivity;

import java.util.List;

import butterknife.Bind;
import cn.zhiao.baselib.base.BaseFragment;
import cn.zhiao.baselib.base.BaseListFragment;
import cn.zhiao.baselib.custom.weight.sidebar.MultiItemTypeSupport;
import cn.zhiao.baselib.custom.weight.sidebar.OnItemClickListener;
import cn.zhiao.baselib.custom.weight.sidebar.RecyclerViewHolder;
import cn.zhiao.baselib.custom.weight.sidebar.SideBar;
import cn.zhiao.baselib.custom.weight.sidebar.support.SectionSupport;

/**
 * author：Administrator on 2017/4/1 09:27
 * company: xxxx
 * email：1032324589@qq.com
 */
public class BrandViewPagerFragment extends BaseFragment implements BrandView{
    @Bind(R.id.rec)
    RecyclerView rec;
    @Bind(R.id.contact_dialog)
    TextView contactDialog;
    @Bind(R.id.contact_sidebar)
    SideBar contactSidebar;
    private LinearLayoutManager mLinearLayoutManagerCategory;
    private BaseListFragment.DataAdapter mCategoryAdapter;
    private BrandPresenterImpl presenter;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_brandviewpager;
    }

    @Override
    public void initView() {
        contactSidebar.setTextView(contactDialog);
        mLinearLayoutManagerCategory = new LinearLayoutManager(getActivity());
        mLinearLayoutManagerCategory.setOrientation(LinearLayoutManager.VERTICAL);
        rec.setLayoutManager(mLinearLayoutManagerCategory);
        presenter.getBrands("");
    }

    @Override
    protected void initPresenter() {
        presenter = new BrandPresenterImpl(getContext(),this);
    }


    private void setadapter(List<GoodsBrand> goodsBrandList) {
        //rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        //rec.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
        SectionSupport<GoodsBrand> sectionSupport = new SectionSupport<GoodsBrand>() {
            @Override
            public int sectionHeaderLayoutId() {
                return R.layout.contact_header;
            }

            @Override
            public int sectionTitleTextViewId() {
                return R.id.tv_head;
            }

            @Override
            public String getTitle(GoodsBrand goodsBrand) {
                return goodsBrand.getName().substring(0, 1);
            }
        };
        MultiItemTypeSupport<GoodsBrand> multiItemTypeSupport=new MultiItemTypeSupport<GoodsBrand>() {
            @Override
            public int getLayoutId(int itemType) {
                return 0;
            }

            @Override
            public int getItemViewType(int position, GoodsBrand goodsBrand) {
                return 0;
            }
        };
        final SectionAdapter<GoodsBrand> sectionAdapter = new SectionAdapter<GoodsBrand>(getActivity(), R.layout.item_dialog_goodsdetail,multiItemTypeSupport, goodsBrandList, sectionSupport) {
            @Override
            public void convert(RecyclerViewHolder holder, GoodsBrand goodsBrand) {
//                logI("品牌当前--------------------------->"+Constants.SERVER_SHOPPING_PATH+goodsBrand.getLogo());
//                holder.setSimpleDraweeViewResource(R.id.sdv_picture_goodsdetail, Constants.SERVER_SHOPPING_PATH+goodsBrand.getLogo());
                holder.setText(R.id.tv_name_goodsdetail, goodsBrand.getName().contains("/")?goodsBrand.getName().substring(0,goodsBrand.getName().indexOf("/")):goodsBrand.getName());
            }
        };

        sectionAdapter.setOnItemClickListener(new OnItemClickListener<GoodsBrand>() {
            @Override
            public void onItemClick(ViewGroup parent, View view, GoodsBrand goodsBrand, int position) {
                logI("当前点击--------------->"+goodsBrand.getName()+"item--------->"+sectionAdapter.getItemCount());
//                Intent intent1 = new Intent(getContext(), ShoppingCacheActivity.class);
//                intent1.putExtra("type", "9");
//                intent1.putExtra("space_id", goodsBrand.getBrand_id());
//                intent1.putExtra("name", goodsBrand.getName());
//                startActivity(intent1);
                gt(ShoppingCacheActivity.class);

            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, GoodsBrand goodsBrand, int position) {
                return false;
            }

        });
        rec.setAdapter(sectionAdapter);
        contactSidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                logI("当前字符串----------------------->"+s);
                //int position = sectionAdapter.getIndexForPosition(s.charAt(0)-44);
                int position = sectionAdapter.getPositionForSection(s.charAt(0));
                logI("当前触摸点----------------------->"+position);
                //if (position != -1) rec.scrollToPosition(position);
                if (position != -1) rec.smoothScrollToPosition(position);
            }
        });
    }

    @Override
    public void getBrands(List<GoodsBrand> model) {
        setadapter(model);
    }
}
