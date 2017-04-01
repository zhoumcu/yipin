package com.hunter.fastandroid.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.hunter.fastandroid.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：Administrator on 2017/3/31 08:59
 * company: xxxx
 * email：1032324589@qq.com
 */
public class GoodsDetailDialogFragment extends DialogFragment {

    private static final String TAG = GoodsDetailDialogFragment.class.getSimpleName();

    private static GoodsDetailDialogFragment getInstance() {
        GoodsDetailDialogFragment goodsDetailDialogFragment = new GoodsDetailDialogFragment();
        Bundle bundle = new Bundle();
        goodsDetailDialogFragment.setArguments(bundle);
        return goodsDetailDialogFragment;
    }

    public static void showDialog(AppCompatActivity activity, OnResponseListenter listenter) {

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        Fragment fragment = activity.getSupportFragmentManager().findFragmentByTag(TAG);
        if (fragment != null) {
            fragmentTransaction.remove(fragment);
        }
        fragmentTransaction.addToBackStack(null);
        // Create and show the dialog.
        GoodsDetailDialogFragment dialogFragment = GoodsDetailDialogFragment.getInstance();
        dialogFragment.setListener(listenter);
        dialogFragment.show(fragmentTransaction, TAG);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialogfragment_goods_detail, container, false);

        initView(view);
        initData();
        initEvent();

        ButterKnife.bind(this, view);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.dialogfragment_goods_detail);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        return dialog;
    }

    private void initView(View view) {
        ButterKnife.bind(this, view);
    }

    private void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View view = listAdapter.getView(i, listView.getChildAt(i), listView);
            view.measure(0, 0);
            totalHeight += view.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        System.out.println(totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1)));

        listView.setLayoutParams(params);
    }


    private void initEvent() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 回调监听
     */
    public OnResponseListenter listenter;

    @OnClick(R.id.tv_ok)
    public void onClick() {
        this.dismiss();
        OrderConfirmationFragment.jumpIn((AppCompatActivity) getActivity());
    }

    public interface OnResponseListenter {
        void cancal();

        void ok(String skuId, double shoufu, int loanTime);
    }

    public void setListener(OnResponseListenter listenter) {
        this.listenter = listenter;
    }


}
