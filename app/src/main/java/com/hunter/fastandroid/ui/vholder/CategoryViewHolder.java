package com.hunter.fastandroid.ui.vholder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hunter.fastandroid.R;
import com.hunter.fastandroid.dao.Category;
import com.hunter.fastandroid.ui.activity.ShoppingCacheActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.zhiao.baselib.base.BaseActivity;
import cn.zhiao.baselib.base.BaseListFragment;
import cn.zhiao.baselib.utils.CommonUtil;

/**
 * author：Administrator on 2016/12/8 14:42
 * company: xxxx
 * email：1032324589@qq.com
 */
public class CategoryViewHolder extends BaseViewHolder<Category> {



    @Bind(R.id.sdv_head_band)
    SimpleDraweeView sdvHeadBand;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.gridLayout)
    GridLayout gridLayout;
    private BaseActivity baseAcitivity;
    private Context context;
    private BaseListFragment.DataAdapter mCategoryAdapter;
    private LinearLayoutManager mLinearLayoutManagerCategory;
    private List<Category> datas;
    private List<Category> datasAll;

    public CategoryViewHolder(Context context, BaseActivity baseActivity, BaseListFragment<Category>.DataAdapter mCategoryAdapter, List<Category> datas, List<Category> datasAll,
                              LinearLayoutManager mLinearLayoutManagerCategory, ViewGroup parent) {
        super(parent, R.layout.item_category_view_pager_adapter);
        this.context = context;
        this.baseAcitivity = baseActivity;
        this.datas = datas;
        this.datasAll = datasAll;
        this.mCategoryAdapter = mCategoryAdapter;
        this.mLinearLayoutManagerCategory = mLinearLayoutManagerCategory;
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(final Category category) {
        super.setData(category);
        tvName.setText(category.getName());
        if (category.getUrl()!=null) {
            sdvHeadBand.setImageURI(Uri.parse(category.getUrl()));
        }

        gridLayout.removeAllViews();
//        if (!category.isFirst()) {
        TextView[] textViews;
        int row;// 行数
        int column = 3;// 列数为3

        List<Category> subDatas = new ArrayList<>();
        setSubData(subDatas,category.getCid());
        int sum = subDatas.size();

        if (sum!=0) {
            int temp = sum/column;
            if (sum % 3 == 0) {// 能整除
                row = temp;
            } else {// 不能整除
                row = temp+1;
            }
            // 初始化Textview数据
            textViews = new TextView[sum];
//                int w = CommonUtil.dipTopx(120);
            Log.e("CategoryViewHolder","屏幕宽度："+ CommonUtil.getDeviceWidth(context));
            int w = (CommonUtil.getDeviceWidth(context)/3);
            Log.e("CategoryViewHolder","每个宽度--："+w);
            int h = CommonUtil.dipTopx(44);
            // 初始化布局，并生成
            gridLayout.setRowCount(row);// 设置行数
            gridLayout.setColumnCount(column);// 设置列数
            GridLayout.LayoutParams layoutParams;

            for (int i = 0; i < sum; i++) {// 循环向网络布局添加TV
                textViews[i] = new TextView(context);
                textViews[i].setText(subDatas.get(i).getName());
//              textViews[i].setLayoutParams(new ViewGroup.LayoutParams(w, h));
                layoutParams = new GridLayout.LayoutParams();//GridLayout.spec((i/3)+1),GridLayout.spec(i%3)
                layoutParams.width = w;
                layoutParams.height = h;
                layoutParams.setGravity(ViewGroup.LayoutParams.MATCH_PARENT);
                textViews[i].setLayoutParams(layoutParams);
                textViews[i].setGravity(Gravity.CENTER);
                textViews[i].setTextColor(Color.parseColor("#3b3e40"));
                textViews[i].setTextSize(TypedValue.COMPLEX_UNIT_DIP,13);
                textViews[i].setTag(subDatas.get(i).getCid());
                gridLayout.addView(textViews[i]);
                textViews[i].setOnClickListener(myOnClickListener);
            }
        }
        if (category.isShow()) {
            gridLayout.setVisibility(View.VISIBLE);
        } else {
            gridLayout.setVisibility(View.GONE);
        }
        sdvHeadBand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (category.isShow()) {//已经显示了，则当前隐藏
                    category.setShow(false);
                    mCategoryAdapter.notifyDataSetChanged();
                } else {//当前为隐藏，则显示当前，并将其他的隐藏
                    setAllGone();
                    category.setShow(true);
                    mCategoryAdapter.notifyDataSetChanged();
                    sdvHeadBand.getY();
                    mLinearLayoutManagerCategory.scrollToPositionWithOffset(indextInData(category.getName()),(int)sdvHeadBand.getY());
                }
            }
        });
    }
    // 监听类
    View.OnClickListener myOnClickListener = new View.OnClickListener() {// 用户点击相关分类，跳转到不同的分类页面
        @Override
        public void onClick(View v) {
            TextView tempTv = (TextView) v;
            String tag = (String) tempTv.getTag();
//            Shareutl.shortToast("您点击的分类:"+tempTv.getText().toString()+"cid"+tag);
            Intent intent1 = new Intent(context, ShoppingCacheActivity.class);
            intent1.putExtra("type", "8");
//            intent1.putExtra("space_id", tag+"");
//            intent1.putExtra("name", tempTv.getText().toString());
            context.startActivity(intent1);
        }
    };
    /**
     * 设置分类
     */
    private void setSubData(List<Category> subDatas,String parentId) {
        if (subDatas.size() > 0) {
            subDatas.clear();
        }
        for (Category item : datasAll) {
            if (item.getParent_id().equals(parentId)) {
                subDatas.add(item);
            }
        }
    }

    private void setAllGone() {
        for (Category item : datas) {
            item.setShow(false);
        }
    }

    private int indextInData(String name) {
        for (int i =0;i<datas.size();i++) {
            if (name.equals(datas.get(i).getName())) {
                return i;
            }
        }
        return 0;
    }
}
