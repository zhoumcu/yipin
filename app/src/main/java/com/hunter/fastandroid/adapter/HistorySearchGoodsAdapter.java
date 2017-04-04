package com.hunter.fastandroid.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hunter.fastandroid.R;
import com.hunter.fastandroid.ui.fragment.SearchGoodsFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.zhiao.baselib.utils.L;
import cn.zhiao.baselib.utils.SharedPrefrecesUtils;


/**
 * 历史搜索商品  适配器  B1
 * Created by  on 2015/11/11.
 */
public class HistorySearchGoodsAdapter extends RecyclerView.Adapter<HistorySearchGoodsAdapter.ViewHolder> {

    //历史数据
    private static final int HISTORY_DATA = 1;
    //清空历史数据
    private static final int DELETE_HISTORY_DATA = 2;

    private Context context;
    private SearchGoodsFragment fragment;
    private LayoutInflater mLayoutInflater;
    private List<String> mListHistorySearch;

    public HistorySearchGoodsAdapter(SearchGoodsFragment fragment, List<String> mListHistorySearch) {

        this.fragment = fragment;
        this.mListHistorySearch = mListHistorySearch;
        this.context = fragment.getActivity();
        mLayoutInflater = LayoutInflater.from(context);

    }

    /**
     * ItemClick的回调接口
     */
    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;

        switch (viewType) {
            case HISTORY_DATA:
                view = mLayoutInflater.inflate(R.layout.item_history_search_goods, parent, false);
                break;
            case DELETE_HISTORY_DATA:
                view = mLayoutInflater.inflate(R.layout.item_delete_history_data, parent, false);
                break;
        }

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        L.i("搜索历史-------->mListHistorySearch="+mListHistorySearch.size());
        switch (getItemViewType(position)){
            case HISTORY_DATA:
                /**
                 * 监听item
                 */
                if (mOnItemClickListener != null) {
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mOnItemClickListener.OnItemClick(holder.itemView, position);

                        }
                    });
                }
                holder.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String key= SharedPrefrecesUtils.getStrFromSharedPrefrences(SearchGoodsFragment.HISTORY_KEY, context);
                            String child=mListHistorySearch.get(position);
                        String substring="";
                        if(key.contains(child)) {
                            if(mListHistorySearch.size()==2){
                                clearAll();
                            }
                            if (position == 0) {
                                 substring=key.replace(child+"#","");//key.substring(key.indexOf(child+"#"));
                            }else if(position==mListHistorySearch.size()-1){
                                 substring=key.replace("#"+child,"");//key.substring(0,key.indexOf("#"+child));
                            }else{
                                substring=key.replace(child+"#","");
                            }
                            if(!TextUtils.isEmpty(substring)) {
                                SharedPrefrecesUtils.saveStrToSharedPrefrences(SearchGoodsFragment.HISTORY_KEY,substring,context);
                                mListHistorySearch.clear();
                                fragment.bindHistorySearchData();
                                fragment.notifyHisttoryData();
                            }

                        }
                    }
                });

                holder.tvHistorySearch.setText(mListHistorySearch.get(position).toString());
                break;
            case DELETE_HISTORY_DATA:
                holder.tvDeleteHostorySearchData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearAll();
                    }
                });
                break;
        }
    }

    private void clearAll() {
        SharedPrefrecesUtils.clearStrToSharedPrefrences(SearchGoodsFragment.HISTORY_KEY,context);
        mListHistorySearch.clear();
        fragment.notifyHisttoryData();
    }

    @Override
    public int getItemCount() {
        return mListHistorySearch == null ? 0 : mListHistorySearch.size();
    }

    @Override
    public int getItemViewType(int position) {

        return position == (mListHistorySearch.size() - 1) ? DELETE_HISTORY_DATA : HISTORY_DATA;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Nullable
        @Bind(R.id.tv_history_search)
        TextView tvHistorySearch;
        @Nullable
        @Bind(R.id.tv_delete_hostory_search_data)
        TextView tvDeleteHostorySearchData;
        @Nullable
        @Bind(R.id.dele)
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
