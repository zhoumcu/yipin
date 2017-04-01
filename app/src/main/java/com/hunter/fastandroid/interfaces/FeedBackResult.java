package com.hunter.fastandroid.interfaces;

/**
 * author：Administrator on 2017/3/30 15:51
 * company: xxxx
 * email：1032324589@qq.com
 */

public interface FeedBackResult<M> {
    void onSucess(M model);
    void onError(String msg);
}
