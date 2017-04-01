package com.hunter.fastandroid.interfaces.model;

import com.hunter.fastandroid.dao.GoodsBrand;
import com.hunter.fastandroid.interfaces.FeedBackResult;

import java.util.List;

/**
* Created by Administrator on 2017/04/01
*/

public interface BrandModel{

    void getBrands(String url, FeedBackResult<List<GoodsBrand>> feedBackResult);
}