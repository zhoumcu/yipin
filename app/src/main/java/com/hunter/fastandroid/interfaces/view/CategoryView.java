package com.hunter.fastandroid.interfaces.view;

import com.hunter.fastandroid.dao.Category;

import java.util.List;

import cn.zhiao.baselib.base.IBaseView;

/**
* Created by Administrator on 2017/04/01
*/

public interface CategoryView extends IBaseView{

    void getCategorys(List<Category> model);
}