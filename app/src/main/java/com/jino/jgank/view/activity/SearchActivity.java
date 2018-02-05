package com.jino.jgank.view.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fingdo.statelayout.StateLayout;
import com.jino.baselibrary.base.activity.RxActivity;
import com.jino.baselibrary.di.component.AppComponent;
import com.jino.jgank.R;
import com.jino.jgank.adapter.SearchResultAdapter;
import com.jino.jgank.contract.SearchContract;
import com.jino.jgank.di.component.DaggerActivityComponent;
import com.jino.jgank.model.bean.ArticleItem;
import com.jino.jgank.presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;

public class SearchActivity extends RxActivity<SearchPresenter> implements SearchView.OnQueryTextListener, SearchContract.View, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

    @BindView(R.id.lay_state)
    public StateLayout mLayState;

    @BindView(R.id.rv_search)
    public RecyclerView mListSearch;

    private List<ArticleItem> mResultList;
    private SearchResultAdapter adapter;
    private String mKeyword;

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoadFailed() {

    }

    @Override
    public void onLoadSucceed() {

    }

    @Override
    public void injectComponent(AppComponent component) {
        DaggerActivityComponent.builder()
                .appComponent(component)
                .build()
                .inject(this);
    }

    @Override
    public int layoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        mToolbar.setTitle("");
        setupToolBar(mToolbar);

        adapter = new SearchResultAdapter(mResultList);
        mListSearch.setAdapter(adapter);
        mListSearch.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mListSearch.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        adapter.setOnLoadMoreListener(this, mListSearch);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        mResultList = new ArrayList<>();
        mPresenter.onAttach(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setIconified(true);
        searchView.onActionViewExpanded();
        searchView.setQueryHint("输入关键字搜索");
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mKeyword = query;
        mLayState.showLoadingView();
        mPresenter.searchKeyword(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void showResult(Collection<ArticleItem> datas) {
        mLayState.showContentView();
        adapter.addData(datas);
    }

    @Override
    public void showMore(Collection<ArticleItem> datas) {
        adapter.addData(datas);
        adapter.loadMoreComplete();
    }

    @Override
    public void showEmpty() {
        mLayState.showEmptyView();
    }

    @Override
    public void noMoreData() {
        adapter.loadMoreFail();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getMoreData(mKeyword);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ArticleItem item = (ArticleItem) adapter.getItem(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable(WebDetialActivity.PARAMS_DATA, item);
        startActivity(WebDetialActivity.class, bundle);
    }
}
