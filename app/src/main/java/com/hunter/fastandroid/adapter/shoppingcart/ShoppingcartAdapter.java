package com.hunter.fastandroid.adapter.shoppingcart;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hunter.fastandroid.R;
import com.hunter.fastandroid.dao.ShoppingCartGoods;
import com.hunter.fastandroid.ui.fragment.ShoppingCartFragment;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 购物车  适配器
 *  2015.12.11加入购物车真实数据，修改全选/全不选的逻辑，以及传值到Fragment的值
 *  2015.12.12加入减少/增加商品数量，以及删除商品,保持fragment的数据同步着更新
 *  2015.12.29加入商品的skuid，在更新，删除，增加商品的地方
 * Created by win7 on 2015/11/16.
 */
public class ShoppingcartAdapter extends RecyclerView.Adapter<ShoppingcartAdapter.ViewHolder> {

    private static String URL_UPDATA_SHOPPING_CART = "/cart/update";
    private static String URL_REMOVE_SHOPPING_CART = "/cart/remove";


    private Context context;
    private Fragment fragment;
    private LayoutInflater mLayoutInflater;
    private List<ShoppingCartGoods> mOrder;

    private boolean checkAll = false;//是否全选
    private int checkNumber = 0;//选中的商品种类
    private int totalNumber = 0;//商品数量
    private double totalPrice = 0;//选中的商品价钱（所有）
    private double shoufuPay = 0; //首付之和：所选商品全额+所选商品首付
    private double monthPay = 0;  //月供之和：所选商品月供之和

    private SparseArray<Boolean> isCheckArr;//做标记，哪些被选中

    public SparseArray<Boolean> getIsCheckArr () {
        return isCheckArr;
    }

    public void setIsCheckArr (SparseArray<Boolean> isCheckArr) {
        this.isCheckArr = isCheckArr;
    }

    public int getCheckNumber () {
        return checkNumber;
    }

    public void setCheckNumber (int checkNumber) {
        this.checkNumber = checkNumber;
    }

    public void setCheckAll (boolean checkAll) {
        this.checkAll = checkAll;
    }

    /**
     * 设置全选/全不选
     */
    public void setCheckALL (boolean checkall) {
        isCheckArr.clear();
        for (int i = 0; i < mOrder.size(); i++) {
            isCheckArr.put(i, checkall);
        }
        notifyDataSetChanged();
    }

    public ShoppingcartAdapter(Fragment fragment, List<ShoppingCartGoods> mOrder) {
        this.fragment = fragment;
        this.mOrder = mOrder;
        this.context = fragment.getActivity();
        mLayoutInflater = LayoutInflater.from(context);

        isCheckArr = new SparseArray<>();
    }

    /**
     * ItemClick的回调接口
     */
    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener (OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_shopping_cart, parent, false);
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
    public void onBindViewHolder (final ViewHolder holder, final int position) {

        final boolean isCheck = isCheckArr.get(position, false);

        holder.ckChooseShoppingCart.setChecked(isCheck);

        bindData(holder, position);
        initEvnent(holder, position);
    }

    //绑定数据
    private void bindData (ViewHolder holder, int position) {

        ShoppingCartGoods shoppingCartGoods = mOrder.get(position);

        //名称
        holder.tvGoodsNameShoppingCart.setText(shoppingCartGoods.getName());
        //描述
        holder.tvGoodsInfoShoppingCart.setText(shoppingCartGoods.getSummary());
//        //总价钱
//        String price = context.getResources().getString(R.string.money_us_dollar) + Double.valueOf(shoppingCartGoods.getPrice()) * Integer.valueOf(shoppingCartGoods.getQuantity());
//        holder.tvGoodsPriceShoppingCart.setText(price);
        //数量
        String number = context.getResources().getString(R.string.X) + shoppingCartGoods.getQuantity();
        holder.tvGoodsNumberShoppingCart.setText(number);
        //图片
        holder.ivGoodsPictureShoppingCart.setImageURI(Uri.parse(shoppingCartGoods.getThumb()));

        DecimalFormat decimalFormat = new DecimalFormat(".00");
        //物品单价
        String price = context.getResources().getString(R.string.money_us_dollar) + decimalFormat.format(Double.valueOf(shoppingCartGoods.getPrice()));
        holder.tvGoodsPriceShoppingCart.setText(price);
        //月供分期
        if(shoppingCartGoods.getInstalments()>0) {
            String shoufu_monthpay = "首付："+decimalFormat.format(Double.valueOf(shoppingCartGoods.getShoufu())* Integer.valueOf(shoppingCartGoods.getQuantity())) + "元，" + decimalFormat.format(shoppingCartGoods.getmonthpay()* Integer.valueOf(shoppingCartGoods.getQuantity())) + "x" + shoppingCartGoods.getInstalments()+"期";
            holder.tvShoufuMonthpay.setText(shoufu_monthpay);
        }else{
            String totalPay = "全额："+decimalFormat.format(Double.valueOf(shoppingCartGoods.getPrice()) * Integer.valueOf(shoppingCartGoods.getQuantity()))+"元";
            holder.tvShoufuMonthpay.setText(totalPay);
        }
    }

    //点击监听
    private void initEvnent (final ViewHolder holder, final int position) {

        final ShoppingCartGoods shoppingCartGoods = mOrder.get(position);

        //是否选中
        holder.ckChooseShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                boolean isCheck = holder.ckChooseShoppingCart.isChecked();
                isCheckArr.put(position, isCheck);
                checkNumber = 0;
                totalNumber = 0;
                totalPrice = 0;
                shoufuPay = 0;
                monthPay = 0;

                notifyDataSetChanged();

                //计算选中商品总数
                for (int i = 0; i < mOrder.size(); i++) {
                    ShoppingCartGoods shoppingCartGoods = mOrder.get(i);

                    Boolean aBoolean = isCheckArr.get(i);
                    if (aBoolean != null) {
                        if (aBoolean) {
                            checkNumber++;
                            totalNumber += Integer.valueOf(shoppingCartGoods.getQuantity());
                            //totalPrice += Integer.valueOf(shoppingCartGoods.getQuantity()) * Double.valueOf(shoppingCartGoods.getPrice());

                            if(shoppingCartGoods.getInstalments() > 0){
                                totalPrice += Integer.valueOf(shoppingCartGoods.getQuantity()) * Double.valueOf(shoppingCartGoods.getShoufu());
                                shoufuPay += Integer.valueOf(shoppingCartGoods.getQuantity()) * Double.valueOf(shoppingCartGoods.getShoufu());
                                monthPay += Integer.valueOf(shoppingCartGoods.getQuantity()) * shoppingCartGoods.getmonthpay();
                            }else{
                                totalPrice += Integer.valueOf(shoppingCartGoods.getQuantity()) * Double.valueOf(shoppingCartGoods.getPrice());
                            }
                        }
                    }
                }

                //如果所有item被选中，则全选会被选中
                if (checkNumber == mOrder.size()) {
                    ((ShoppingCartFragment) fragment).ckCheckAll.setChecked(true);
                } else {
                    ((ShoppingCartFragment) fragment).ckCheckAll.setChecked(false);
                }

                //注意这个数量一定要你按商品数量   需要重新计算，不能用checkNumber(一种商品可能选择了几件)  （已去掉TODO提示）
                ((ShoppingCartFragment) fragment).setGoodsNumber(totalNumber);
                //这里缺少设置价钱,注意：这里一定要算  总的商品的价钱(一种商品可能选择了几件)   （已去掉TODO提示）
                //((ShoppingCartFragment) fragment).setGoodsPrice(totalPrice, "不含运费");
                ((ShoppingCartFragment) fragment).setGoodsPrice(totalPrice);
                ((ShoppingCartFragment) fragment).setTvGoodsShoufuAndCarriage(shoufuPay);
                ((ShoppingCartFragment) fragment).setTvGoodsMonthpayAndCarriage(monthPay);

                holder.ckChooseShoppingCart.setChecked(isCheck);
            }
        });

        //编辑
        holder.tvEditGoodsShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                String state = holder.tvEditGoodsShoppingCart.getText().toString();

                if (state.equals(fragment.getActivity().getResources().getString(R.string.tv_goods_edit))) {
                    holder.llGoodsInfoShoppingCart.setVisibility(View.GONE);
                    holder.llEditShoppingCart.setVisibility(View.VISIBLE);
                    holder.tvEditGoodsShoppingCart.setText(fragment.getActivity().getResources().getString(R.string.tv_goods_finish));

                    holder.goodsNumber.setText(shoppingCartGoods.getQuantity() + "");
                } else if (state.equals(fragment.getActivity().getResources().getString(R.string.tv_goods_finish))) {
                    holder.llGoodsInfoShoppingCart.setVisibility(View.VISIBLE);
                    holder.llEditShoppingCart.setVisibility(View.GONE);
                    holder.tvEditGoodsShoppingCart.setText(fragment.getActivity().getResources().getString(R.string.tv_goods_edit));
                }
            }
        });

        //减少（已删除）
        holder.cutDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                if (Integer.valueOf(shoppingCartGoods.getQuantity()) <= 1) {
                    Toast.makeText(context, "商品数不能小于1",Toast.LENGTH_SHORT).show();
                    return;
                }
                String pid = shoppingCartGoods.getProduct_id();
                if (Integer.valueOf(shoppingCartGoods.getSku_id())!=0) {
                    pid = pid + "." + shoppingCartGoods.getSku_id();
                }
                String quantity = (Integer.valueOf(shoppingCartGoods.getQuantity()) - 1) + "";
                updata(holder, pid, quantity);
            }
        });

        //增加
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                String pid = shoppingCartGoods.getProduct_id();
                if (Integer.valueOf(shoppingCartGoods.getSku_id())!=0) {
                    pid = pid + "." + shoppingCartGoods.getSku_id();
                }
                String quantity = (Integer.valueOf(shoppingCartGoods.getQuantity()) + 1) + "";
                updata(holder, pid, quantity);
            }
        });

        //移除
        holder.tvRemoveGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                String urlRemove = URL_REMOVE_SHOPPING_CART;
                HashMap<String, String> params = new HashMap<String, String>();
                String pid = shoppingCartGoods.getProduct_id();
                if (Integer.valueOf(shoppingCartGoods.getSku_id())!=0) {
                    pid = pid + "." + shoppingCartGoods.getSku_id();
                }
            }
        });

        /**
         * 监听item
         */
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    mOnItemClickListener.OnItemClick(holder.itemView, position);
                }
            });
        }
    }

    /**
     * 删除，增加   （更新数据）
     *
     * @param pid
     * @param quantity
     */
    private void updata (final ViewHolder holder, String pid, final String quantity) {
        String urlUpdata = URL_UPDATA_SHOPPING_CART;

    }

    @Override
    public int getItemCount () {
        return mOrder == null ? 0 : mOrder.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.ck_choose_shopping_cart)
        CheckBox ckChooseShoppingCart;
        @Bind(R.id.iv_goods_picture_shopping_cart)
        ImageView ivGoodsPictureShoppingCart;
        @Bind(R.id.tv_goods_name_shopping_cart)
        TextView tvGoodsNameShoppingCart;
        @Bind(R.id.tv_edit_goods_shopping_cart)
        TextView tvEditGoodsShoppingCart;
        @Bind(R.id.tv_goods_info_shopping_cart)
        TextView tvGoodsInfoShoppingCart;
        @Bind(R.id.tv_goods_price_shopping_cart)
        TextView tvGoodsPriceShoppingCart;
        @Bind(R.id.tv_goods_number_shopping_cart)
        TextView tvGoodsNumberShoppingCart;
        //点编辑隐藏这个布局
        @Bind(R.id.ll_goods_info_shopping_cart)
        LinearLayout llGoodsInfoShoppingCart;

        @Bind(R.id.cut_down)
        ImageView cutDown;
        @Bind(R.id.goods_number)
        TextView goodsNumber;
        @Bind(R.id.add)
        ImageView add;
        @Bind(R.id.tv_remove_goods)
        TextView tvRemoveGoods;
        @Bind(R.id.ll_edit_goods)
        LinearLayout llEditGoods;   //这个无用
        //点编辑显示这个布局
        @Bind(R.id.ll_edit_shopping_cart)
        LinearLayout llEditShoppingCart;

        @Bind(R.id.tv_shoufu_monthpay)
        TextView tvShoufuMonthpay;

        public ViewHolder (View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
