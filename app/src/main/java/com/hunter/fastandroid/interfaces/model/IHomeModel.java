package com.hunter.fastandroid.interfaces.model;

import com.hunter.fastandroid.interfaces.FeedBackResult;

/**
* Created by Administrator on 2017/03/30
*/

public interface IHomeModel<M>{

    void getHomeProductors(String url, FeedBackResult<M> callback);
}