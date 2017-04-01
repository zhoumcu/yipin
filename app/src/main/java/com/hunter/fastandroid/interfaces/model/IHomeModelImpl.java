package com.hunter.fastandroid.interfaces.model;


import android.content.Context;

import com.hunter.fastandroid.dao.ShoppingVip;
import com.hunter.fastandroid.interfaces.FeedBackResult;

import java.util.ArrayList;
import java.util.List;

import cn.zhiao.baselib.base.BaseModel;

/**
* Created by Administrator on 2017/03/30
*/

public class IHomeModelImpl extends BaseModel implements IHomeModel<List<ShoppingVip>>{

    public IHomeModelImpl(Context context) {
        super(context);
    }


    @Override
    public void getHomeProductors(String url, FeedBackResult<List<ShoppingVip>> callback) {
        List<ShoppingVip> list = new ArrayList<>();
        for (int i=0;i<10;i++){
            ShoppingVip products = new ShoppingVip();
            products.setName("sdfsdf");
            products.setImgsrc("http://img1.imgtn.bdimg.com/it/u=1538368858,4289171815&fm=11&gp=0.jpg");
            list.add(products);
        }
        callback.onSucess(list);
    }
}