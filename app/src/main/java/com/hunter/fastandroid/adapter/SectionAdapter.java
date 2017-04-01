package com.hunter.fastandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.hunter.fastandroid.dao.GoodsBrand;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.zhiao.baselib.custom.weight.sidebar.MultiItemCommonAdapter;
import cn.zhiao.baselib.custom.weight.sidebar.MultiItemTypeSupport;
import cn.zhiao.baselib.custom.weight.sidebar.RecyclerViewHolder;
import cn.zhiao.baselib.custom.weight.sidebar.support.SectionSupport;
import cn.zhiao.baselib.utils.L;
import cn.zhiao.baselib.utils.Pinyin;


/**
 * Created by zhy on 16/4/9.
 */
public abstract class SectionAdapter<T> extends MultiItemCommonAdapter<T> {
    private SectionSupport mSectionSupport;
    private static final int TYPE_SECTION = 0;
    private LinkedHashMap<String, Integer> mSections;

    private MultiItemTypeSupport<T> headerItemTypeSupport;

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position, null);
    }

    final RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver()
    {
        @Override
        public void onChanged()
        {
            super.onChanged();
            findSections();
        }
    };

    public SectionAdapter(Context context, int layoutId, List<T> datas, SectionSupport sectionSupport) {
        this(context, layoutId, null, datas, sectionSupport);
    }

    public SectionAdapter(Context context, MultiItemTypeSupport multiItemTypeSupport, List<T> datas, SectionSupport sectionSupport) {
        this(context, -1, multiItemTypeSupport, datas, sectionSupport);
    }

    public SectionAdapter(Context context, int layoutId, MultiItemTypeSupport multiItemTypeSupport, List<T> datas, SectionSupport sectionSupport) {
        super(context, datas, multiItemTypeSupport);
        mLayoutId = layoutId;
        initMulitiItemTypeSupport(layoutId, multiItemTypeSupport);
        mMultiItemTypeSupport = headerItemTypeSupport;
        mSectionSupport = sectionSupport;
        mSections = new LinkedHashMap<>();
        findSections();
        registerAdapterDataObserver(observer);
    }

    private void initMulitiItemTypeSupport(int layoutId, final MultiItemTypeSupport multiItemTypeSupport) {
        if (layoutId != -1)
        {
            headerItemTypeSupport = new MultiItemTypeSupport<T>()
            {
                @Override
                public int getLayoutId(int itemType)
                {
                    if (itemType == TYPE_SECTION)
                        return mSectionSupport.sectionHeaderLayoutId();
                    else
                        return mLayoutId;
                }

                @Override
                public int getItemViewType(int position, T o)
                {
                    int positionVal = getIndexForPosition(position);
                    return mSections.values().contains(position) ?
                            TYPE_SECTION :
                            1;
                }
            };
        } else if (multiItemTypeSupport != null) {
            headerItemTypeSupport = new MultiItemTypeSupport<T>()
            {
                @Override
                public int getLayoutId(int itemType)
                {
                    if (itemType == TYPE_SECTION)
                        return mSectionSupport.sectionHeaderLayoutId();
                    else
                        return multiItemTypeSupport.getLayoutId(itemType);
                }

                @Override
                public int getItemViewType(int position, T o)
                {
                    int positionVal = getIndexForPosition(position);
                    return mSections.values().contains(position) ?
                            TYPE_SECTION :
                            multiItemTypeSupport.getItemViewType(positionVal, o);
                }
            };
        } else {
            throw new RuntimeException("layoutId or MultiItemTypeSupport must set one.");
        }

    }

    @Override
    protected boolean isEnabled(int viewType) {
        if (viewType == TYPE_SECTION)
            return false;
        return super.isEnabled(viewType);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        unregisterAdapterDataObserver(observer);
    }

    public void findSections() {
        int n = mDatas.size();
        int nSections = 0;
        mSections.clear();

        for (int i = 0; i < n; i++) {
            String sectionName = mSectionSupport.getTitle(mDatas.get(i));
            String key= Pinyin.pinying(sectionName).substring(0,1);
            L.i("adapter--------------------->"+sectionName+key);
            if (!mSections.containsKey(key))
            {
                mSections.put(key, i + nSections);
                nSections++;
            }
        }

    }


    @Override
    public int getItemCount()
    {
        return super.getItemCount() + mSections.size();
    }

    public int getIndexForPosition(int position) {
        int nSections = 0;

        Set<Map.Entry<String, Integer>> entrySet = mSections.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet)
        {
            if (entry.getValue() < position)
            {
                nSections++;
            }
        }
        return position - nSections;
    }

    /*选中的是哪一条*/
    public int getPositionForSection(int section) {
        for (int i = 0; i < mDatas.size(); i++) {
            GoodsBrand b = (GoodsBrand) mDatas.get(i);
            String l = ( b.getName().contains("/")?b.getName().substring(0,b.getName().indexOf("/")):b.getName()).substring(0, 1);
            L.i("截取的字符串---->l="+l);
            char firstChar = l.toUpperCase().charAt(0);
            if (firstChar == section) {
                Set<Map.Entry<String, Integer>> entrySet = mSections.entrySet();
                int j=0;
                for (Map.Entry<String, Integer> entry : entrySet)
                {
                    L.i("组合里的字符串---->entry="+entry.getKey());
                    if (entry.getKey().charAt(0)<firstChar) {
                        for(int k=0;k<entry.getValue();k++){
                        j++;
                        }
                    }
                }
                return i+j;
            }
        }
        return -1;
    }
    @Override
    protected int getPosition(RecyclerView.ViewHolder viewHolder)
    {
        return getIndexForPosition(viewHolder.getAdapterPosition());
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position)
    {
        position = getIndexForPosition(position);
        if (holder.getItemViewType() == TYPE_SECTION) {
            String sectionName = mSectionSupport.getTitle(mDatas.get(position));
            String key=Pinyin.pinying(sectionName).substring(0,1);
            holder.setText(mSectionSupport.sectionTitleTextViewId(), key);
            return;
        }
        super.onBindViewHolder(holder, position);
    }


}
