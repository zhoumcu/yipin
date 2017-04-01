package com.hunter.fastandroid.interfaces.model;


import com.hunter.fastandroid.dao.GoodsBrand;
import com.hunter.fastandroid.interfaces.FeedBackResult;

import java.util.ArrayList;
import java.util.List;

/**
* Created by Administrator on 2017/04/01
*/

public class BrandModelImpl implements BrandModel{


    @Override
    public void getBrands(String url, FeedBackResult<List<GoodsBrand>> feedBackResult) {
        List<GoodsBrand> list = new ArrayList<>();
        for (int i=0;i<10;i++){
            GoodsBrand mCategory = new GoodsBrand();
            mCategory.setName("宝马");
            mCategory.setLogo("http://img1.imgtn.bdimg.com/it/u=1538368858,4289171815&fm=11&gp=0.jpg");
            mCategory.setDesc("宝马车是贵族");
            list.add(mCategory);
        }
        feedBackResult.onSucess(list);
    }
}