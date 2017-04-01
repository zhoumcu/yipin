package com.hunter.fastandroid.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hunter.fastandroid.R;

import java.util.List;

import butterknife.Bind;
import cn.zhiao.baselib.base.BaseFragment;
import cn.zhiao.baselib.custom.MultipleTextViewGroup;


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


    private List<String> mListHotSearch;//热门搜索
    private List<String> mListSearchResult;//历史搜索
    private LinearLayoutManager mLinearLayoutManagerHistorySearch;

    private String key = "";  //搜索关键字
    private String historyKey = "";

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

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onClick(View view) {

    }
}
