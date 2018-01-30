package com.jino.jgank.view.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jino.baselibrary.base.fragment.BaseFragment;
import com.jino.baselibrary.di.scope.PreFragment;
import com.jino.jgank.R;
import com.jino.jgank.adapter.BaseFragmentPageAdapter;
import com.jino.jgank.net.GankService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

@PreFragment
public class GankArticleFragment extends BaseFragment {

    @BindView(R.id.lay_tab)
    public TabLayout mTabLayout;
    @BindView(R.id.viewpager_gank)
    public ViewPager mViewPager;

    private List<String> mTitle = Arrays.asList("Android", "iOS", "前端", "拓展资源");
    private List<Fragment> mFragments;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_gank_article;
    }

    @Override
    public void initView(View view) {
        mViewPager.setAdapter(new BaseFragmentPageAdapter(getChildFragmentManager(), mFragments, mTitle));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void initData() {
        initFragments();
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(GankCategroyFragment.newInstance(GankService.CATEGROY_ANDROID));
        mFragments.add(GankCategroyFragment.newInstance(GankService.CATEGROY_IOS));
        mFragments.add(GankCategroyFragment.newInstance(GankService.CATEGROY_WEB));
        mFragments.add(GankCategroyFragment.newInstance(GankService.CATEGROY_EXT));
    }

    @Override
    public void attachView(View view) {

    }

}
