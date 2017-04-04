/*
 * Copyright 2014 trinea.cn All right reserved. This software is the confidential and proprietary information of
 * trinea.cn ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with trinea.cn.
 */
package com.hunter.fastandroid.adapter.autosrcollviewpager;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * 特惠第二个轮播图/商品详情(无点击事件)
 * imagePagerAdapter
 *
 * 修改布局，和数据类型,加入bitmapUtils,轮播以及点击事件
 * * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2014-2-23
 */
public class ImagePagerBaseAdapter2 extends RecyclingPagerAdapter {

    private Context context;
    private Fragment fragment;
    private List<String> imageIdList;

    private int           size;
    private boolean       isInfiniteLoop;

    public ImagePagerBaseAdapter2(Fragment fragment, List<String> imageIdList) {
        this.fragment=fragment;
        this.context = fragment.getActivity();
        this.imageIdList = imageIdList;
//        this.size = ListUtils.getSize(imageIdList);
        this.size =imageIdList.size();
        isInfiniteLoop = false;
    }

    @Override
    public int getCount() {
        // Infinite loop
        return isInfiniteLoop ? Integer.MAX_VALUE : imageIdList.size();
    }

    /**
     * get really position
     * 
     * @param position
     * @return
     */
    private int getPosition(int position) {
        return isInfiniteLoop ? position % size : position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup container) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = holder.imageView = new SimpleDraweeView(context);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }
//        holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        holder.imageView.setAdjustViewBounds(true);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtils.s(context, "你点击了ViewPager的轮播图");
            }
        });

        if(imageIdList.size()!=0){
            holder.imageView.setImageURI(Uri.parse(imageIdList.get(position%imageIdList.size())));
        }

        return view;
    }

    private static class ViewHolder {

        SimpleDraweeView imageView;
    }

    /**
     * @return the isInfiniteLoop
     */
    public boolean isInfiniteLoop() {
        return isInfiniteLoop;
    }

    /**
     * @param isInfiniteLoop the isInfiniteLoop to set
     */
    public ImagePagerBaseAdapter2 setInfiniteLoop(boolean isInfiniteLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
        return this;
    }
}
