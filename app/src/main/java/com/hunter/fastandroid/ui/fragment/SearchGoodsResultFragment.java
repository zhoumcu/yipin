package com.hunter.fastandroid.ui.fragment;

/**
 * Created by ymn on 2017/4/2.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hunter.fastandroid.R;
import com.hunter.fastandroid.adapter.SearchGoodsResultAdapter;
import com.hunter.fastandroid.dao.Goods;
import com.hunter.fastandroid.ui.activity.ShoppingCacheActivity;
import com.jude.easyrecyclerview.EasyRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import cn.zhiao.baselib.base.BaseFragment;
import cn.zhiao.baselib.utils.SharedPrefrecesUtils;

/**
 * 搜索商品结果页面   B11
 * Created by  on 2015/11/10.
 */
public class SearchGoodsResultFragment extends BaseFragment implements View.OnClickListener{

    //    public static String TAG = "SearchGoodsFragment";
    public static String TAG = SearchGoodsResultFragment.class.getSimpleName();
    public static String SEARCH_GOODS_BY_KEY = "/product/list";
    public static String KEY_SPACE = "#";
    public static String HISTORY_KEY = "history_key";
    public static String ARG = "key";
    public static String ISFROMKEYBOAR = "isFromKeyboar";
    public static int ROWNUMBER = 2;
    public static int DATA_NUMBER = 10;

    @Bind(R.id.iv_back_actionbar)
    ImageView ivBackActionbar;
    @Bind(R.id.et_search_goods_name)
    EditText etSearchGoodsName;
    @Bind(R.id.tv_search)
    TextView tvSearch;
    @Bind(R.id.prrcv_search_goods_result)
    EasyRecyclerView prrcvSearchGoodsResult;
    @Bind(R.id.pb)
    RelativeLayout pb;
    @Bind(R.id.rl_empty)
    RelativeLayout rlEmpty;

    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    private String key = "";  //搜索关键字
    private String historyKey = "";//历史搜索

    private int page = 0; //分页加载
    private int psize = DATA_NUMBER; //每页加载数
    private boolean isRefresh = false;//是否下拉刷新
    private boolean isFromKeyboar = false;//true为标记从键盘点击键过来，不标记的话，通过键盘的搜索会有两个Fragment，不知道为什么
    private SearchGoodsResultAdapter mSearchGoodsResultAdapter;
    private ArrayList<Goods> mListSearchResult;

    public SearchGoodsResultFragment () {

    }

    public static SearchGoodsResultFragment newInstance () {
        SearchGoodsResultFragment searchGoodsResultFragment = new SearchGoodsResultFragment();
        return searchGoodsResultFragment;
    }

    public static SearchGoodsResultFragment newInstance (String key) {
        SearchGoodsResultFragment searchGoodsResultFragment = new SearchGoodsResultFragment();

        Bundle bundle = new Bundle();
        bundle.putString(ARG, key);
        searchGoodsResultFragment.setArguments(bundle);
        return searchGoodsResultFragment;
    }

    public static SearchGoodsResultFragment newInstance (String key, boolean isFromKeyboar) {
        SearchGoodsResultFragment searchGoodsResultFragment = new SearchGoodsResultFragment();

        Bundle bundle = new Bundle();
        bundle.putString(ARG, key);
        bundle.putBoolean(ISFROMKEYBOAR, isFromKeyboar);
        searchGoodsResultFragment.setArguments(bundle);
        return searchGoodsResultFragment;
    }

    public static void jumpIn (AppCompatActivity ac) {
        Fragment fragment = SearchGoodsResultFragment.newInstance();

        FragmentManager fragmentmanager = ac.getSupportFragmentManager();
        fragmentmanager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment, SearchGoodsResultFragment.TAG)
                .commitAllowingStateLoss();
    }

    public static void jumpIn (AppCompatActivity ac, String key) {
        Fragment fragment = SearchGoodsResultFragment.newInstance(key);

        FragmentManager fragmentmanager = ac.getSupportFragmentManager();
        fragmentmanager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment, SearchGoodsResultFragment.TAG)
                .commitAllowingStateLoss();
    }

    public static void jumpIn (AppCompatActivity ac, String key, boolean isFromKeyboar) {
        Fragment fragment = SearchGoodsResultFragment.newInstance(key,isFromKeyboar);

        FragmentManager fragmentmanager = ac.getSupportFragmentManager();
        fragmentmanager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment, SearchGoodsResultFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_search_goods_result;
    }

    @Override
    public void initView() {
        initRec();
        initEvent();
    }


    @Override
    protected void initPresenter() {

    }

    private void initdata () {
        if (mListSearchResult == null) {
            mListSearchResult = new ArrayList<>();
        }
    }

    private void initRec () {
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(ROWNUMBER, StaggeredGridLayoutManager.VERTICAL);
        prrcvSearchGoodsResult.setLayoutManager(staggeredGridLayoutManager);
        mSearchGoodsResultAdapter = new SearchGoodsResultAdapter(this, mListSearchResult);
        prrcvSearchGoodsResult.setAdapter(mSearchGoodsResultAdapter);
    }

    private void requestData (int page, int psize, String key) {

    }

    private void initEvent () {
        ivBackActionbar.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
        mSearchGoodsResultAdapter.setOnItemClickListener(new SearchGoodsResultAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick (View view, int position) {
//                GoodsDetailFragment.jumpIn(mMainActivity, mListSearchResult.get(position).getProduct_id());
                Intent intent1 = new Intent(getActivity(), ShoppingCacheActivity.class);
                intent1.putExtra("type", "1");
                intent1.putExtra("product_id", mListSearchResult.get(position).getProduct_id());
                startActivity(intent1);
            }
        });

        tvSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER){
                    //得到历史搜索
                    historyKey = SharedPrefrecesUtils.getStrFromSharedPrefrences(HISTORY_KEY, getActivity());

                    key = etSearchGoodsName.getText().toString();

                    if (TextUtils.isEmpty(key)) {
                        etSearchGoodsName.setError("不能为空");
                        etSearchGoodsName.requestFocus();

                    }else {
                        page = 1;
                        isRefresh = true;
                        requestData(page, psize, key);
                        //保存搜索
                        saveHistorySearchKey();
                    }
                }
                return false;
            }
        });
    }


    @Override
    public void onResume () {
        super.onResume();
        etSearchGoodsName.setText(key);
    }

    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            //返回
            case R.id.iv_back_actionbar:
                getFragmentManager().popBackStack();
                break;
            //搜索
            case R.id.tv_search:
                getFragmentManager().popBackStack();
                if (isFromKeyboar) {//解决了两个Fragment的问题
                    getFragmentManager().popBackStack();
                }
                //得到历史搜索
                historyKey = SharedPrefrecesUtils.getStrFromSharedPrefrences(HISTORY_KEY, getActivity());

                key = etSearchGoodsName.getText().toString();

                if (TextUtils.isEmpty(key)) {
                    etSearchGoodsName.setError("不能为空");
                    etSearchGoodsName.requestFocus();
                    return;
                }

                page = 1;
                isRefresh = true;
                requestData(page, psize, key);


                //保存搜索
                saveHistorySearchKey();
                break;
        }
    }

    /**
     * 保存历史搜索数据
     */
    private void saveHistorySearchKey () {
        if (TextUtils.isEmpty(historyKey)) {
            historyKey = key;
        } else {
            //下面三行是把刚搜索的数据和以前搜索的数据重复的删除掉
            String keywords1 = historyKey.replaceAll(key + KEY_SPACE, "");
            String keywords2 = keywords1.replaceAll(KEY_SPACE + key, "");
            String keywords3 = keywords2.replaceAll(KEY_SPACE + key + KEY_SPACE, KEY_SPACE);

            //判断历史搜索数据是否和现在搜索的关键字一样
            if (! historyKey.equals(key)) {
                historyKey = key + KEY_SPACE + keywords3;
            } else {
                historyKey = key;
            }
        }
        SharedPrefrecesUtils.saveStrToSharedPrefrences(HISTORY_KEY, historyKey, getActivity());
    }
}
