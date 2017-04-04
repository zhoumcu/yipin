package cn.zhiao.baselib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment implements IBaseView {
    public static final String MY_TAG = "BaseFragment";
    private BaseActivity mActivity;
    private View mLayoutView;

    /**
     * 初始化布局
     */
    public abstract int getLayoutRes();

    /**
     * 初始化视图
     */
    public abstract void initView();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayoutView = getCreateView(inflater, container);
        ButterKnife.bind(this, mLayoutView);
        initPresenter();
        initView();
        return mLayoutView;
    }

    protected abstract void initPresenter();

    /**
     * 获取Fragment布局文件的View
     *
     * @param inflater
     * @param container
     * @return
     */
    private View getCreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(getLayoutRes(), container, false);
    }

    /**
     * 获取当前Fragment状态
     *
     * @return true为正常 false为未加载或正在删除
     */
    private boolean getStatus() {
        return (isAdded() && !isRemoving());
    }

    /**
     * 获取Activity
     *
     * @return
     */
    public BaseActivity getBaseActivity() {
        if (mActivity == null) {
            mActivity = (BaseActivity) getActivity();
        }
        return mActivity;
    }

    @Override
    public void showProgress(boolean flag, String message) {
        if (getStatus()) {
            BaseActivity activity = getBaseActivity();
            if (activity != null) {
                activity.showProgress(flag, message);
            }
        }
    }

    @Override
    public void showProgress(String message) {
        showProgress(true, message);
    }

    @Override
    public void showProgress() {
        showProgress(true);
    }

    @Override
    public void showProgress(boolean flag) {
        showProgress(flag, "");
    }

    @Override
    public void hideProgress() {
        if (getStatus()) {
            BaseActivity activity = getBaseActivity();
            if (activity != null) {
                activity.hideProgress();
            }
        }
    }

    @Override
    public void showToast(int resId) {
        if (getStatus()) {
            BaseActivity activity = getBaseActivity();
            if (activity != null) {
                activity.showToast(resId);
            }
        }
    }

    @Override
    public void showToast(String msg) {
        if (getStatus()) {
            BaseActivity activity = getBaseActivity();
            if (activity != null) {
                activity.showToast(msg);
            }
        }
    }
    @Override
    public void logE(String msg) {
        Log.e(MY_TAG,msg);
    }

    @Override
    public void logI(String msg) {
        Log.i(MY_TAG,msg);
    }

    @Override
    public void logW(String msg) {
        Log.w(MY_TAG,msg);
    }

    @Override
    public void logD(String msg) {
        Log.d(MY_TAG,msg);
    }
    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void close() {
    }
    /**
     *
     * @param cl
     */
    public void gt(Class cl){
        gt(null, cl);
    }

    /**
     *
     * @param bundle
     * @param cl
     */
    public void gt(Bundle bundle,Class cl){
        BaseActivity activity = getBaseActivity();
        if (activity != null) {
            activity.gt(bundle, cl);
        }
    }
    /**
     * 是否显示TollBar
     *
     * @param show
     */
    public void setShowToolbar(boolean show) {
        if (show) {
            if(getBaseActivity().toolbar!=null){
                getBaseActivity().toolbar.setVisibility(View.VISIBLE);
            }
        } else {
            if(getBaseActivity().toolbar!=null){
                getBaseActivity().toolbar.setVisibility(View.GONE);
            }
        }
    }
}
