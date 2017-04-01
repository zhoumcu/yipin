package com.hunter.fastandroid.interfaces.presenter;


import android.content.Context;

import com.hunter.fastandroid.dao.Category;
import com.hunter.fastandroid.interfaces.FeedBackResult;
import com.hunter.fastandroid.interfaces.model.CategoryModelImpl;
import com.hunter.fastandroid.interfaces.view.CategoryView;
import com.hunter.fastandroid.presenter.interfaces.CategoryPresenter;

import java.util.List;

/**
* Created by Administrator on 2017/04/01
*/

public class CategoryPresenterImpl implements CategoryPresenter {

    private CategoryModelImpl model;
    private CategoryView categoryView;

    public CategoryPresenterImpl(Context context, CategoryView categoryView) {
        this.categoryView = categoryView;
        model = new CategoryModelImpl();
    }

    @Override
    public void getCategorys(String url) {
        model.getCategorys(url, new FeedBackResult<List<Category>>() {
            @Override
            public void onSucess(List<Category> model) {
                categoryView.getCategorys(model);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}