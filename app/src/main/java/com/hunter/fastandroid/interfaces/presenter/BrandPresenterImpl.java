package com.hunter.fastandroid.interfaces.presenter;


import android.content.Context;

import com.hunter.fastandroid.dao.GoodsBrand;
import com.hunter.fastandroid.interfaces.FeedBackResult;
import com.hunter.fastandroid.interfaces.model.BrandModelImpl;
import com.hunter.fastandroid.interfaces.view.BrandView;
import com.hunter.fastandroid.presenter.interfaces.BrandPresenter;

import java.util.List;

/**
* Created by Administrator on 2017/04/01
*/

public class BrandPresenterImpl implements BrandPresenter {

    private BrandModelImpl model;
    private BrandView brandView;

    public BrandPresenterImpl(Context context, BrandView brandView) {
        this.brandView = brandView;
        model = new BrandModelImpl();
    }

    @Override
    public void getBrands(String url) {
        model.getBrands(url, new FeedBackResult<List<GoodsBrand>>() {
            @Override
            public void onSucess(List<GoodsBrand> model) {
                brandView.getBrands(model);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}