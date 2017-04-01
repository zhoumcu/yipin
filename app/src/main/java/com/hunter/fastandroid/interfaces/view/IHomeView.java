package com.hunter.fastandroid.interfaces.view;

import com.hunter.fastandroid.dao.ShoppingVip;

import java.util.List;

import cn.zhiao.baselib.base.IBaseView;

/**
* Created by Administrator on 2017/03/30
*/

public interface IHomeView extends IBaseView{
    /**
     *  获取首页产品列表
     * @param productses
     */
    void ProdcutorResult(List<ShoppingVip> productses);
}