package com.hunter.fastandroid.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hunter.fastandroid.R;
import com.hunter.fastandroid.adapter.HistorySearchGoodsAdapter;
import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.zhiao.baselib.base.BaseFragment;
import cn.zhiao.baselib.custom.MultipleTextViewGroup;
import cn.zhiao.baselib.utils.SharedPrefrecesUtils;


/**
 * 搜索商品
 * <p/>
 * 添加接口和数据，加入根据key搜索跳转到下一个页面的代码
 * Created by zhoumcu on 2015/11/10.
 */
public class SearchGoodsFragment extends BaseFragment implements View.OnClickListener {

    public static String TAG = SearchGoodsFragment.class.getSimpleName();
    public static String BASE_URL_HOT_SEARCH = "/product/hot-keywords?ajax=json";

    public static String KEY_SPACE = "#";
    public static String HISTORY_KEY = "history_key";
    public static int HISTORY_KEY_NUMBER = 5;


    @Bind(R.id.mtvg_hot_search_key)
    MultipleTextViewGroup mtvgHotSearchKey;
    @Bind(R.id.rcv_histore_search)
    RecyclerView rcvHistoreSearch;
    @Bind(R.id.iv_back_actionbar)
    ImageView ivBackActionbar;
    @Bind(R.id.et_search_goods_name)
    EditText etSearchGoodsName;
    @Bind(R.id.tv_search)
    TextView tvSearch;


    private List<String> mListHotSearch = new ArrayList<>();//热门搜索
    private List<String> mListSearchResult = new ArrayList<>();;//历史搜索
    private LinearLayoutManager mLinearLayoutManagerHistorySearch;

    private String key = "";  //搜索关键字
    private String historyKey = "";
    private HistorySearchGoodsAdapter mHistorySearchGoodsAdapter;

    public SearchGoodsFragment() {

    }

    public static SearchGoodsFragment newInstance() {
        SearchGoodsFragment searchGoodsFragment = new SearchGoodsFragment();
        return searchGoodsFragment;
    }

    public static void jumpIn(AppCompatActivity ac) {
        Fragment fragment = SearchGoodsFragment.newInstance();
        FragmentManager fragmentmanager = ac.getSupportFragmentManager();
        fragmentmanager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment, SearchGoodsFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_search_goods;
    }

    @Override
    public void initView() {
        setShowToolbar(false);
        initRec();
        initEvent();
        bindHistorySearchData();;
        bindHotData();
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回
            case R.id.iv_back_actionbar:
                logE("返回");
                getFragmentManager().popBackStack();
                break;
            //搜索
            case R.id.tv_search:
                logE("搜索");
                getFragmentManager().popBackStack();
                break;
        }
    }
    private void initRec() {
        mLinearLayoutManagerHistorySearch = new LinearLayoutManager(getActivity());
        mLinearLayoutManagerHistorySearch.setOrientation(LinearLayoutManager.VERTICAL);
        rcvHistoreSearch.setLayoutManager(mLinearLayoutManagerHistorySearch);
        mHistorySearchGoodsAdapter = new HistorySearchGoodsAdapter(this, mListSearchResult);
        rcvHistoreSearch.setAdapter(mHistorySearchGoodsAdapter);
    }

    //绑定历史搜索的数据
    public void bindHistorySearchData() {
        historyKey = SharedPrefrecesUtils.getStrFromSharedPrefrences(HISTORY_KEY, getActivity());
        L.i("保存的历史数据" + historyKey);
        if (!TextUtils.isEmpty(historyKey)) {
            mListSearchResult.clear();
            String[] keyArr = historyKey.split(KEY_SPACE);
            //最多添加五个数据
            for (int i = 0; i < (keyArr.length > HISTORY_KEY_NUMBER ? HISTORY_KEY_NUMBER : keyArr.length); i++) {
                L.i("keyArr[" + i + "]=" + keyArr[i]);
                mListSearchResult.add(keyArr[i]);
            }
            /*for (int i = 0; i < keyArr.length; i++) {
                if (i < HISTORY_KEY_NUMBER) {
                    mListSearchResult.add(keyArr[i]);
                }
            }*/
            if (keyArr.length > 0) {
                mListSearchResult.add("清空搜索记录");
            }
        }
    }


    private void bindHotData() {
        if (mListHotSearch.size() > 0) mListHotSearch.clear();
            requestHotData();
    }

    private void requestHotData() {
        for (int i=0;i<10;i++){
            String s = "产品";
            mListHotSearch.add(s);
        }
        mtvgHotSearchKey.setTextViews(mListHotSearch);
    }

    private void initEvent() {

        ivBackActionbar.setOnClickListener(this);
        tvSearch.setOnClickListener(this);

        //热门搜索
        mtvgHotSearchKey.setOnMultipleTVItemClickListener(new MultipleTextViewGroup.OnMultipleTVItemClickListener() {
            @Override
            public void onMultipleTVItemClick(View view, int position) {
                TextView tv = (TextView) view;
                key = tv.getText().toString();
                etSearchGoodsName.setText(key);
                saveHistorySearchKey();
                SearchGoodsResultFragment.jumpIn((AppCompatActivity) getActivity(), key);
            }
        });

        //历史搜索
        mHistorySearchGoodsAdapter.setOnItemClickListener(new HistorySearchGoodsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {

                key = mListSearchResult.get(position).toString();
                etSearchGoodsName.setText(key);
                saveHistorySearchKey();
                SearchGoodsResultFragment.jumpIn((AppCompatActivity) getActivity(), key);
            }
        });

        etSearchGoodsName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    key = etSearchGoodsName.getText().toString();
                    if (TextUtils.isEmpty(key)) {
                        etSearchGoodsName.setError("不能为空");
                        etSearchGoodsName.requestFocus();
                    } else {
                        saveHistorySearchKey();
                        SearchGoodsResultFragment.jumpIn((AppCompatActivity) getActivity(), key, true);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 保存历史搜索数据
     */
    private void saveHistorySearchKey() {
        if (TextUtils.isEmpty(historyKey)) {
            historyKey = key;
        } else {
            //下面三行是把刚搜索的数据和以前搜索的数据重复的删除掉
            String keywords1 = historyKey.replaceAll(key + KEY_SPACE, "");
            String keywords2 = keywords1.replaceAll(KEY_SPACE + key, "");
            String keywords3 = keywords2.replaceAll(KEY_SPACE + key + KEY_SPACE, KEY_SPACE);

            //判断历史搜索数据是否和现在搜索的关键字一样
            if (!historyKey.equals(key)) {
                historyKey = key + KEY_SPACE + keywords3;
            } else {
                historyKey = key;
            }
        }
        SharedPrefrecesUtils.saveStrToSharedPrefrences(HISTORY_KEY, historyKey, getActivity());
    }

    public void notifyHisttoryData() {
        mHistorySearchGoodsAdapter.notifyDataSetChanged();
    }

}
