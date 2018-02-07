package com.jino.jgank.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fingdo.statelayout.StateLayout;
import com.jino.baselibrary.base.fragment.AbsEasyRxFragment;
import com.jino.baselibrary.di.component.AppComponent;
import com.jino.baselibrary.utils.ConditionUtils;
import com.jino.jgank.R;
import com.jino.jgank.adapter.ArticleItemAdapter;
import com.jino.jgank.contract.GankCategroyContract;
import com.jino.jgank.db.DBManager;
import com.jino.jgank.di.component.DaggerFragmentComponent;
import com.jino.jgank.model.bean.ArticleItem;
import com.jino.jgank.presenter.GankCategroyPresenter;
import com.jino.jgank.view.activity.WebDetialActivity;

import java.util.List;

import butterknife.BindView;

/**
 * 展示Gank分类的Fragment
 * Created by JINO on 2018/1/24.
 */

public class GankCategroyFragment extends AbsEasyRxFragment<GankCategroyPresenter>
        implements GankCategroyContract.View, BaseQuickAdapter.RequestLoadMoreListener,
        BaseQuickAdapter.OnItemClickListener {

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
    public void loadMore(List<ArticleItem> items) {
        mAdapter.addData(items);
        mAdapter.loadMoreComplete();
        mStateLayout.showContentView();
    }

    @Override
    public void refresh(List<ArticleItem> items) {
        mStateLayout.showContentView();
        mAdapter.setNewData(items);
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadMoreData(mCategroy);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ArticleItem itemData = (ArticleItem) adapter
                .getData().get(position);
        ArticleItem itemInDB = DBManager.getInstance().get(itemData.getUrl());
        if (itemInDB == null) {
            itemData.setType(ArticleItem.TYPE_HISTORY);
            DBManager.getInstance().insertArticleItem(itemData);
        } else if ((itemInDB.getType() & ArticleItem.TYPE_HISTORY) != ArticleItem.TYPE_HISTORY) {
            itemInDB.setType(itemInDB.getType() | ArticleItem.TYPE_HISTORY);
            DBManager.getInstance().updataArticleItem(itemInDB);
        }
        ((TextView) view.findViewById(R.id.tv_title)).setTextColor(Color.GRAY);
        Intent intent = new Intent(getActivity(), WebDetialActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(WebDetialActivity.PARAMS_DATA, itemData);
//        bundle.putCharSequence(WebDetialActivity.PARAMS_URL, itemData.getUrl());
//        bundle.putCharSequence(WebDetialActivity.PARAMS_TITLE, itemData.getDesc());
        intent.putExtras(bundle);
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view, getResources().getString(R.string.share_item));
        ActivityCompat.startActivity(getContext(),intent,compat.toBundle());
    }


}
