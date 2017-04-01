package com.hunter.fastandroid.interfaces.view;

import com.hunter.fastandroid.dao.GoodsBrand;

import java.util.List;

import cn.zhiao.baselib.base.IBaseView;

/**
* Created by Administrator on 2017/04/01
*/

public interface BrandView extends IBaseView{

    void getBrands(List<GoodsBrand> model);
}