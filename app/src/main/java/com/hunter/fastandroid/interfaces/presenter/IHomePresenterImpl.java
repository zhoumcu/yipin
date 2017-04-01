package com.hunter.fastandroid.interfaces.presenter;


import android.content.Context;

import com.hunter.fastandroid.dao.ShoppingVip;
import com.hunter.fastandroid.interfaces.FeedBackResult;
import com.hunter.fastandroid.interfaces.model.IHomeModel;
import com.hunter.fastandroid.interfaces.model.IHomeModelImpl;
import com.hunter.fastandroid.interfaces.view.IHomeView;
import com.hunter.fastandroid.presenter.interfaces.IHomePresenter;

import java.util.List;

import cn.zhiao.baselib.base.BasePresenter;

/**
* Created by Administrator on 2017/03/30
*/

public class IHomePresenterImpl extends BasePresenter implements IHomePresenter {
    private IHomeView homeView;
    private IHomeModel model;

    public IHomePresenterImpl(Context context,IHomeView homeView) {
        model = new IHomeModelImpl(context);
        this.homeView = homeView;
    }

    @Override
    public void getHomeProductors(String url) {
        //homeView.showProgress();
        model.getHomeProductors(url, new FeedBackResult<List<ShoppingVip>>() {
            @Override
            public void onSucess(List<ShoppingVip> model) {
                homeView.ProdcutorResult(model);
                //homeView.hideProgress();
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}