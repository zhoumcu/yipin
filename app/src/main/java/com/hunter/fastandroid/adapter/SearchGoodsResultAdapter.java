package com.hunter.fastandroid.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hunter.fastandroid.R;
import com.hunter.fastandroid.dao.Goods;
import com.hunter.fastandroid.ui.fragment.GoodsDetailFragment;
import com.hunter.fastandroid.ui.fragment.OrderConfirmationFragment;
import com.hunter.fastandroid.ui.fragment.SearchGoodsResultFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 搜索商品结果   适配器  B11
 * Created by on 2015/11/11.
 */
public class SearchGoodsResultAdapter extends RecyclerView.Adapter<SearchGoodsResultAdapter.ViewHolder> {

    public static final int REQUEST_ORDER = 50001;//商品清单
    private Context context;
    private SearchGoodsResultFragment fragment;
    private LayoutInflater mLayoutInflater;
    private List<Goods> mListSearchResult;

    public SearchGoodsResultAdapter(SearchGoodsResultFragment fragment, List<Goods> mListSearchResult) {
        this.fragment = fragment;
        this.mListSearchResult = mListSearchResult;
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
        View view = mLayoutInflater.inflate(R.layout.item_search_goods_result, parent, false);
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
        final Goods goods = mListSearchResult.get(position);
        if (goods != null) {
            if (null != goods.getPhoto()) {
                holder.ivHeadPerson.setImageURI(Uri.parse(goods.getPhoto()));
//                bitmapUtils.display(holder.ivHeadPerson, goods.getLogo());
            }
            holder.tvGoodsNameSearchResult.setText(goods.getName());//产品名称
//        holder.tvGoodsNameSearchResult.setText(mListSearchResult.get(position).getSubtitle());//副标题
            holder.tvGoodsOldPriceSearchResult.setText(goods.getPrice1());//
            holder.tvGoodsOldPriceSearchResult.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//删除线
            holder.tvCarriageSearchResult.setText("包邮");
//            holder.tvGoodsNewPriceSearchResult.setText(goods.getPrice());//最终价,每期价格
//            holder.tvGoodsNewPriceSearchResult.setText(goods.getFenqi_price()+"元");//最终价,每期价格
            if (mListSearchResult.get(position).getIs_instalment() == 1) {//等于1为分期
                holder.tvGoodsNewPriceSearchResult.setText("首付￥"+mListSearchResult.get(position).getShoufu());//改成了首付
                holder.tvFenqinum.setText("x"+mListSearchResult.get(position).getFenqi()+"期");
            } else {//0为全额
                holder.tvGoodsNewPriceSearchResult.setText("全额￥"+mListSearchResult.get(position).getPrice());
            }
        }


        // 跳转到确认订单页面( 2015.12.18去掉todo)
        holder.tvImmediatelyPaySearchResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (!CommonUtils.isLogin()) {
//                    CommonUtils.loginIn(fragment, null, REQUEST_ORDER);
//                    return;
//                }

                if (goods.getSku_attr().equals("")) {
                    OrderConfirmationFragment.jumpIn((AppCompatActivity)fragment.getActivity(), goods.getProduct_id(), "1");
                } else {
                    GoodsDetailFragment.jumpIn((AppCompatActivity) fragment.getActivity(), goods.getProduct_id());
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return mListSearchResult == null ? 0 : mListSearchResult.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //        @Optional
//        @Bind(R.id.iv_head_person)
//        ImageView ivHeadPerson;
        @Bind(R.id.iv_head_person)
        SimpleDraweeView ivHeadPerson;
        @Bind(R.id.tv_goods_name_search_result)
        TextView tvGoodsNameSearchResult;
        @Bind(R.id.tv_goods_old_price_search_result)
        TextView tvGoodsOldPriceSearchResult;
        @Bind(R.id.tv_carriage_search_result)
        TextView tvCarriageSearchResult;
        @Bind(R.id.tv_goods_new_price_search_result)
        TextView tvGoodsNewPriceSearchResult;
        @Bind(R.id.tx_fenqinum)
        TextView tvFenqinum;
        @Bind(R.id.tv_immediately_pay_search_result)
        TextView tvImmediatelyPaySearchResult;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
