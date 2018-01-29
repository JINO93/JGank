package com.jino.jgank.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fingdo.statelayout.StateLayout;
import com.jino.baselibrary.base.fragment.AbsEasyRxFragment;
import com.jino.baselibrary.di.component.AppComponent;
import com.jino.baselibrary.utils.ConditionUtils;
import com.jino.jgank.R;
import com.jino.jgank.adapter.ArticleItemAdapter;
import com.jino.jgank.contract.GankCategroyContract;
import com.jino.jgank.di.component.DaggerFragmentComponent;
import com.jino.jgank.entity.GankResponEntity;
import com.jino.jgank.presenter.GankCategroyPresenter;
import com.jino.jgank.view.activity.WebDetialActivity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by JINO on 2018/1/24.
 */

public class GankCategroyFragment extends AbsEasyRxFragment<GankCategroyPresenter> implements GankCategroyContract.View, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {

    public static final String CATEGROY = "cate";

    private String mCategroy;
    private ArticleItemAdapter mAdapter;

    @BindView(R.id.lay_state)
    public StateLayout mStateLayout;

    @BindView(R.id.recycle)
    public RecyclerView mItemList;

    public static GankCategroyFragment newInstance(String cate) {
        GankCategroyFragment fragment = new GankCategroyFragment();
        Bundle arguments = new Bundle();
        arguments.putCharSequence(CATEGROY, cate);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_gank_categroy;
    }

    @Override
    public void initView(View view) {
        mAdapter = new ArticleItemAdapter(this);
        mAdapter.setOnLoadMoreListener(this, mItemList);
        mAdapter.setOnItemClickListener(this);
        mItemList.setLayoutManager(new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL, false));
        mItemList.setAdapter(mAdapter);
        mItemList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL));
    }

    @Override
    public void initData() {
        mCategroy = getArguments().getString(CATEGROY);
        ConditionUtils.checkParams(!TextUtils.isEmpty(mCategroy), "category can not be null.");
        mStateLayout.showLoadingView();
        mPresenter.loadData(mCategroy);
    }

    @Override
    public void attachView(View view) {
        mPresenter.onAttach(this);
    }

    @Override
    protected void inject(AppComponent appComponent) {
        DaggerFragmentComponent.builder()
                .appComponent(appComponent).build().inject(this);
    }

    @Override
    public void onLoading() {
    }

    @Override
    public void onLoadFailed() {
        mStateLayout.showErrorView();
    }

    @Override
    public void onLoadSucceed() {
        if (mAdapter.getItemCount() <= 0) {
            mStateLayout.showErrorView();
        } else {
            mStateLayout.showContentView();
        }
    }


    @Override
    public void loadMore(List<GankResponEntity.GankItemBean> items) {
        mAdapter.addData(items);
        mAdapter.loadMoreComplete();
        mStateLayout.showContentView();
    }

    @Override
    public void refresh(List<GankResponEntity.GankItemBean> items) {
        mStateLayout.showContentView();
        mAdapter.setNewData(items);
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadMoreData(mCategroy);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        GankResponEntity.GankItemBean itemData = (GankResponEntity.GankItemBean) adapter
                .getData().get(position);
        Intent intent = new Intent(getActivity(), WebDetialActivity.class);
        Bundle bundle = new Bundle();
        bundle.putCharSequence(WebDetialActivity.PARAMS_URL, itemData.getUrl());
        bundle.putCharSequence(WebDetialActivity.PARAMS_TITLE, itemData.getDesc());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
