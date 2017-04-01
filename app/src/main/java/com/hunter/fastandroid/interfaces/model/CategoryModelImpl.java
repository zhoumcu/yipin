package com.hunter.fastandroid.interfaces.model;


import com.hunter.fastandroid.dao.Category;
import com.hunter.fastandroid.interfaces.FeedBackResult;

import java.util.ArrayList;
import java.util.List;

/**
* Created by Administrator on 2017/04/01
*/

public class CategoryModelImpl implements CategoryModel{

    @Override
    public void getCategorys(String url, FeedBackResult<List<Category>> feedBackResult) {
        List<Category> list = new ArrayList<>();
        for (int i=0;i<10;i++){
            Category mCategory = new Category();
            mCategory.setName("衣服");
            mCategory.setPhoto_id("http://img1.imgtn.bdimg.com/it/u=1538368858,4289171815&fm=11&gp=0.jpg");
            mCategory.setCid("5");
            mCategory.setShow(true);
            mCategory.setParent_id("5");
            mCategory.setDepth("2");
            list.add(mCategory);
        }
        feedBackResult.onSucess(list);
    }
}