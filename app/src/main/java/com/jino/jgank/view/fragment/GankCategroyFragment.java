package com.jino.jgank.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fingdo.statelayout.StateLayout;
import com.jino.baselibrary.base.fragment.AbsEasyRxFragment;
import com.jino.baselibrary.di.component.AppComponent;
import com.jino.jgank.R;
import com.jino.jgank.contract.GankCategroyContract;
import com.jino.jgank.di.component.DaggerFragmentComponent;
import com.jino.jgank.presenter.GankCategroyPresenter;

import butterknife.BindView;

/**
 * Created by JINO on 2018/1/24.
 */

public class GankCategroyFragment extends AbsEasyRxFragment<GankCategroyPresenter> implements GankCategroyContract.View {

    public static final String CATEGROY = "cate";

    private String mCategroy;

    @BindView(R.id.lay_state)
    public StateLayout mStateLayout;

    @BindView(R.id.recycle)
    public RecyclerView mItemList;

    public GankCategroyFragment() {
        if (getArguments() != null) {
            mCategroy = getArguments().getString(CATEGROY);
        }
    }

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
        mStateLayout.showLoadingView();
    }

    @Override
    public void onLoadFailed() {
        mStateLayout.showErrorView();
    }

    @Override
    public void onLoadSucceed() {
        mStateLayout.showContentView();
    }


    @Override
    public void loadMore() {

    }

    @Override
    public void refresh() {

    }
}
