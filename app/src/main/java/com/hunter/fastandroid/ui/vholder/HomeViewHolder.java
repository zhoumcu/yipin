package com.hunter.fastandroid.ui.vholder;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.hunter.fastandroid.R;
import com.hunter.fastandroid.dao.ShoppingVip;
import com.hunter.fastandroid.ui.activity.FoodDetailActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.zhiao.baselib.base.BaseActivity;

/**
 * author：Administrator on 2016/12/8 14:42
 * company: xxxx
 * email：1032324589@qq.com
 */
public class HomeViewHolder extends BaseViewHolder<ShoppingVip> {

    private BaseActivity baseAcitivity;
    private Context context;
    @Bind(R.id.iv_head_band)
    SimpleDraweeView ivHeadBand;
    @Bind(R.id.tv_goods_new_price_search_result)
    TextView tvGoodsNewPriceSearchResult;
    @Bind(R.id.tx_fenqinum)
    TextView txFenqinum;
    @Bind(R.id.tv_immediately_pay_search_result)
    TextView tvImmediatelyPaySearchResult;
    @Bind(R.id.iv_head_person)
    SimpleDraweeView ivHeadPerson;
    @Bind(R.id.tv_goods_name_search_result)
    TextView tvGoodsNameSearchResult;
    @Bind(R.id.tv_goods_old_price_search_result)
    TextView tvGoodsOldPriceSearchResult;
    @Bind(R.id.tv_carriage_search_result)
    TextView tvCarriageSearchResult;
    @Bind(R.id.ll_product)
    LinearLayout llProduct;
    @Bind(R.id.tv_goods_new_price_search_result1)
    TextView tvGoodsNewPriceSearchResult1;
    @Bind(R.id.tx_fenqinum1)
    TextView txFenqinum1;
    @Bind(R.id.tv_immediately_pay_search_result1)
    TextView tvImmediatelyPaySearchResult1;
    @Bind(R.id.iv_head_person1)
    SimpleDraweeView ivHeadPerson1;
    @Bind(R.id.tv_goods_name_search_result1)
    TextView tvGoodsNameSearchResult1;
    @Bind(R.id.tv_goods_old_price_search_result1)
    TextView tvGoodsOldPriceSearchResult1;
    @Bind(R.id.tv_carriage_search_result1)
    TextView tvCarriageSearchResult1;
    @Bind(R.id.ll_product1)
    LinearLayout llProduct1;
    @Bind(R.id.tv_goods_new_price_search_result2)
    TextView tvGoodsNewPriceSearchResult2;
    @Bind(R.id.tx_fenqinum2)
    TextView txFenqinum2;
    @Bind(R.id.tv_immediately_pay_search_result2)
    TextView tvImmediatelyPaySearchResult2;
    @Bind(R.id.iv_head_person2)
    SimpleDraweeView ivHeadPerson2;
    @Bind(R.id.tv_goods_name_search_result2)
    TextView tvGoodsNameSearchResult2;
    @Bind(R.id.tv_goods_old_price_search_result2)
    TextView tvGoodsOldPriceSearchResult2;
    @Bind(R.id.tv_carriage_search_result2)
    TextView tvCarriageSearchResult2;
    @Bind(R.id.ll_product2)
    LinearLayout llProduct2;

    public HomeViewHolder(Context context, BaseActivity baseActivity, ViewGroup parent) {
        super(parent, R.layout.item_shopping_vip_adapter);
        this.context = context;
        this.baseAcitivity = baseActivity;
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(ShoppingVip data) {
        super.setData(data);
//        //更多
        ivHeadBand.setImageURI(Uri.parse(data.getImgsrc()));
//        //SuZi
//        //修改：修复图片加载适配问题
        setControllerListener(ivHeadBand, data.getImgsrc(), getScreenWidth(context));
////        holder.ivHeadBand.setAspectRatio(2.16f);
//        int size = data.getProducts().size();
//        if (size > 0) {
//            //第一个
//            if (data.getProducts().get(0).getPhoto() != null) {
//                ivHeadPerson.setImageURI(Uri.parse(data.getProducts().get(0).getPhoto()));
//
////        bitmapUtils.display(holder.ivHeadPerson, mListShoppingVip.get(position).getLogo());
//            }
//            if (data.getProducts().get(0).getName().equals("")) {
//                tvGoodsNameSearchResult.setText(data.getProducts().get(0).getName());//产品名称
//            } else {
//                tvGoodsNameSearchResult.setText(data.getProducts().get(0).getName());//产品名称
//            }
////        holder.tvGoodsNameSearchResult.setText(mListShoppingVip.get(position).getSubtitle());//副标题
////        holder.tvGoodsOldPriceSearchResult.setText(mListShoppingVip.get(position).getPrice1());//
////        holder.tvGoodsOldPriceSearchResult.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//删除线
//            tvCarriageSearchResult.setText("包邮");
//            tvGoodsNewPriceSearchResult.setText(String.format("￥%s", data.getProducts().get(0).getFenqi_per()));//改成了分期数加分期价格mListShoppingVip.get(position).getFenqi_price() + "元*" + mListShoppingVip.get(position).getFenqi() + "期"//第一个
////            tvFenqinum.setText("x" + data.getProducts().get(0).getFenqi_num() + "期");
//            if (size > 1) {
//                //第二个
//                if (data.getProducts().get(1).getPhoto() != null) {
//                    ivHeadPerson1.setImageURI(Uri.parse(data.getProducts().get(1).getPhoto()));
//                }
//                if (data.getProducts().get(1).getName().equals("")) {
//                    tvGoodsNameSearchResult1.setText(data.getProducts().get(1).getName());//产品名称
//                } else {
//                    tvGoodsNameSearchResult1.setText(data.getProducts().get(1).getName());//产品名称
//                }
//                tvCarriageSearchResult1.setText("包邮");
//                tvGoodsNewPriceSearchResult1.setText(String.format("￥%s", data.getProducts().get(1).getFenqi_per()));//改成了分期数加分期价格mListShoppingVip.get(position).getFenqi_price() + "元*" + mListShoppingVip.get(position).getFenqi() + "期"//第二个
////                tvFenqinum1.setText("x" + data.getProducts().get(1).getFenqi_num() + "期");
//                if (size > 2) {
//                    //第三个
//                    if (data.getProducts().get(2).getPhoto() != null) {
//                        ivHeadPerson2.setImageURI(Uri.parse(data.getProducts().get(2).getPhoto()));
//                    }
//                    if (data.getProducts().get(2).getName().equals("")) {
//                        tvGoodsNameSearchResult2.setText(data.getProducts().get(2).getName());//产品名称
//                    } else {
//                        tvGoodsNameSearchResult2.setText(data.getProducts().get(2).getName());//产品名称
//                    }
//                    tvCarriageSearchResult2.setText("包邮");
//                    tvGoodsNewPriceSearchResult2.setText(String.format("￥%s", data.getProducts().get(2).getFenqi_per()));//改成了分期数加分期价格mListShoppingVip.get(position).getFenqi_price() + "元*" + mListShoppingVip.get(position).getFenqi() + "期"
////                    tvFenqinum2.setText("x" + data.getProducts().get(2).getFenqi_num() + "期");
//                }
//            }
//        }

        //  跳转到商品清单页面(2015.12.18删除todo)

        tvImmediatelyPaySearchResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!CommonUtils.isLogin()) {
//                    CommonUtils.loginIn(fragment, null, REQUEST_ORDER);
//                    return;
//                }
            }
        });
        //更多
        ivHeadBand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到查看更多页面,"space_id":23528734,跳转
                baseAcitivity.gt(FoodDetailActivity.class);
            }
        });
        //跳转到商品详情
        llProduct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent1 = new Intent(fragment.getContext(), ShoppingCacheActivity.class);
//                intent1.putExtra("type", "1");
//                intent1.putExtra("product_id", mListShoppingVip.get(position).getProducts().get(1).getProduct_id()+"");
//                fragment.startActivity(intent1);
            }
        });
        llProduct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent1 = new Intent(fragment.getContext(), ShoppingCacheActivity.class);
//                intent1.putExtra("type", "1");
//                intent1.putExtra("product_id", mListShoppingVip.get(position).getProducts().get(2).getProduct_id()+"");
//                fragment.startActivity(intent1);
            }
        });
        llProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent1 = new Intent(fragment.getContext(), ShoppingCacheActivity.class);
//                intent1.putExtra("type", "1");
//                intent1.putExtra("product_id", mListShoppingVip.get(position).getProducts().get(0).getProduct_id()+"");
//                fragment.startActivity(intent1);
            }
        });
    }
    /**
     * 获取屏幕的宽度
     * */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
    /**
     * 通过imageWidth 的宽度，自动适应高度
     */
    public static void setControllerListener(final SimpleDraweeView simpleDraweeView, String imagePath, final int imageWidth) {
        final ViewGroup.LayoutParams layoutParams = simpleDraweeView.getLayoutParams();
        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
                if (imageInfo == null) {
                    return;
                }
                int height = imageInfo.getHeight();
                int width = imageInfo.getWidth();
                layoutParams.width = imageWidth;
                layoutParams.height = (int) ((float) (imageWidth * height) / (float) width);
                simpleDraweeView.setLayoutParams(layoutParams);
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                throwable.printStackTrace();
            }
        };
        DraweeController controller = Fresco.newDraweeControllerBuilder().setControllerListener(controllerListener).setUri(Uri.parse(imagePath)).build();
        simpleDraweeView.setController(controller);
    }
}
