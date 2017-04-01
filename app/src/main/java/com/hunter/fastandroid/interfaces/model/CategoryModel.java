package com.hunter.fastandroid.interfaces.model;

import com.hunter.fastandroid.dao.Category;
import com.hunter.fastandroid.interfaces.FeedBackResult;

import java.util.List;

/**
* Created by Administrator on 2017/04/01
*/

public interface CategoryModel{

    void getCategorys(String url, FeedBackResult<List<Category>> feedBackResult);
}